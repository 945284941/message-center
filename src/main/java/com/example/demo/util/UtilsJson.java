package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Title: ${file_name}
 * @Project: smwx
 * @Package: com.ctrl.smzl.util
 * @author: 黑猫警长 zzb
 * @Description:
 * @version: 1.0
 * @DATE: 下午5:13 2018/1/8
 */
public class UtilsJson {


    /**
     * 把Json字符串转换成Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Set<String> keys = jsonObject.keySet();
        String key;
        Object value;
        Map<String, Object> valueMap = new HashMap<String, Object>();
        for (String e : keys) {
            key = e;
            value = jsonObject.get(e);
            valueMap.put(key, value);
        }
        return valueMap;
    }


}
