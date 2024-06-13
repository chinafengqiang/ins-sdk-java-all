package cn.ins.api.internal.util;

import cn.ins.api.FileItem;
import cn.ins.api.InsConstants;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * @author JQ.Feng
 * @title: HttpUtils
 * @desc: 网络工具类
 * @date 2024/6/10 16:29
 */
public class WebUtils {
    private static final String DEFAULT_CHARSET = InsConstants.CHARSET_UTF8;
    private static final String METHOD_POST     = "POST";
    private static final String METHOD_GET      = "GET";

    private static SSLContext ctx = null;

    private static HostnameVerifier verifier = null;

    private static SSLSocketFactory socketFactory = null;

    private static int keepAliveTimeout = 0;

    /**
     * 是否校验SSL服务端证书，默认为需要校验
     */
    private static volatile boolean needCheckServerTrusted = true;

    static {

        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()},
                    new SecureRandom());

            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);

            socketFactory = ctx.getSocketFactory();
        } catch (Exception e) {

        }

        verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true; //允许URL的主机名和服务器的标识主机名不匹配的情况
            }
        };

    }

    private WebUtils() {
    }

    /**
     * 设置是否校验SSL服务端证书
     *
     * @param needCheckServerTrusted true：需要校验（默认，推荐）；
     *                               <p>
     *                               false：不需要校验（仅当部署环境不便于进行服务端证书校验，且已有其他方式确保通信安全时，可以关闭SSL服务端证书校验功能）
     */
    public static void setNeedCheckServerTrusted(boolean needCheckServerTrusted) {
        WebUtils.needCheckServerTrusted = needCheckServerTrusted;
    }

    /**
     * Getter method for property <tt>needCheckServerTrusted</tt>.
     *
     * @return property value of needCheckServerTrusted
     */
    public static boolean isNeedCheckServerTrusted() {
        return needCheckServerTrusted;
    }

    /**
     * 设置KeepAlive连接超时时间，一次HTTP请求完成后，底层TCP连接将尝试尽量保持该超时时间后才关闭，以便其他HTTP请求复用TCP连接
     * <p>
     * KeepAlive连接超时时间设置为0，表示使用默认的KeepAlive连接缓存时长（目前为5s）
     * <p>
     * 连接并非一定能保持指定的KeepAlive超时时长，比如服务端断开了连接
     * <p>
     * 注：该方法目前只在JDK8上测试有效
     *
     * @param timeout KeepAlive超时时间，单位秒
     */
    public static void setKeepAliveTimeout(int timeout) {
        if (timeout < 0 || timeout > 60) {
            throw new RuntimeException("keep-alive timeout value must be between 0 and 60.");
        }
        keepAliveTimeout = timeout;
    }

    /**
     * 执行JSON 数据作为请求体HTTP POST请求
     *
     * @param url            请求地址
     * @param params         请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @return 响应字符串
     * @throws IOException
     */
    public static String doRequestBodyPost(String url, Map<String, Object> params, String charset,
                                int connectTimeout, int readTimeout, Map<String, String> headers) throws IOException {
        String ctype = "application/json;charset=" + charset;
        String query = buildRequestBodyQuery(params);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout,"", 0, headers, null);
    }


    /**
     * 执行HTTP POST请求，可使用代理proxy。
     *
     * @param url            请求地址
     * @param params         请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, String charset,
                                int connectTimeout, int readTimeout, String proxyHost,
                                int proxyPort, Map<String, String> headers, Map<String, String> resHeaders) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout, proxyHost, proxyPort, headers, resHeaders);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url            请求地址
     * @param ctype          请求类型
     * @param content        请求字节数组
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, String ctype, byte[] content, int connectTimeout,
                                int readTimeout, String proxyHost, int proxyPort, Map<String, String> headers, Map<String, String> resHeaders) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                conn = null;
                if (!StringUtils.isEmpty(proxyHost)) {
                    conn = getConnection(new URL(url), METHOD_POST, ctype, proxyHost, proxyPort, headers);
                } else {
                    conn = getConnection(new URL(url), METHOD_POST, ctype, headers);
                }
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                InsLogger.logCommError(e,url,content);
                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
                if (resHeaders != null) {
                    resHeaders.put("trace_id", conn.getHeaderField("trace_id"));
                }
            } catch (IOException e) {
                InsLogger.logCommError(e,conn,content);
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();

            }
        }

        return rsp;
    }


    /**
     * 执行带文件上传的HTTP POST请求。
     *
     * @param url            请求地址
     * @param params         文本请求参数
     * @param fileParams     文件请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params,
                                Map<String, FileItem> fileParams, String charset,
                                int connectTimeout, int readTimeout, String proxyHost,
                                int proxyPort, Map<String, String> headers, Map<String, String> resHeaders) throws IOException {
        if (fileParams == null || fileParams.isEmpty()) {
            return doPost(url, params, charset, connectTimeout, readTimeout, proxyHost, proxyPort, headers, resHeaders);
        }

        String boundary = System.currentTimeMillis() + ""; // 随机分隔线
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                String ctype = "multipart/form-data;boundary=" + boundary + ";charset=" + charset;
                conn = null;
                if (!StringUtils.isEmpty(proxyHost)) {
                    conn = getConnection(new URL(url), METHOD_POST, ctype, proxyHost, proxyPort, headers);
                } else {
                    conn = getConnection(new URL(url), METHOD_POST, ctype, headers);
                }
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
                conn.setFixedLengthStreamingMode(getTotalLength(params, fileParams, boundary, charset));
            } catch (IOException e) {
                InsLogger.logCommError(e,url,params);
                throw e;
            }

            try {
                out = conn.getOutputStream();

                byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);

                // 组装文本请求参数
                Set<Map.Entry<String, String>> textEntrySet = params.entrySet();
                for (Map.Entry<String, String> textEntry : textEntrySet) {
                    byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue(),
                            charset);
                    out.write(entryBoundaryBytes);
                    out.write(textBytes);
                }

                // 组装文件请求参数
                Set<Map.Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
                for (Map.Entry<String, FileItem> fileEntry : fileEntrySet) {
                    FileItem fileItem = fileEntry.getValue();
                    byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getFileName(),
                            fileItem.getMimeType(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(fileBytes);
                    fileItem.writeFileContent(out);
                }

                // 添加请求结束标志
                byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
                out.write(endBoundaryBytes);
                rsp = getResponseAsString(conn);
                if (resHeaders != null) {
                    resHeaders.put("trace_id", conn.getHeaderField("trace_id"));
                }
            } catch (IOException e) {
                InsLogger.logCommError(e,conn,params);
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    public static byte[] getTextEntry(String fieldName, String fieldValue,
                                      String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    public static  byte[] getFileEntry(String fieldName, String fileName, String mimeType,
                                       String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    private static long getTotalLength(Map<String, String> params, Map<String, FileItem> fileParams, String boundary, String charset) {
        try {
            byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);
            long length = 0;

            Set<Map.Entry<String, String>> textEntrySet = params.entrySet();
            for (Map.Entry<String, String> textEntry : textEntrySet) {
                byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue(),
                        charset);
                length += entryBoundaryBytes.length + textBytes.length;
            }

            Set<Map.Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
            for (Map.Entry<String, FileItem> fileEntry : fileEntrySet) {
                FileItem fileItem = fileEntry.getValue();
                byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getFileName(),
                        fileItem.getMimeType(), charset);
                length += entryBoundaryBytes.length + fileBytes.length + fileItem.fileContentLength();
            }

            byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
            length += endBoundaryBytes.length;
            return length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 执行HTTP GET请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, DEFAULT_CHARSET);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params,
                               String charset) throws IOException {
        HttpURLConnection conn = null;
        String rsp = null;

        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);
            try {
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype, null);
            } catch (IOException e) {
               InsLogger.logCommError(e,url,params);
               throw e;
            }

            try {
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                InsLogger.logCommError(e, conn, params);
                throw e;
            }

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }


    public static HttpURLConnection getConnection(URL url, String method, String ctype,
                                                  Map<String, String> headers) throws IOException {
        return getConnection(url, method, ctype, null, headers);
    }

    public static HttpURLConnection getConnection(URL url, String method, String ctype,
                                                  String proxyHost, int proxyPort,
                                                  Map<String, String> headers) throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        return getConnection(url, method, ctype, proxy, headers);
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, Proxy proxy,
                                                   Map<String, String> headers) throws IOException {
        HttpURLConnection conn = null;
        if ("https".equals(url.getProtocol())) {
            HttpsURLConnection connHttps = null;
            if (proxy != null) {
                connHttps = (HttpsURLConnection) url.openConnection(proxy);
            } else {
                connHttps = (HttpsURLConnection) url.openConnection();
            }
            if (!needCheckServerTrusted) {
                //设置不校验服务端证书的SSLContext
                connHttps.setSSLSocketFactory(socketFactory);
                connHttps.setHostnameVerifier(verifier);
            }
            conn = connHttps;
        } else {
            conn = null;
            if (proxy != null) {
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "aop-sdk-java");
        conn.setRequestProperty("Content-Type", ctype);
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
        }
        return conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        }

        if (StringUtils.isEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "?" + query;
            }
        } else {
            if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }
        }

        return new URL(strUrl);
    }

    public static String buildRequestBodyQuery(Map<String, Object> params){
        if (params == null || params.isEmpty()) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(params);
        return jsonObject.toJSONString();
    }


    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());

        //此时设置KeepAlive超时所需数据结构才刚初始化完整，可以通过反射修改
        //同时也不宜将修改时机再滞后，因为可能后续连接缓存类已经消费了默认的KeepAliveTimeout值，再修改已经无效
        setKeepAliveTimeout(conn);
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    /**
     * 由于HttpUrlConnection不支持设置KeepAlive超时时间，该方法通过反射机制设置
     *
     * @param connection 需要设置KeepAlive的连接
     */
    private static void setKeepAliveTimeout(HttpURLConnection connection) {
        if (keepAliveTimeout == 0) {
            return;
        }
        try {

            Field delegateHttpsUrlConnectionField = Class.forName("sun.net.www.protocol.https.HttpsURLConnectionImpl").getDeclaredField(
                    "delegate");
            delegateHttpsUrlConnectionField.setAccessible(true);
            Object delegateHttpsUrlConnection = delegateHttpsUrlConnectionField.get(connection);

            Field httpClientField = Class.forName("sun.net.www.protocol.http.HttpURLConnection").getDeclaredField("http");
            httpClientField.setAccessible(true);
            Object httpClient = httpClientField.get(delegateHttpsUrlConnection);

            Field keepAliveTimeoutField = Class.forName("sun.net.www.http.HttpClient").getDeclaredField("keepAliveTimeout");
            keepAliveTimeoutField.setAccessible(true);
            keepAliveTimeoutField.setInt(httpClient, keepAliveTimeout);
        } catch (Throwable ignored) {
            //设置KeepAlive超时只是一种优化辅助手段，设置失败不应阻塞主链路，设置失败不应影响功能
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }
    }
}
