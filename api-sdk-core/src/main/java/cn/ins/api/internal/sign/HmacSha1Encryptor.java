package cn.ins.api.internal.sign;

import cn.ins.api.InsConstants;
import cn.ins.api.internal.sign.BaseHashEncryptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author JQ.Feng
 * @title: HmacSha1Encryptor
 * @desc: HmacSha1 实现
 * @date 2024/6/6 15:35
 */
public class HmacSha1Encryptor extends BaseHashEncryptor {
    @Override
    protected String doSign(String content, String charset, String secret) throws Exception {

        byte[] secretBytes = secret.getBytes(charset);
        byte[] contentBytes = content.getBytes(charset);
        // Create HMAC-SHA256 key from the given secret
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretBytes, getHashAlgorithm());

        // Get an instance of Mac object implementing HMAC-SHA256
        Mac mac = Mac.getInstance(getHashAlgorithm());
        mac.init(secretKeySpec);

        // Calculate the HMAC value
        byte[] hmacBytes = mac.doFinal(contentBytes);

        // Convert result into a hexadecimal string
        StringBuilder sb = new StringBuilder(hmacBytes.length * 2);
        for (byte b : hmacBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    protected boolean doVerify(String content, String charset, String secret, String sign) throws Exception {
        return sign.equals(doSign(content, charset, secret));
    }

    @Override
    protected String getHashAlgorithm() {
        return InsConstants.SIGN_TYPE_HMACSHA1;
    }
}
