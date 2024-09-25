package cn.inspiry.api.service.push.response;

import cn.ins.api.InsResponse;

/**
 * @author JQ.Feng
 * @title: AdsPushResponse
 * @desc: TODO
 * @date 2024/8/22 18:09
 */
public class AdsPushResponse extends InsResponse {
    public AdsPushResponse(){super();}
    public AdsPushResponse(String requestId, int code, String msg) {
        super(requestId, code, msg);
    }
}
