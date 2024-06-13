package cn.ins.api.internal.sign;

import cn.ins.api.internal.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author JQ.Feng
 * @title: InsSignature
 * @desc: TODO
 * @date 2024/6/7 20:23
 */
public class InsSignature {
    public static String getSignContent(Map<String, Object> sortedParams) {
        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value.toString())) {
                content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
                index++;
            }
        }
        return content.toString();
    }
}
