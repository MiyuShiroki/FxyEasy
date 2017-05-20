package com.fxy.frame.loader;

/**
 * 图片加载工厂，Glide实现加载
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class LoaderFactory {
    private static ILoader loader;

    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (LoaderFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }
}
