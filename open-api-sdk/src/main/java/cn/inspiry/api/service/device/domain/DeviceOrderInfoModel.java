package cn.inspiry.api.service.device.domain;

import cn.ins.api.InsObject;
import cn.ins.api.internal.mapping.ApiField;

/**
 * @author JQ.Feng
 * @title: DeviceOrderInfoModel
 * @desc: /open/device/orderInfo 请求参数
 * @date 2024/9/26 11:05
 */
public class DeviceOrderInfoModel extends InsObject {
    @ApiField("serialNum")
    private String serialNum;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
}
