package com.zw.fd.library.request;

/**
 * Created by zhenwei on 2017/8/31.
 */

public class Options {
    private int connectTimeOut;

    private int readTimeOut;

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }
}
