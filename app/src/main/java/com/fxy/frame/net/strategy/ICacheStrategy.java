package com.fxy.frame.net.strategy;

import com.fxy.frame.net.core.ApiCache;
import com.fxy.frame.net.mode.CacheResult;

import rx.Observable;

/**
 * @Description: 缓存策略接口
 */
public interface ICacheStrategy<T> {
    <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Class<T> clazz);
}
