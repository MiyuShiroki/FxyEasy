package com.fxy.frame.download.mode;


/**
 * 下载范围，包含开始及结束长度信息
 * @author MiyuShiroki
 * @date  2017/5/18
 */
public class DownRange {
    private long start;
    private long end;

    public DownRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public DownRange setStart(long start) {
        this.start = start;
        return this;
    }

    public long getEnd() {
        return end;
    }

    public DownRange setEnd(long end) {
        this.end = end;
        return this;
    }

    @Override
    public String toString() {
        return "DownRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
