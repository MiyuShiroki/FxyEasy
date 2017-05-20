package com.fxy.frame.net.request;

import android.content.Context;

import com.fxy.frame.net.callback.ACallback;
import com.fxy.frame.net.callback.DCallback;
import com.fxy.frame.net.mode.CacheResult;
import com.fxy.frame.net.subscriber.ApiCallbackSubscriber;
import com.vise.utils.assist.ClassUtil;


import rx.Observable;
import rx.Subscription;

/**
 * @Description: 下载请求
 */
public class DownloadRequest extends BaseRequest<DownloadRequest> {

    public DownloadRequest() {
    }

    public DownloadRequest(DCallback callback) {
        this.downCallback = callback;
    }

    @Override
    protected <T> Observable<T> execute(Class<T> clazz) {
        return apiService.downFile(suffixUrl, params).compose(this.norTransformer(clazz));
    }

    @Override
    protected <T> Observable<CacheResult<T>> cacheExecute(Class<T> clazz) {
        return null;
    }

    @Override
    protected <T> Subscription execute(Context context, ACallback<T> callback) {
        return this.execute(ClassUtil.getTClass(callback))
                .subscribe(new ApiCallbackSubscriber(context, callback));
    }
}
