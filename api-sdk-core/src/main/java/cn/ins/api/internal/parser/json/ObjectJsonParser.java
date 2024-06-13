package cn.ins.api.internal.parser.json;

import cn.ins.api.InsParser;
import cn.ins.api.InsResponse;
import cn.ins.api.exception.InsApiException;
import cn.ins.api.internal.mapping.Converter;

/**
 * 单个JSON对象解释器。
 *
 */
public class ObjectJsonParser<T extends InsResponse> implements InsParser<T> {

    private Class<T> clazz;

    public ObjectJsonParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T parse(String rsp) throws InsApiException {
        Converter converter = new JsonConverter();
        return converter.toResponse(rsp, clazz);
    }

    public Class<T> getResponseClass() {
        return clazz;
    }



}
