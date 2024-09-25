package cn.inspiry.api.service.push.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.push.response.AdsPushResponse;

/**
 * @author JQ.Feng
 * @title: AdsPushRequest
 * @desc: open/isp/ads/push
 * @date 2024/8/22 18:11
 */

public class AdsPushRequest implements InsRequest<AdsPushResponse> {
    private String path = "/open/isp/ads/push";
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
    public Class<AdsPushResponse> getResponseClass() {
        return AdsPushResponse.class;
    }
}
