package com.nowcoder.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

/**
 *  * Created by wenda on 2018/2/16.
 */
public class WendaUtil {
    private static final Logger logger = LoggerFactory.getLogger(WendaUtil.class);

    public static int ANONYMOUS_USERID = 3;
    public static int SYSTEM_USERID = 4;

    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.put(entry.getKey(), entry.getValue());
        }
        return json.toJSONString();
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("生成MD5失败", e);
            return null;
        }
    }
    public static void main(String []args)
    {
//        Date start=new Date();
//        String []words={"草尼玛","傻逼","嫖娼", "赌博", "情赌", "色情", "尼玛", "中国共产党", "习近平", "毛泽东", "共产党", "革命", "草你妈", "艹你妈", "操你妈",};
//        String str="草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁 草尼玛 大傻逼 习近平 中国共产党万岁";
//        for(String word : words) {
//            str = str.replaceAll(word,StrforLen('*',word.length()));
//        }
//        System.out.println(str);
//        Date end=new Date();
//        System.out.println("time:"+(end.getTime()-start.getTime())+" ms");
    }
    public static String StrforLen(Character c,int len)
    {
        StringBuilder result=new StringBuilder();
        for(int i=0;i<len;i++)
        {
            result.append(c);
        }
        return  result.toString();
    }

    /**
     *
     * @param str
     */
    public static String Filter(String str)
    {//过滤敏感词
        Date start=new Date();
        String []words={"草尼玛","傻逼","嫖娼", "赌博", "情赌", "色情", "尼玛", "中国共产党", "习近平", "毛泽东", "共产党", "革命", "草你妈", "艹你妈", "操你妈",};
        for(String word : words) {
            str = str.replaceAll(word,StrforLen('*',word.length()));
        }
        System.out.println(str);
        Date end=new Date();
        System.out.println("time:"+(end.getTime()-start.getTime())+" ms");
        return str;
    }
}
