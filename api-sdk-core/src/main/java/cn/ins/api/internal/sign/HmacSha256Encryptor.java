package cn.ins.api.internal.sign;

import cn.ins.api.InsConstants;
import cn.ins.api.internal.sign.HmacSha1Encryptor;

/**
 * @author JQ.Feng
 * @title: HmacSha256Encryptor
 * @desc: HmacSha256
 * @date 2024/6/6 15:50
 */
public class HmacSha256Encryptor extends HmacSha1Encryptor {
    @Override
    protected String getHashAlgorithm() {
        return InsConstants.SIGN_TYPE_HMACSHA256;
    }
}
