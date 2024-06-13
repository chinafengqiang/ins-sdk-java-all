package cn.ins.api;

import cn.ins.api.internal.http.AbstractHttpClient;
import cn.ins.api.internal.http.HttpClientUtil;
import cn.ins.api.internal.sign.DefaultSigner;
import cn.ins.api.internal.sign.Signer;
import cn.ins.api.internal.util.StringUtils;

/**
 * @author JQ.Feng
 * @title: DefaultInsClient
 * @desc: 默认insclient
 * @date 2024/6/10 22:15
 */
public class DefaultInsClient extends AbstractInsClient{
    private String secret;
    private Signer signer;
    private AbstractHttpClient httpClient;
    public DefaultInsClient(String serverUrl, String appKey,String secret) {
        super(serverUrl, appKey,InsConstants.SIGN_TYPE_HMACSHA1_KEY,InsConstants.CHARSET_UTF8);
        this.secret = secret;
        this.signer = new DefaultSigner(secret);
    }

    public DefaultInsClient(String serverUrl, String appKey, String secret, String signMethod, String charset) {
        super(serverUrl, appKey, signMethod, charset);
        this.secret = secret;
        this.signer = new DefaultSigner(secret);
    }

    public DefaultInsClient(InsConfig config){
        super(config.getServerUrl(),config.getAppKey(),config.getSignMethod(),config.getCharset()
        ,config.getConnectTimeout(),config.getReadTimeout());
        this.secret = config.getSecret();
        this.signer = new DefaultSigner(secret);
        setHttpClient(config);
    }

    private void setHttpClient(InsConfig config){
        httpClient = new HttpClientUtil();
        initHttpClient(httpClient,config);
        setCustomizedHttpClient(httpClient);
    }

    private void initHttpClient(AbstractHttpClient httpClient,InsConfig config) {
        //初始化变
        if (httpClient != null) {
            httpClient.setConnectTimeout(config.getConnectTimeout());
            httpClient.setReadTimeout(config.getReadTimeout());
            httpClient.setCustomHeaders(config.getCustomHeaders());
            httpClient.setProxyHost(config.getProxyHost());
            httpClient.setProxyPort(config.getProxyPort());
            httpClient.setMaxIdleConnections(config.getMaxIdleConnections());
            httpClient.setKeepAliveDuration(config.getKeepAliveDuration());
        }
    }
    @Override
    public Signer getSigner() {
        return signer;
    }
}
