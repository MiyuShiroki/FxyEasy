 package com.fxy.frame.download.core;



import com.fxy.frame.common.ViseConfig;
import com.fxy.frame.net.mode.ApiHost;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 下载Retrofit
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class DownRetrofit {

    private DownRetrofit() {
    }

    public static Retrofit getInstance() {
        return SingletonFactory.INSTANCE;
    }

    private static class SingletonFactory {
        private static final Retrofit INSTANCE = create();

        private static Retrofit create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(ViseConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.connectTimeout(ViseConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            return new Retrofit.Builder().baseUrl(ApiHost.getHost())
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }
}
