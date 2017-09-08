package com.zw.fd.library;

import android.content.Context;

import com.zw.fd.library.request.RequestBuilder;

/**
 * Created by zhenwei on 2017/8/31.
 */

public class FileDownloader {

    private static final int THREAD_COUNT = 1;

    public static final int CONNECT_TIME_OUT = 30 * 1000;
    public static final int READ_TIME_OUT = 30 * 1000;
    public static final int RETYY_COUNT = 3;

    public static GlobalOptions mGlobalOptions = new GlobalOptions(CONNECT_TIME_OUT,
            READ_TIME_OUT,
            RETYY_COUNT,
            null,
            RequestBuilder.PROCESS.REMOTE_PROCESS);

    private static FileDownloader fileDownloader;

    public static FileDownloader get() {
        if (fileDownloader == null) {
            synchronized (FileDownloader.class) {
                if (fileDownloader == null) {
                    fileDownloader = new FileDownloader();
                }
            }
        }
        return fileDownloader;
    }

    public static void init(Context context, GlobalOptions globalOptions) {
        FileDownLoaderHelper.holdApplication(context.getApplicationContext());
        FileDownLoaderHelper.holdGlobalOptions(globalOptions);
        mGlobalOptions = globalOptions;
    }

    public static GlobalOptions getGlobalOptions(){
        return mGlobalOptions;
    }

    private Context mContext;

    FileDownloader() {
    }

    public RequestBuilder create() {
        return new RequestBuilder();
    }

    public void start(String url) {

    }

    public void start(int taskId) {

    }

    public void start(int taskId, int groupId) {

    }

    public void startAll() {

    }

    public void startAll(int groupId) {

    }

    public void pause(String url){
        RequestDispatcher.getInstance().pause(url);
    }

    public void pause(int taskId) {

    }

    public void pause(int taskId, int groupId) {

    }

    public void delete(int taskId) {

    }

    public void delete(int taskId, int groupId) {

    }

}
