package com.fxy.frame.cache;

/**
 *缓存接口
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public interface ICache {
    void put(String key, Object value);

    Object get(String key);

    boolean contains(String key);

    void remove(String key);

    void clear();
}
