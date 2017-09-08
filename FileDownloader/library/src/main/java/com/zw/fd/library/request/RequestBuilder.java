package com.zw.fd.library.request;

import com.zw.fd.library.IRequestDownLoadListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/8/31.
 */

public class RequestBuilder {

    private String url;
    private String storePath;
    private IRequestDownLoadListener downLoadListener;
    private PROCESS process;

    public RequestBuilder() {
    }

    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder process(PROCESS process) {
        this.process = process;
        return this;
    }

    public RequestBuilder path(String storePath) {
        this.storePath = storePath;
        return this;
    }

    public RequestBuilder downLoadListener(IRequestDownLoadListener downLoadListener) {
        this.downLoadListener = downLoadListener;
        return this;
    }

    public Request build() {
        return new Request(url, storePath, downLoadListener);
    }

    public enum PROCESS {
        REMOTE_PROCESS,        //在新进程中并启动SERVICE
        LOCAL_SERVICE,         //在当前进程中启动服务
        LOCAL_APPLICATION;     //在当前进程中不启动服务
    }

}
