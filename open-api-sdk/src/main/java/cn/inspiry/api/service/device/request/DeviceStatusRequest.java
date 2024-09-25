package cn.inspiry.api.service.device.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.device.response.DeviceStatusResponse;

/**
 * @author JQ.Feng
 * @title: DeviceStatus
 * @desc:  获取设备（Terminal）状态 对应API地址：/open/device/status
 * @date 2024/9/25 17:27
 */
public class DeviceStatusRequest implements InsRequest<DeviceStatusResponse> {
    private String path = "/open/device/status";
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
    public Class<DeviceStatusResponse> getResponseClass() {
        return DeviceStatusResponse.class;
    }
}
