package com.yxkj.kzzx.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by RaoKui on 2017/7/13.
 */

public class GsonUtils {
    public static <T> T parseJSON(String json, Class<T> clazz) {
        Gson gson = new Gson();
        T info = gson.fromJson(json, clazz);
        return info;
    }

    /**
     * Type type = new
     * TypeToken&lt;ArrayList&lt;TypeInfo>>(){}.getType();
     * Type所在的包：java.lang.reflect
     * TypeToken所在的包：com.google.gson.reflect.TypeToken
     *
     * @param
     * @param type
     * @return
     */
    public static <T> T parseJSONArray(String jsonArr, Type type) {
        Gson gson = new Gson();
        T infos = gson.fromJson(jsonArr, type);
        return infos;
    }
}
