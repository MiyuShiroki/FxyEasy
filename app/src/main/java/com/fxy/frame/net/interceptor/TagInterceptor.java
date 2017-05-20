package com.fxy.frame.net.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description: 设置请求标签拦截
 */
public class TagInterceptor implements Interceptor {

    private Object tag;

    public TagInterceptor(Object tag) {
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.tag(tag);
        return chain.proceed(builder.build());
    }
}
