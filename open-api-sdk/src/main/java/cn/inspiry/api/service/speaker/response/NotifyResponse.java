package cn.inspiry.api.service.speaker.response;

import cn.ins.api.InsResponse;

/**
 * @author JQ.Feng
 * @title: NotifyResponse
 * @desc: NotifyResponse
 * @date 2024/12/12 16:34
 */
public class NotifyResponse extends InsResponse {
    public NotifyResponse() {
        super();
    }

    public NotifyResponse(String requestId, int code, String msg) {
        super(requestId, code, msg);
    }


}
