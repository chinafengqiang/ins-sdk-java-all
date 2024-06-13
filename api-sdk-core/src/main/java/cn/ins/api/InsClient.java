package cn.ins.api;

import cn.ins.api.exception.InsApiException;

/**
 * @author JQ.Feng
 * @title: InsClient
 * @desc: HTTP请求客户端
 * @date 2024/6/6 11:38
 */
public interface InsClient {
    <T extends InsResponse> T execute(InsRequest request) throws InsApiException;

    <T extends InsResponse> T execute(InsRequest request,String authToken) throws InsApiException;
}
