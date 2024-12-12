package cn.inspiry.api.service.speaker.response;

import cn.ins.api.InsResponse;

/**
 * @author JQ.Feng
 * @title: GetAuthResponse
 * @desc: GetAuthResponse
 * @date 2024/12/12 16:10
 */
public class GetAuthResponse extends InsResponse {
    public GetAuthResponse() {
        super();
    }

    public GetAuthResponse(String requestId, int code, String msg) {
        super(requestId, code, msg);
    }
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
