package com.zw.fd.library.request;

import com.zw.fd.library.FileDownLoaderHelper;
import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.IRequestManager;
import com.zw.fd.library.persistance.DBHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenwei on 2017/9/4.
 */
public class RequestManager implements IRequestManager {
    private Options options;
    private DownLoadThreadPool threadPool;
    private IDownCallback downCallback;
    private DBHelper dbHelper;

    private final int threadCount = 1;

    private static class RequestManageHolder {
        private static RequestManager INSTANCE = new RequestManager();
    }

    public static RequestManager getInstance() {
        return RequestManageHolder.INSTANCE;
    }

    private RequestManager() {
        threadPool = new DownLoadThreadPool();
        dbHelper = new DBHelper(FileDownLoaderHelper.getAPPLICATION());
    }

    private void setRequestOptions(Options options) {
        this.options = options;
    }

    public RequestManager defaultRequestOptions(Options options) {
        setRequestOptions(options);
        return this;
    }

    void execute(RequestRunnable requestRunnable) {
        threadPool.submit(requestRunnable);
    }

    public Options getOptions() {
        return options;
    }

    @Override
    public RequestBuilder create(String url) {
        return null;
    }

    @Override
    public void start(Request request) {
        threadPool.submit(new RequestRunnable(request, dbHelper));
    }

    @Override
    public void start(int taskId) {
        threadPool.pause(taskId);
    }

    @Override
    public void start(int taskId, int groupId) {

    }

    @Override
    public void pause(int taskId) {

    }

    @Override
    public void pause(int taskId, int groupId) {

    }

    @Override
    public void pauseAll() {

    }

    @Override
    public void delete(int taskId) {

    }

    @Override
    public void delete(int taskId, int groupId) {

    }

    @Override
    public void deleteAll() {

    }
}
