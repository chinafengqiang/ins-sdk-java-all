package cn.ins.api.internal.util;

import cn.ins.api.InsConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 纯字符串字典结构。
 *
 */
public class InsHashMap extends HashMap<String, String> {

    private static final long serialVersionUID = -1277791390393392630L;

    public InsHashMap() {
        super();
    }

    public InsHashMap(Map<? extends String, ? extends String> m) {
        super(m);
    }

    public String put(String key, Object value) {
        String strValue;

        if (value == null) {
            strValue = null;
        } else if (value instanceof String) {
            strValue = (String) value;
        } else if (value instanceof Integer) {
            strValue = ((Integer) value).toString();
        } else if (value instanceof Long) {
            strValue = ((Long) value).toString();
        } else if (value instanceof Float) {
            strValue = ((Float) value).toString();
        } else if (value instanceof Double) {
            strValue = ((Double) value).toString();
        } else if (value instanceof Boolean) {
            strValue = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(InsConstants.DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(InsConstants.DATE_TIMEZONE));
            strValue = format.format((Date) value);
        } else if(value instanceof List<?>){
            strValue = JSON.toJSONString(value);
        }else{
            strValue = value.toString();
        }

        return this.put(key, strValue);
    }

    public String put(String key, String value) {
        if (StringUtils.areNotEmpty(key, value)) {
            return super.put(key, value);
        } else {
            return null;
        }
    }

}
