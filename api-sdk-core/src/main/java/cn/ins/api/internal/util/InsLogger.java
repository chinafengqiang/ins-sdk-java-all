package cn.ins.api.internal.util;

import cn.ins.api.InsConstants;
import cn.ins.api.InsResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 客户端日志 通讯错误格式：time^_^api^_^app^_^ip^_^os^_^sdk^_^url^responseCode 业务错误格式：time^_^response
 */
public class InsLogger {

    private static final Log clog = LogFactory.getLog("sdk.comm.err");
    private static final Log blog = LogFactory.getLog("sdk.biz.err");
    private static final Log ilog = LogFactory.getLog("sdk.biz.info");

    private static String  osName           = System.getProperties().getProperty("os.name");
    private static String  ip               = null;
    private static boolean needEnableLogger = true;

    public static void setNeedEnableLogger(boolean needEnableLogger) {
        InsLogger.needEnableLogger = needEnableLogger;
    }

    public static String getIp() {
        if (ip == null) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ip;
    }

    public static void setIp(String ip) {
        InsLogger.ip = ip;
    }

    /**
     * 通讯错误日志
     */
    public static void logCommError(Exception e, HttpURLConnection conn, byte[] content) {
        if (!needEnableLogger) {
            return;
        }
        String contentString = null;
        try {
            contentString = new String(content, "UTF-8");
            logCommError(e, conn, contentString);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 通讯错误日志
     */
    public static void logCommError(Exception e, String url,byte[] content) {
        if (!needEnableLogger) {
            return;
        }
        String contentString = null;
        try {
            contentString = new String(content, "UTF-8");
            logCommError(e, url, contentString);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 通讯错误日志
     */
    public static void logCommError(Exception e, HttpURLConnection conn, Map<String, String> params) {
        if (!needEnableLogger) {
            return;
        }
        _logCommError(e, conn, null, params);
    }

    public static void logCommError(Exception e, String url, Map<String, String> params) {
        if (!needEnableLogger) {
            return;
        }
        _logCommError(e, null, url, params);
    }

    /**
     * 通讯错误日志
     */
    private static void logCommError(Exception e, HttpURLConnection conn,String content) {
        Map<String, String> params = parseParam(content);
        _logCommError(e, conn, null, params);
    }

    /**
     * 通讯错误日志
     */
    private static void logCommError(Exception e, String url,
                                     String content) {
        Map<String, String> params = parseParam(content);
        _logCommError(e, null, url, params);
    }

    /**
     * 通讯错误日志
     */
    private static void _logCommError(Exception e, HttpURLConnection conn, String url, Map<String, String> params) {
        DateFormat df = new SimpleDateFormat(InsConstants.DATE_TIME_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone(InsConstants.DATE_TIMEZONE));
        String sdkName = InsConstants.SDK_VERSION;
        String urlStr = null;
        if (conn != null) {
            urlStr = conn.getURL().toString();
        } else {
            urlStr = url;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));// 时间
        sb.append("^_^");
        sb.append(getIp());// IP地址
        sb.append("^_^");
        sb.append(osName);// 操作系统
        sb.append("^_^");
        sb.append(sdkName);// SDK名字,这是例子，请换成其他名字
        sb.append("^_^");
        sb.append(urlStr);// 请求URL
        sb.append("^_^");
        sb.append((e.getMessage() + "").replaceAll("\r\n", " "));
        clog.error(sb.toString());
    }

    private static Map<String, String> parseParam(String contentString) {
        Map<String, String> params = new HashMap<String, String>();
        if (contentString == null || contentString.trim().equals("")) {
            return params;
        }
        String[] paramsArray = contentString.split("\\&");
        if (paramsArray != null) {
            for (String param : paramsArray) {
                String[] keyValue = param.split("=");
                if (keyValue != null && keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    /**
     * 业务/系统错误日志
     */
    public static void logBizDebug(String rsp) {
        if (!needEnableLogger) {
            return;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(InsConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append(rsp);

        if (blog.isDebugEnabled()) {
            blog.debug(sb.toString());
        }
    }

    /**
     * 业务/系统错误日志
     */
    public static void logBizError(String rsp) {
        if (!needEnableLogger) {
            return;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(InsConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append(rsp);
        blog.error(sb.toString());
    }



    /**
     * 业务/系统错误日志
     */
    public static void logBizError(Throwable t) {
        if (!needEnableLogger) {
            return;
        }
        blog.error(t);
    }

    /**
     * 发生特别错误时记录完整错误现场
     */
    public static void logErrorScene(Map<String, Object> rt, InsResponse tRsp) {
        if (!needEnableLogger) {
            return;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone(InsConstants.DATE_TIMEZONE));
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorScene");
        sb.append("^_^");
        sb.append(tRsp.getCode());
        sb.append("^_^");
        sb.append(tRsp.getMsg());
        sb.append("^_^");
        sb.append(ip);
        sb.append("^_^");
        sb.append(osName);
        sb.append("^_^");
        sb.append(df.format(new Date()));
        sb.append("^_^");
        sb.append("Params:");
        appendLog(rt, sb);
        blog.error(sb.toString());
    }



    private static void appendLog(Map<String, Object> map, StringBuilder sb) {
        boolean first = true;
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            if (!first) {
                sb.append("&");
            } else {
                first = false;
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
    }

    public static Boolean isBizDebugEnabled() {
        return blog.isDebugEnabled();
    }

    /**
     * 开启DEBUG级别日志（仅针对JDK14LOGGER，LOG4J请自行修改配置文件）
     *
     * @param isEnabled
     */
    public static void setJDKDebugEnabled(Boolean isEnabled) {
        //如果使用JDK14LOGGER，将业务日志级别设为DEBUG(FINE)
        //        if (blog instanceof Jdk14Logger) {
        //            Jdk14Logger logger = (Jdk14Logger) blog;
        //            if (isEnabled) {
        //                logger.getLogger().setLevel(Level.FINE);
        //                Handler consoleHandler = new ConsoleHandler();
        //                consoleHandler.setLevel(Level.FINE);
        //                logger.getLogger().addHandler(consoleHandler);
        //            } else {
        //                logger.getLogger().setLevel(Level.INFO);
        //            }
        //        }
    }
}
