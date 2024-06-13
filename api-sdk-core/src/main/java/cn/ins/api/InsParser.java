package cn.ins.api;

import cn.ins.api.exception.InsApiException;

/**
 * 响应解释器接口。响应格式可以是JSON, XML等等。
 *
 */
public interface InsParser<T extends InsResponse> {

    /**
     * 把响应字符串解释成相应的领域对象。
     *
     * @param rsp 响应字符串
     * @return 领域对象
     */
    T parse(String rsp) throws InsApiException;

    /**
     * 获取响应类类型。
     */
    Class<T> getResponseClass() throws InsApiException;


}
