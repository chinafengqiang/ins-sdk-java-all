/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package cn.ins.api.internal.sign;


public interface Signer {

    String sign(String sourceContent, String signType, String charset);
}