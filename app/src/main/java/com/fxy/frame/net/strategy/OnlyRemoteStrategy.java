package com.fxy.frame.net.strategy;


import com.fxy.frame.net.core.ApiCache;
import com.fxy.frame.net.mode.CacheResult;
import rx.Observable;

/**
 * @Description: 缓存策略--只取网络
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 16/12/31 14:30.
 */
public class OnlyRemoteStrategy<T> extends CacheStrategy<T> {
    @Override
    public <T> Observable<CacheResult<T>> execute(ApiCache apiCache, String cacheKey, Observable<T> source, Class<T> clazz) {
        return loadRemote(apiCache, cacheKey, source);
    }
}
