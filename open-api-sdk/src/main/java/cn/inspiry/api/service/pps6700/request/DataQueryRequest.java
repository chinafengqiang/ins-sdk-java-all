package cn.inspiry.api.service.pps6700.request;

import cn.ins.api.InsObject;
import cn.ins.api.InsRequest;
import cn.inspiry.api.service.pps6700.response.DataQueryResponse;

/**
 * @author JQ.Feng
 * @title: DataQueryRequest
 * @desc: TODO
 * @date 2024/6/11 09:43
 */
public class DataQueryRequest implements InsRequest<DataQueryResponse> {
    private String path = "/open/data/pps6700/query";
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
    public Class<DataQueryResponse> getResponseClass() {
        return DataQueryResponse.class;
    }
}
