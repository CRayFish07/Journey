package com.shakool.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by geekgao on 16-4-26.
 * 获取配置参数
 */
public class PropertiesUtils {
    private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(PropertiesUtils.class.getResourceAsStream("/properties/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getVoicePackage() {
        try {
            return new String(properties.getProperty("voicepackage").getBytes("iso-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("解析配置参数编码出错");
        }
        return "";
    }
}
