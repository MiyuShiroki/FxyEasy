package com.fxy.frame.loader;

import android.content.Context;
import android.widget.ImageView;
import com.fxy.frame.common.ViseConfig;
import java.io.File;

/**
 * 图片加载接口
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public interface ILoader {
    /**
     * 初始化
     * @param context
     */
    void init(Context context);

    /**
     * 加载网络图片
     * @param target
     * @param url
     * @param options
     */
    void loadNet(ImageView target, String url, Options options);

    /**
     * 加载本地资源
     * @param target
     * @param resId
     * @param options
     */
    void loadResource(ImageView target, int resId, Options options);

    /**
     * 加载Assets
     * @param target
     * @param assetName
     * @param options
     */
    void loadAssets(ImageView target, String assetName, Options options);

    /**
     * 加载File文件图片
     * @param target
     * @param file
     * @param options
     */
    void loadFile(ImageView target, File file, Options options);

    /**
     * 清空MemoryCache
     * @param context
     */
    void clearMemoryCache(Context context);
    /**
     * 清空DiskCache
     * @param context
     */
    void clearDiskCache(Context context);

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;//加载中的资源id
        public int loadErrorResId = RES_NONE;//加载失败的资源id

        public static Options defaultOptions() {
            return new Options(ViseConfig.IL_LOADING_RES, ViseConfig.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
