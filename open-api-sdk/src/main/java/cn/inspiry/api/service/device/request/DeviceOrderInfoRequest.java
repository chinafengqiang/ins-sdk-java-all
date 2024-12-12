package cn.inspiry.api.service.device.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.device.response.DeviceOrderInfoResponse;
import cn.inspiry.api.service.device.response.DeviceStatusResponse;

/**
 * @author JQ.Feng
 * @title: DeviceOrderInfoRequest
 * @desc: 获取设备选品信息 -- 对应API地址：/open/device/orderInfo
 * @date 2024/9/26 10:58
 */
public class DeviceOrderInfoRequest implements InsRequest<DeviceOrderInfoResponse> {
    private String path = "/open/device/orderInfo";
    private String apiVersion = "v1";
    private boolean needEncrypt = false;
    private InsObject bizModel;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String getVersion() {
        return this.apiVersion;
    }

    @Override
    public void setNeedEncrypt(boolean needEncrypt) {
        this.needEncrypt = needEncrypt;
    }

    @Override
    public boolean getNeedEncrypt() {
        return this.needEncrypt;
    }

    @Override
    public InsObject getBizModel() {
        return this.bizModel;
    }

    @Override
    public void setBizModel(InsObject bizModel) {
        this.bizModel = bizModel;
    }

    @Override
    public Class<DeviceOrderInfoResponse> getResponseClass() {
        return DeviceOrderInfoResponse.class;
    }
}
