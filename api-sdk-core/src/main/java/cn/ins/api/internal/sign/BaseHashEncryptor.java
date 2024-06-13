package cn.ins.api.internal.sign;

import cn.ins.api.exception.InsApiException;
import cn.ins.api.internal.util.StringUtils;

/**
 * @author JQ.Feng
 * @title: BaseHashEncryptor
 * @desc: 签名工具类
 * @date 2024/6/6 15:11
 */
public abstract class BaseHashEncryptor implements IHashEncryptor {
    //默认字符集编码。现在推荐使用UTF-8
    private static String DEFAULT_CHARSET = "UTF-8";

    @Override
    public String sign(String content, String charset, String secret) throws InsApiException {
        try {
            if (StringUtils.isEmpty(content)) {
                throw new InsApiException("sign content is empty");
            }
            if (StringUtils.isEmpty(secret)) {
                throw new InsApiException("sign secret is empty");
            }
            if (StringUtils.isEmpty(charset)) {
                charset = DEFAULT_CHARSET;
            }
            return doSign(content, charset, secret);
        }catch (Exception e){
             throw new InsApiException("sign error",e);
        }

    }

    @Override
    public boolean verify(String content, String charset, String secret, String sign) throws InsApiException {
        try {
            if (StringUtils.isEmpty(content)) {
                throw new InsApiException("verify sign content is empty");
            }
            if (StringUtils.isEmpty(secret)) {
                throw new InsApiException("verify sign secret is empty");
            }
            if (StringUtils.isEmpty(sign)) {
                throw new InsApiException("sign is empty");
            }
            if (StringUtils.isEmpty(charset)) {
                charset = DEFAULT_CHARSET;
            }
            return doVerify(content,charset,secret,sign);
        }catch (Exception e){
            throw new InsApiException("verify sign error",e);
        }
    }

    protected abstract String doSign(String content, String charset, String secret) throws Exception;

    protected abstract boolean doVerify(String content, String charset, String secret, String sign) throws Exception;

    protected abstract String getHashAlgorithm();
}
