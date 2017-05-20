package com.fxy.frame.net.request;

import android.content.Context;

import com.fxy.frame.net.H;
import com.fxy.frame.net.callback.ACallback;
import com.fxy.frame.net.mode.CacheResult;
import com.fxy.frame.net.subscriber.ApiCallbackSubscriber;
import com.vise.utils.assist.ClassUtil;


import rx.Observable;
import rx.Subscription;

/**
 * @Description: Patch请求
 */
public class PatchRequest extends BaseRequest<PatchRequest> {
    @Override
    protected <T> Observable<T> execute(Class<T> clazz) {
        return apiService.patch(suffixUrl, params).compose(this.norTransformer(clazz));
    }

    @Override
    protected <T> Observable<CacheResult<T>> cacheExecute(Class<T> clazz) {
        return execute(clazz).compose(H.getInstance().getApiCache().transformer(cacheMode, clazz));
    }

    @Override
    protected <T> Subscription execute(Context context, ACallback<T> callback) {
        if (isLocalCache) {
            return this.cacheExecute(ClassUtil.getTClass(callback))
                    .subscribe(new ApiCallbackSubscriber(context, callback));
        }
        return this.execute(ClassUtil.getTClass(callback))
                .subscribe(new ApiCallbackSubscriber(context, callback));
    }
}
