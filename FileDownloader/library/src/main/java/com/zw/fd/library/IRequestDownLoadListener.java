package com.zw.fd.library;

/**
 * Created by zhenwei on 2017/9/4.
 */

public interface IRequestDownLoadListener {
    void onWait();

    void prepare();

    void onStart();

    void onProgress(long progress);

    void onSpeed(long bytePerSecond);

    void onPause();

    void onComplete();

    void onFail();

}
