package com.fxy.frame.net;

import android.content.Context;
import com.fxy.frame.net.callback.DCallback;
import com.fxy.frame.net.callback.UCallback;
import com.fxy.frame.net.config.NetGlobalConfig;
import com.fxy.frame.net.core.ApiCache;
import com.fxy.frame.net.request.DeleteRequest;
import com.fxy.frame.net.request.DownloadRequest;
import com.fxy.frame.net.request.GetRequest;
import com.fxy.frame.net.request.HeadRequest;
import com.fxy.frame.net.request.OptionsRequest;
import com.fxy.frame.net.request.PatchRequest;
import com.fxy.frame.net.request.PostRequest;
import com.fxy.frame.net.request.PutRequest;
import com.fxy.frame.net.request.UploadRequest;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscription;

/**
 * @Description: 网络请求入口
 */
public class H {
    private static H h;
    private static Context context;
    private static OkHttpClient.Builder okHttpBuilder;
    private static Retrofit.Builder retrofitBuilder;
    private static ApiCache.Builder apiCacheBuilder;
    private OkHttpClient okHttpClient;
    private ApiCache apiCache;

    private static final NetGlobalConfig NET_GLOBAL_CONFIG = NetGlobalConfig.getInstance();

    public static NetGlobalConfig CONFIG() {
        return NET_GLOBAL_CONFIG;
    }

    private H() {

    }

    public static H getInstance() {
        if (h == null) {
            synchronized (H.class) {
                if (h == null) {
                    h = new H();
                }
            }
        }
        return h;
    }

    public static void init(Context appContext) {
        if (context == null && appContext != null) {
            context = appContext.getApplicationContext();
            okHttpBuilder = new OkHttpClient.Builder();
            retrofitBuilder = new Retrofit.Builder();
            apiCacheBuilder = new ApiCache.Builder(context);
        }
    }

    public static Context getContext() {
        if (context == null) {
            throw new IllegalStateException("Please call H.init(this) in Application to initialize!");
        }
        return context;
    }

    public static OkHttpClient.Builder getOkHttpBuilder() {
        if (okHttpBuilder == null) {
            throw new IllegalStateException("Please call H.init(this) in Application to initialize!");
        }
        return okHttpBuilder;
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        if (retrofitBuilder == null) {
            throw new IllegalStateException("Please call H.init(this) in Application to initialize!");
        }
        return retrofitBuilder;
    }

    public static ApiCache.Builder getApiCacheBuilder() {
        if (apiCacheBuilder == null) {
            throw new IllegalStateException("Please call H.init(this) in Application to initialize!");
        }
        return apiCacheBuilder;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = getOkHttpBuilder().build();
        }
        return okHttpClient;
    }

    public ApiCache getApiCache() {
        if (apiCache == null || apiCache.isClosed()) {
            apiCache = getApiCacheBuilder().build();
        }
        return apiCache;
    }

    /**
     * GET请求
     * @return
     */
    public static GetRequest GET() {
        return new GetRequest();
    }

    /**
     * POST请求
     * @return
     */
    public static PostRequest POST() {
        return new PostRequest();
    }

    /**
     * HEAD请求
     * @return
     */
    public static HeadRequest HEAD() {
        return new HeadRequest();
    }

    /**
     * PUT请求
     * @return
     */
    public static PutRequest PUT() {
        return new PutRequest();
    }

    /**
     * PATCH请求
     * @return
     */
    public static PatchRequest PATCH() {
        return new PatchRequest();
    }

    /**
     * OPTIONS请求
     * @return
     */
    public static OptionsRequest OPTIONS() {
        return new OptionsRequest();
    }

    /**
     * DELETE请求
     * @return
     */
    public static DeleteRequest DELETE() {
        return new DeleteRequest();
    }

    /**
     * 上传
     * @return
     */
    public static UploadRequest UPLOAD() {
        return new UploadRequest();
    }

    /**
     * 上传（包含上传进度）
     * @param callback 上传进度回调
     * @return
     */
    public static UploadRequest UPLOAD(UCallback callback) {
        return new UploadRequest(callback);
    }

    /**
     * 下载
     * @return
     */
    public static DownloadRequest DOWNLOAD() {
        return new DownloadRequest();
    }

    /**
     * 下载（包含下载进度）
     * @param callback 下载进度回调
     * @return
     */
    public static DownloadRequest DOWNLOAD(DCallback callback) {
        return new DownloadRequest(callback);
    }

    /**
     * 根据Tag取消请求
     */
    public void cancelTag(Object tag) {
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 取消所有请求请求
     */
    public void cancelAll() {
        for (Call call : getOkHttpClient().dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : getOkHttpClient().dispatcher().runningCalls()) {
            call.cancel();
        }
    }

    /**
     * 清除对应Key的缓存
     * @param key
     */
    public void removeCache(String key) {
        getApiCache().remove(key);
    }

    /**
     * 清楚所有缓存并关闭缓存
     * @return
     */
    public Subscription clearCache() {
        return getApiCache().clear();
    }

}
