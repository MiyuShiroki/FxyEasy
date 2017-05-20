package com.fxy.frame.net.strategy;



import com.fxy.frame.net.core.ApiCache;
import com.fxy.frame.net.mode.CacheResult;

import rx.Observable;

/**
 * @Description: 缓存策略--只取缓存
 */
public class OnlyCacheStrategy<T> extends CacheStrategy<T> {
    @Override
    public <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Class<T> clazz) {
        return loadCache(apiCache, cacheKey, clazz);
    }
}
