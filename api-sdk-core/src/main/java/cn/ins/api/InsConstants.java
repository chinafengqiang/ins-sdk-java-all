package cn.ins.api;

/**
 * @author JQ.Feng
 * @title: InsConstants
 * @desc: TODO
 * @date 2024/6/6 11:56
 */
public class InsConstants {
    public static final String SIGN_TYPE_HMACSHA1 = "HmacSHA1";
    public static final String SIGN_TYPE_HMACSHA256 = "HmacSHA256";

    public static final String SIGN_TYPE_HMACSHA1_KEY = "HMAC-SHA1";
    public static final String SIGN_TYPE_HMACSHA256_KEY = "HMAC-SHA256";

    public static final String FORMAT_JSON = "json";

    public static final String TIMESTAMP = "Timestamp";

    public static final String VERSION = "Version";

    public static final String SIGN = "Sign";

    public static final String APP_KEY = "AppKey";

    public static final String SIGN_METHOD = "SignMethod";

    public static final String Nonce = "Nonce";

    public static final String TOKEN = "Token";

    /**
     * 默认时间格式
     **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date默认时区
     **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /**
     * UTF-8字符集
     **/
    public static final String CHARSET_UTF8 = "UTF-8";


    /**
     * SDK版本号
     */
    public static final String SDK_VERSION = "ins-sdk-java-1.0.0.ALL";

    /**
     * api版本号
     */
    public static final String API_VERSION = "v1";

    /**
     * api成功状态码
     */
    public static final int API_RESULT_SUCCESS = 0;


    public static final String API_RESULT_CODE = "code";
    public static final String API_RESULT_MSG = "msg";
    public static final String API_RESULT_REQUEST_ID = "requestId";
    public static final String API_RESULT_DATA = "data";

}
