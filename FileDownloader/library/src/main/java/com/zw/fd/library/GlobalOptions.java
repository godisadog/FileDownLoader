package com.zw.fd.library;

import com.zw.fd.library.request.RequestBuilder;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class GlobalOptions {
    private int connectTimeOut;

    private int readTimeOut;

    private int retryCount;

    private String basePath;

    private RequestBuilder.PROCESS process;

    public GlobalOptions() {
    }

    public GlobalOptions(int connectTimeOut,
                         int readTimeOut,
                         int retryCount,
                         String basePath,
                         RequestBuilder.PROCESS process) {
        this.connectTimeOut = connectTimeOut;
        this.readTimeOut = readTimeOut;
        this.retryCount = retryCount;
        this.basePath = basePath;
        this.process = process;
    }

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

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public RequestBuilder.PROCESS getProcess() {
        return process;
    }

    public void setProcess(RequestBuilder.PROCESS process) {
        this.process = process;
    }
}
