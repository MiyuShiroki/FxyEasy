package com.fxy.frame.net.strategy;

import com.fxy.frame.net.core.ApiCache;
import com.fxy.frame.net.mode.CacheResult;
import rx.Observable;
import rx.functions.Func1;

/**
 * @Description: 缓存策略--缓存和网络
 */
public class CacheAndRemoteStrategy<T> extends CacheStrategy<T> {
    @Override
    public <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, final Class<T> clazz) {
        Observable<CacheResult<T>> cache = loadCache(apiCache, cacheKey, clazz);
        final Observable<CacheResult<T>> remote = loadRemote(apiCache, cacheKey, source);
        return Observable.concat(cache, remote).filter(new Func1<CacheResult<T>, Boolean>() {
            @Override
            public Boolean call(CacheResult<T> result) {
                return result.getCacheData() != null;
            }
        });
    }
}
