package com.example.mgtserver.utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author Hexrt
 * @description 字符串工具
 * @create 2022-01-17 23:59
 **/
public class StringUtil {

    /**
     * 日期格式化
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm ss");

    /**
     * 通过UUID获取指定长度的随机字符串
     * @param length 指定长度
     * @return 随机字符串
     */
    public static String getUuidKey(int length) {
        length = Math.max(1, Math.min(length, 32));
        return getUuidKey().substring(0, length);
    }

    /**
     * 获取通过UUID生成的随机字符串
     * @return 字符串
     */
    public static String getUuidKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取固定长度为13的随机的数字字符串
     * @return 字符串
     */
    public static String getNumberKey(){
        return getNumberKey(13);
    }

    /**
     * 获取指定长度的随机的数字字符串<br>
     * 长度限制 1 ~ 18<br>
     * 根据时间生成
     * @param length 长度
     * @return 字符串
     */
    public static String getNumberKey(int length){
        length = Math.max(1, Math.min(length, 18));
        Long time = System.currentTimeMillis();
        return String.format("%018d", time).substring(18-length, 18);
    }

    /**
     * 格式化时间
     * @param gmt 时间戳
     * @return 格式化时间
     */
    public static String gmtFormat(Long gmt) {
        //todo 线程不安全
        return SIMPLE_DATE_FORMAT.format(gmt);
    }

}
