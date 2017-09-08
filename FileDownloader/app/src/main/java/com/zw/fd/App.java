package com.zw.fd;

import android.app.Application;

import com.zw.fd.library.FileDownloader;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.init(this, FileDownloader.mGlobalOptions);
    }
}
