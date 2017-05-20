package com.fxy.frame.net.body;



import com.fxy.frame.net.callback.DCallback;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

/**
 * @Description: 下载进度响应实体类
 *
 */
public class DownloadProgressResponseBody extends ResponseBody {
    private final ResponseBody responseBody;
    private final DCallback callback;
    private BufferedSource bufferedSource;

    public DownloadProgressResponseBody(ResponseBody responseBody, DCallback callback) {
        this.responseBody = responseBody;
        this.callback = callback;
        if (responseBody == null || callback == null) {
            throw new NullPointerException("this responseBody and callback must not null.");
        }
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (bytesRead == -1) {
                    callback.onComplete();
                } else {
                    callback.onProgress(totalBytesRead, responseBody.contentLength());
                }
                return bytesRead;
            }

            @Override
            public Timeout timeout() {
                callback.onFail(-1, "Download TimeOut!");
                return super.timeout();
            }
        };
    }
}
