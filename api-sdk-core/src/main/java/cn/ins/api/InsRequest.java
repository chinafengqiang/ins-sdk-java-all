package cn.ins.api;

/**
 * @author JQ.Feng
 * @title: InsRequest
 * @desc: 请求接口
 * @date 2024/6/6 11:34
 */
public interface InsRequest<T extends InsResponse> {
    /**
     * 设置当前API method对应的path
     * @param path
     */
    void setPath(String path);

    /**
     * 获取path
     * @return
     */
    String getPath();
    /**
     * 设置当前API的版本信息
     *
     * @param apiVersion API版本
     */
    void setApiVersion(String apiVersion);

    String getVersion();

    /**
     * 设置请求是否需要加密
     *
     * @param needEncrypt
     */
    void setNeedEncrypt(boolean needEncrypt);

    boolean getNeedEncrypt();

    InsObject getBizModel();

    /**
     * 设置业务实体，如需使用此方法
     *
     * @param bizModel
     */
    void setBizModel(InsObject bizModel);

    /**
     * 得到当前API的响应结果类型
     *
     * @return 响应类型
     */
    Class<T> getResponseClass();
}
