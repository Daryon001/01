package com.hixtrip.sample.infra.util;

public class JsonUtil {



    public static <T> T toBean(String content, Class<T> clz) {
        // todo 模拟json字符串转化为　bean
        return (T) clz.getClass();
    }

    public static String toJson(Object o) {
        // todo 模拟bean转化为　json字符串
        return "";
    }

}
