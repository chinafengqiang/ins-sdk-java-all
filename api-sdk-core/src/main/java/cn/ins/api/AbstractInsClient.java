package cn.ins.api;

import cn.ins.api.exception.InsApiException;
import cn.ins.api.internal.http.AbstractHttpClient;
import cn.ins.api.internal.http.HttpClientUtil;
import cn.ins.api.internal.parser.json.ObjectJsonParser;
import cn.ins.api.internal.util.*;
import cn.ins.api.internal.sign.InsSignature;
import cn.ins.api.internal.sign.Signer;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author JQ.Feng
 * @title: AbstractInsClient
 * @desc: HTTP请求客户端抽象类
 * @date 2024/6/6 11:42
 */
public abstract class AbstractInsClient implements InsClient{
    private String serverUrl;
    private String appKey;
    private String signMethod;
    private String token;
    private String charset;

    private int connectTimeout  = 3000;
    private int readTimeout = 15000;
//    private   String                                     proxyHost;
//    private   int                                        proxyPort;
//    private   Map<String, String>                        headers;
//    private   int                                        maxIdleConnections = 0;
//    private   long                                       keepAliveDuration  = 10000L;
    private AbstractHttpClient customizedHttpClient;

    public AbstractInsClient(String serverUrl, String appKey, String signMethod, String charset) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.signMethod = signMethod;
        this.charset = charset;
    }

    public AbstractInsClient(String serverUrl, String appKey, String signMethod, String charset,int connectTimeout,int readTimeout) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.signMethod = signMethod;
        this.charset = charset;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public <T extends InsResponse> T execute(InsRequest request) throws InsApiException {
        return _execute(request);
    }

    @Override
    public <T extends InsResponse> T execute(InsRequest request, String authToken) throws InsApiException {
        return execute(request);
    }

    private <T extends InsResponse> T _execute(InsRequest request) throws InsApiException{
        InsParser<T> parser = new ObjectJsonParser<T>(request.getResponseClass());
        String rt = doPost(request);
        if (StringUtils.isEmpty(rt)) {
            return null;
        }
        T tRsp = null;
        try {
            tRsp = parser.parse(rt);
        } catch (InsApiException e) {
            InsLogger.logBizError(rt);
            throw new InsApiException(e);
        }
        return tRsp;
    }

    private <T extends InsResponse> String doPost(InsRequest<T> request) throws InsApiException {
            Map<String, Object> requestHolderWithSign = getRequestHolderWithSign(request);
            String rsp = null;
            try {
                rsp = customizedHttpClient == null ? WebUtils.doRequestBodyPost(this.serverUrl+request.getPath(),requestHolderWithSign, charset, connectTimeout, readTimeout, null)
                : customizedHttpClient.doRequestBodyPost(this.serverUrl+request.getPath(),requestHolderWithSign, charset);
            } catch (Exception e) {
                throw new InsApiException(e);
            }

            return rsp;
    }

    /**
     * 组装接口参数，处理加密、签名逻辑
     * @param request
     * @return
     * @throws InsApiException
     */
    private Map<String,Object> getRequestHolderWithSign(InsRequest request)throws InsApiException {
        InsObject bizModel = request.getBizModel();
        if (bizModel == null) {
            throw new InsApiException("request is null");
        }

        InsHashMap bizMap = new ObjectWriter().write(bizModel, true);
        Map<String,Object> appParams = setCommonParams(request);
        if(bizModel != null && bizMap.size() > 0){
            appParams.putAll(bizMap);
        }
        String signContent = InsSignature.getSignContent(appParams);
        String sign = getSigner().sign(signContent, signMethod, charset);
        appParams.put(InsConstants.SIGN,sign.toUpperCase());
        return appParams;
    }

    private Map<String,Object> setCommonParams(InsRequest request){
        Map<String, Object> appParams = new TreeMap<String, Object>();
        appParams.put(InsConstants.APP_KEY,appKey);
        appParams.put(InsConstants.TIMESTAMP,System.currentTimeMillis());
        appParams.put(InsConstants.Nonce,UUID.randomUUID().toString().replace("-", ""));
        appParams.put(InsConstants.SIGN_METHOD,signMethod);
        if(StringUtils.areNotEmpty(token)){
            appParams.put(InsConstants.TOKEN,token);
        }
        appParams.put(InsConstants.VERSION,request.getVersion());
        return appParams;
    }

    public void setCustomizedHttpClient(AbstractHttpClient customizedHttpClient) {
        this.customizedHttpClient = customizedHttpClient;
    }

    public abstract Signer getSigner();
}

