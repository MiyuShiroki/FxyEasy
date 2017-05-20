package com.fxy.frame.download.core;


/**
 * 下载方式工厂
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class DownTypeFactory {
    private String mUrl;
    private long mFileLength;
    private String mLastModify;
    private DownHelper mDownloadHelper;

    public DownTypeFactory(DownHelper downloadHelper) {
        this.mDownloadHelper = downloadHelper;
    }

    public DownTypeFactory url(String url) {
        this.mUrl = url;
        return this;
    }

    public DownTypeFactory fileLength(long fileLength) {
        this.mFileLength = fileLength;
        return this;
    }

    public DownTypeFactory lastModify(String lastModify) {
        this.mLastModify = lastModify;
        return this;
    }

    public DownType buildNormalDownload() {
        DownType type = new DownType.NormalDownload();
        type.mUrl = this.mUrl;
        type.mFileLength = this.mFileLength;
        type.mLastModify = this.mLastModify;
        type.mDownloadHelper = this.mDownloadHelper;
        return type;
    }

    public DownType buildContinueDownload() {
        DownType type = new DownType.ContinueDownload();
        type.mUrl = this.mUrl;
        type.mFileLength = this.mFileLength;
        type.mLastModify = this.mLastModify;
        type.mDownloadHelper = this.mDownloadHelper;
        return type;
    }

    public DownType buildMultiDownload() {
        DownType type = new DownType.MultiThreadDownload();
        type.mUrl = this.mUrl;
        type.mFileLength = this.mFileLength;
        type.mLastModify = this.mLastModify;
        type.mDownloadHelper = this.mDownloadHelper;
        return type;
    }

    public DownType buildAlreadyDownload() {
        DownType type = new DownType.AlreadyDownloaded();
        type.mUrl = this.mUrl;
        type.mFileLength = this.mFileLength;
        type.mLastModify = this.mLastModify;
        type.mDownloadHelper = this.mDownloadHelper;
        return type;
    }

    public DownType buildRequestRangeNotSatisfiable() {
        DownType type = new DownType.RequestRangeNotSatisfiable();
        type.mUrl = this.mUrl;
        type.mFileLength = this.mFileLength;
        type.mLastModify = this.mLastModify;
        type.mDownloadHelper = this.mDownloadHelper;
        return type;
    }
}
