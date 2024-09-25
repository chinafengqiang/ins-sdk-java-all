package cn.inspiry.api.service.device.domain;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: DeviceStatusModel
 * @desc: /open/device/status 请求参数
 * @date 2024/9/25 17:39
 */
public class DeviceStatusModel extends InsObject {

    @ApiField("serialNum")
    private String serialNum;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
