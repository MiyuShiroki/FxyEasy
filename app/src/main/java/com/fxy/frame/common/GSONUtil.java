package com.fxy.frame.common;

import com.google.gson.Gson;
/**
 * Gson单例操作
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class GSONUtil {
    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}
