package cn.inspiry.api.service.speaker.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.speaker.response.GetAuthResponse;

/**
 * @author JQ.Feng
 * @title: GetAuthRequest
 * @desc: /open/auth request
 * @date 2024/12/12 16:08
 */
public class GetAuthRequest implements InsRequest<GetAuthResponse> {
    private String path = "/open/auth";
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
    public Class<GetAuthResponse> getResponseClass() {
        return GetAuthResponse.class;
    }
}
