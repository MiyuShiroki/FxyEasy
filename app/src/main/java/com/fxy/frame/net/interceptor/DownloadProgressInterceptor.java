package com.fxy.frame.net.interceptor;



import com.fxy.frame.net.body.DownloadProgressResponseBody;
import com.fxy.frame.net.callback.DCallback;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Description: 下载进度拦截
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DCallback callback;

    public DownloadProgressInterceptor(DCallback callback) {
        this.callback = callback;
        if (callback == null) {
            throw new NullPointerException("this callback must not null.");
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), callback))
                .build();
    }
}
