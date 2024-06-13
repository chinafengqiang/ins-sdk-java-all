package cn.ins.api.internal.sign;

import cn.ins.api.exception.InsApiException;

/**
 * @author JQ.Feng
 * @title: IHashEncryptor
 * @desc: TODO
 * @date 2024/6/6 12:30
 */
public interface IHashEncryptor {

    /**
     * 计算指定内容的签名
     * @param content ： 待签名的原文
     * @param charset ： 待签名的原文字符集编码
     * @param secret ： 秘钥
     * @return 签名字符串
     * @throws InsApiException
     */
    String sign(String content, String charset, String secret) throws InsApiException;

    /**
     * 验证指定内容的签名是否正确
     * @param content  ： 待签名的原文
     * @param charset ： 待签名的原文字符集编码
     * @param secret ： 秘钥
     * @param sign ：签名字符串
     * @return true：验证通过；false：验证不通过
     * @throws InsApiException
     */
    boolean verify(String content, String charset, String secret, String sign) throws InsApiException;
}
