package com.example.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TIhuanUtil {
    /**
     * 根据正则表达式获取文本中的变量名列表
     * @param pattern
     * @param content
     * @return
     */
    public static List<String> getParams(String pattern, String content) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);

        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group(1));
        }
        return result;
    }


    public static String content(String content,Map <String, String> map){
         content = content;
        String reg = "\\{ \\{(.+?)\\} \\}";
        List<String> params = TIhuanUtil.getParams(reg, content);
        System.out.println(params);

        Map<String, String> data = map;
//        data.put("result.DATA", "张三丰");
//        data.put("withdrawMoney.DATA", "930118");
        String text = TIhuanUtil.parse(reg, content, data);
        System.out.println(text);
        return  text;
    }
    /**
     * 根据正则表达式将文本中的变量使用实际的数据替换成无变量的文本
     * @param pattern
     * @param content
     * @param data
     * @return
     */
    public static String parse(String pattern, String content, Map<String, String> data) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);

        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String key = m.group(1);
            String value = data.get(key);
            m.appendReplacement(sb, value == null ? "" : value);
        }
        m.appendTail(sb);
        return sb.toString();
    }

}
