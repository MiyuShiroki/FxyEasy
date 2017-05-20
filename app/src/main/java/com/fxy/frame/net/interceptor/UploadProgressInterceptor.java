package com.fxy.frame.net.interceptor;



import com.fxy.frame.net.body.UploadProgressRequestBody;
import com.fxy.frame.net.callback.UCallback;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description: 上传进度拦截
 */
public class UploadProgressInterceptor implements Interceptor {

    private UCallback callback;

    public UploadProgressInterceptor(UCallback callback) {
        this.callback = callback;
        if (callback == null) {
            throw new NullPointerException("this callback must not null.");
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.body() == null) {
            return chain.proceed(originalRequest);
        }
        Request progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(),
                        new UploadProgressRequestBody(originalRequest.body(), callback))
                .build();
        return chain.proceed(progressRequest);
    }
}
