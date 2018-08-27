package com.qiming.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author liuqiming
 * @date 2018/8/27
 * @email liuqiminggood@gmail.com
 * @Description
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
