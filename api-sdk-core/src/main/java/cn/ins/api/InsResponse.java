package cn.ins.api;

import java.io.Serializable;

/**
 * @author JQ.Feng
 * @title: InsResponse
 * @desc: API基础响应信息
 * @date 2024/6/6 11:04
 */
public class InsResponse<T> {
    private String requestId;
    private Integer code;
    private String msg;

    public InsResponse(){}

    public InsResponse(String requestId, int code, String msg) {
        this.requestId = requestId;
        this.code = code;
        this.msg = msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
