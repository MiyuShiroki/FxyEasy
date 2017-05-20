package com.fxy.frame.net.callback;

/**
 * @Description: 下载进度回调
 */
public interface DCallback {
    void onProgress(long currentLength, long totalLength);
    void onComplete();
    void onFail(int errCode, String errMsg);
}
