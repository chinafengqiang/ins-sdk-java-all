package cn.ins.api.internal.sign;

import cn.ins.api.exception.InsApiException;

/**
 * @author JQ.Feng
 * @title: DefaultSigner
 * @desc: 默认签名类
 * @date 2024/6/7 21:40
 */
public class DefaultSigner implements Signer {
    private String secret;

    public DefaultSigner(String secret) {
        this.secret = secret;
    }
    @Override
    public String sign(String sourceContent, String signType, String charset) {
        String sign = null;
        try {
            sign = HashAlgorithmManager.getByName(signType).sign(sourceContent,charset,secret);
        } catch (InsApiException e) {
            throw new RuntimeException(e);
        }
        return sign;
    }
}
