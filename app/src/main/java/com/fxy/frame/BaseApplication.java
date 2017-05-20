package com.fxy.frame;

import android.app.Application;

/**
 * Application基类
 */
public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();

    }

    /**
     * 初始化配置
     */
    protected abstract void initConfigs();
}
