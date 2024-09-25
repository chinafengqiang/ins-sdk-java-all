package cn.inspiry.api.service.device.response;

import cn.ins.api.InsResponse;

/**
 * @author JQ.Feng
 * @title: DeviceStatusResponse
 * @desc: /open/device/status 返回结果
 * @date 2024/9/25 17:30
 */
public class DeviceStatusResponse extends InsResponse {
    public DeviceStatusResponse(String requestId, int code, String msg){
        super(requestId,code,msg);
    }
    public DeviceStatusResponse(){
        super();
    }
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
