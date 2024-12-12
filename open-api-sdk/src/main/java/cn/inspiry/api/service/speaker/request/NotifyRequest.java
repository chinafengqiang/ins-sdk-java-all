package cn.inspiry.api.service.speaker.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.speaker.response.NotifyResponse;

/**
 * @author JQ.Feng
 * @title: NotifyRequest
 * @desc: NotifyRequest
 * @date 2024/12/12 16:35
 */
public class NotifyRequest implements InsRequest<NotifyResponse> {
    private String path = "/open/speaker/notify";
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
    public Class<NotifyResponse> getResponseClass() {
        return NotifyResponse.class;
    }

}
