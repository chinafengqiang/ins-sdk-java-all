package cn.ins.api.internal.parser.json;

import cn.ins.api.InsConstants;
import cn.ins.api.InsResponse;
import cn.ins.api.exception.InsApiException;
import cn.ins.api.internal.mapping.Converter;
import cn.ins.api.internal.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON格式转换器。
 *
 */
public class JsonConverter implements Converter {

    public <T extends InsResponse> T toResponse(String rsp, Class<T> clazz)
            throws InsApiException {

        JSONObject jsonObject = JSON.parseObject(rsp);
        if (jsonObject == null) {
            throw new InsApiException("response error");
        }
        Integer code = jsonObject.getInteger(InsConstants.API_RESULT_CODE);
        String msg = jsonObject.getString(InsConstants.API_RESULT_MSG);
        String reqId = jsonObject.getString(InsConstants.API_RESULT_REQUEST_ID);

        if(code != null &&  code == InsConstants.API_RESULT_SUCCESS){
            String data = jsonObject.getString(InsConstants.API_RESULT_DATA);
            if(StringUtils.areNotEmpty(data)){
                T result = JSON.parseObject(data, clazz);
                setResult(result,code,msg,reqId);
                return result;
            }
        }
        return JSON.parseObject(rsp, clazz);
    }


    private void setResult(InsResponse response,int code,String msg,String reqId) {
        response.setCode(code);
        response.setMsg(msg);
        response.setRequestId(reqId);
    }
}
