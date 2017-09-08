package com.zw.fd.library.request;

import com.zw.fd.library.FileDownLoadStatus;
import com.zw.fd.library.monitor.MonitorDispatcher;
import com.zw.fd.library.persistance.DBHelper;
import com.zw.fd.library.persistance.DownStatusModel;
import com.zw.fd.library.utils.Logger;
import com.zw.fd.library.utils.RequestConstance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by zhenwei on 2017/8/31.
 */

public class RequestRunnable implements Runnable {

    private final String fileName = "hello";

    private Request request;
    private RequestManager requestManager;
    private IRunningRequestPool runningRequestPool;

    private DBHelper db;
    private DownStatusModel statusModel;
    private boolean isCancel = false;

    public RequestRunnable(Request request, DBHelper db) {
        this.request = request;
        this.requestManager = requestManager;
        this.db = db;
        statusModel = db.query(request.getId());
    }

    public void setRunningRequestPool(IRunningRequestPool requestPool) {
        this.runningRequestPool = requestPool;
    }

    @Override
    public void run() {
        //check
        File file = new File(request.getStorePath(), fileName);
        if (statusModel != null && file.exists()) {
            //以实际的大小为准
            statusModel.setSofar(file.length());
            if (statusModel.getSofar() == statusModel.getTotal()) {
                statusModel.setStatus(FileDownLoadStatus.COMPLETE);
            }
        } else {
            statusModel = new DownStatusModel();
            statusModel.setUrl(request.getUrl());
            statusModel.setId(request.getId());
            statusModel.setStatus(FileDownLoadStatus.WAIT);
        }
        db.insertOrUpdateValue(statusModel);

        onStart();

        if (statusModel.getStatus() == FileDownLoadStatus.COMPLETE) {
            onComplete();
        } else {
            down();
        }
    }

    private void down() {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        IRequestConnection connection = null;
        try {
            File file = new File(request.getStorePath(), fileName);
            connection = new RealDownRequestConnection(request.getUrl());
            continueDownSetting(connection);
            //1.connect
            connection.connect();

            boolean isSupportPartial = true;
            //2.getResponseCode and etag
            int reponseCode = connection.getReponseCode();
            String etag = connection.getResponseHeader("ETag");

            if (reponseCode == HttpURLConnection.HTTP_OK) {
                //不支持断点续传
                isSupportPartial = false;
                long length = connection.getContentLength();
                statusModel.seteTag(etag);
                statusModel.setTotal(length);
                statusModel.setSofar(0L);
                Logger.d(this, "etag=" + etag + "  length=" + length + " sofar=" + statusModel.getSofar());
            }else if (reponseCode == HttpURLConnection.HTTP_PARTIAL){
                long length = connection.getContentLength();
                statusModel.seteTag(etag);
                statusModel.setTotal(statusModel.getSofar() + length);
            }else {
                onError(reponseCode, RequestConstance.RESPONSE_ERROR_MSG);
                return;
            }
            db.insertOrUpdateValue(statusModel);

            fileOutputStream = new FileOutputStream(file, isSupportPartial);
            inputStream = connection.inputStream();
            byte[] bytes = new byte[1024 * 5];
            long sofar = statusModel.getSofar();
            int length = 0;
            while ((length = inputStream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, length);
                sofar += length;
                statusModel.setSofar(sofar);
                onProgress();
                //是否取消了
                if (isCancel) {
                    onPause();
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            onError(RequestConstance.IO_ERROR_CODE, RequestConstance.IO_ERROR_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            onError(RequestConstance.IO_ERROR_CODE, RequestConstance.IO_ERROR_MSG);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (runningRequestPool != null) {
                runningRequestPool.remove(this);
            }
        }
    }

    private void continueDownSetting(IRequestConnection connection) {
        HashMap<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Range", "bytes=" + statusModel.getSofar() + "-");

        connection.addHeaders(requestHeaders);
    }

    public void cancel() {
        isCancel = true;
    }

    public int getId() {
        return request.getId();
    }

    private void onStart() {
        runningRequestPool.add(this);
        statusModel.setStatus(FileDownLoadStatus.START);
        callback();
    }

    private void onProgress() {
        statusModel.setStatus(FileDownLoadStatus.PROGRESS);
        callback();
    }

    private void onPause() {
        if (runningRequestPool != null) {
            runningRequestPool.remove(this);
        }
        statusModel.setStatus(FileDownLoadStatus.PAUSE);
        callback();
    }

    private void onComplete() {
        if (runningRequestPool != null) {
            runningRequestPool.remove(this);
        }
        statusModel.setStatus(FileDownLoadStatus.COMPLETE);
        callback();
    }

    private void onError(int errCode, String errMsg) {
        if (runningRequestPool != null) {
            runningRequestPool.remove(this);
        }

        statusModel.setStatus(FileDownLoadStatus.ERROR);
        statusModel.setErrCode(errCode);
        statusModel.setErrMsg(errMsg);
        callback();
    }

    private void callback() {
        MonitorDispatcher.getInstance().notifyChange(statusModel);
    }

}
