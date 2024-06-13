package cn.ins.api.internal.sign;

import cn.ins.api.InsConstants;
import cn.ins.api.exception.InsApiException;
import cn.ins.api.internal.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JQ.Feng
 * @title: HashAlgorithmManager
 * @desc: Hash签名算法管理类
 * @date 2024/6/6 12:38
 */
public class HashAlgorithmManager {
    private static final Map<String, IHashEncryptor> hashManager = new ConcurrentHashMap<String, IHashEncryptor>();

    static {
        hashManager.put(InsConstants.SIGN_TYPE_HMACSHA1_KEY, new HmacSha1Encryptor());
        hashManager.put(InsConstants.SIGN_TYPE_HMACSHA256_KEY, new HmacSha256Encryptor());
    }


    public static IHashEncryptor getByName(String type) throws InsApiException {
        IHashEncryptor hashEncryptor = null;

        if (!StringUtils.isEmpty(type)) {
            hashEncryptor = hashManager.get(type);
        }

        if (hashEncryptor != null) {
            return hashEncryptor;
        }

        throw new InsApiException(String.format("sign type error : [%s]", type));
    }

    public static Map<String, IHashEncryptor> getHashAlgorithmManager() {
        return hashManager;
    }
}
