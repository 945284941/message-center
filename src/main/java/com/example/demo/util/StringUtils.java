package com.example.demo.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lihai
 */
public class StringUtils {

    /**
     * 驼峰转下划线
     * @param source
     * @return
     */
    public static String HumpToUnderline(String source){
        String regexStr = "[A-Z]";
        Matcher matcher = Pattern.compile(regexStr).matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, "_" + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }

    /**
     * 获取数据所在数组下标  不存在则返回 -1
     * @param arr
     * @param str
     * @return
     */
    public static int getIndex(String[] arr, String str){
        int k = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(str)){
                k = i;
            }
        }
        return k;
    }

    public static int getStrSite(String str, String param, Integer times) {
        Pattern pattern = Pattern.compile(param);
        Matcher findMatcher = pattern.matcher(str);
        int number = 0;
        while (findMatcher.find()) {
            number++;
            if (number == times) {//当 param 第times次出现时停止
                break;
            }
        }
        return findMatcher.start();//param 第times次出现的位置
    }

    /**
     * 随机生产 factor 位的数字，最大不超过 19位，因为long的最大值为19位
     *
     * @param factor
     * @return
     */
    public static Long randomNum(int factor) {
        return new Double((Math.random() + 1) * Math.pow(10, factor - 1)).longValue();
    }


    /**
     * @return java.lang.String
     * @Title: getTimestampId
     * @Description: 获取13位当前时间戳 用做id
     * @author lihaichao
     * @date 2018/12/23 21:35
     **/
    public static String getTimestampId() {
        return String.valueOf(new Date().getTime());
    }


    // 获取纳秒时间戳
    public static String getNanotime() {
        return String.valueOf(System.nanoTime());
    }


    /**
     * StringUtils工具类方法
     * 获取一定长度的随机字符串，范围0-9，a-z
     *
     * @param length：指定字符串长度
     * @return 一定长度的随机字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 补零
     * @param size 数据总长度
     * @param num 数值
     * @return
     */
    public static String repairZero(int size, int num){
        return String.format("%0" + size + "d", num);
    }




    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */

    /**
     * list转String
     *
     * @param mList
     * @return
     */
    public static String listToString(List<?> mList) {
        String convertedListStr = "";
        if (null != mList && mList.size() > 0) {
            String[] mListArray = mList.toArray(new String[mList.size()]);
            for (int i = 0; i < mListArray.length; i++) {
                if (i < mListArray.length - 1) {
                    convertedListStr += mListArray[i] + ",";
                } else {
                    convertedListStr += mListArray[i];
                }
            }
            return convertedListStr;
        } else {
            return "List is null!!!";
        }
    }


    /**
     * 　　* 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 　　* @param params 需要排序并参与字符拼接的参数组
     * 　　* @return 拼接后字符串
     * 　　* @throws UnsupportedEncodingException
     *
     */
    public static String createLinkStringByGet(Map<String, Object> params) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder preStr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));
            value = URLEncoder.encode(value, "UTF-8");
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                preStr.append(key).append("=").append(value);
            } else {
                preStr.append(key).append("=").append(value).append("&");
            }
        }
        return preStr.toString();
    }
}
