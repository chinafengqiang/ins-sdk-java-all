package cn.ins.api;

import cn.ins.api.internal.http.AbstractHttpClient;

import java.util.Map;

/**
 * @author JQ.Feng
 * @title: InsConfig
 * @desc: 配置类
 * @date 2024/6/10 22:22
 */
public class InsConfig {
    /**
     * 网关地址
     * 线上：
     * 沙箱：
     */
    private String serverUrl = "";

    /**
     * 开放平台key
     */
    private String appKey;

    /**
     * 私钥
     */
    private String secret;

    /**
     * 报文格式，推荐：json
     */
    private String format = InsConstants.FORMAT_JSON;

    /**
     * 字符串编码，推荐：utf-8
     */
    private String charset = InsConstants.CHARSET_UTF8;

    /**
     * 签名算法类型
     */
    private String signMethod = InsConstants.SIGN_TYPE_HMACSHA1_KEY;



    /**
     * 连接超时，单位：毫秒
     */
    private int connectTimeout = 3000;

    /**
     * 读取超时，单位：毫秒
     */
    private int readTimeout = 15000;

    /**
     * HTTP代理服务器主机地址
     */
    private String proxyHost;

    /**
     * HTTP代理服务器端口
     */
    private int proxyPort;

    /**
     * 自定义HTTP Header
     */
    private Map<String, String> customHeaders;

    /**
     * 连接池最大空闲连接数
     */
    private int maxIdleConnections = 0;

    /**
     * 存活时间，单位：毫秒
     */
    private long keepAliveDuration = 10000L;

    /**
     * 定制httpClient的类实例（需继承AbstractHttpClient）
     */
    private AbstractHttpClient customizedHttpClient;

    public InsConfig(){}

    public InsConfig(String serverUrl, String appKey, String secret) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.secret = secret;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public int getMaxIdleConnections() {
        return maxIdleConnections;
    }

    public void setMaxIdleConnections(int maxIdleConnections) {
        this.maxIdleConnections = maxIdleConnections;
    }

    public long getKeepAliveDuration() {
        return keepAliveDuration;
    }

    public void setKeepAliveDuration(long keepAliveDuration) {
        this.keepAliveDuration = keepAliveDuration;
    }

    public AbstractHttpClient getCustomizedHttpClient() {
        return customizedHttpClient;
    }

    public void setCustomizedHttpClient(AbstractHttpClient customizedHttpClient) {
        this.customizedHttpClient = customizedHttpClient;
    }

}
