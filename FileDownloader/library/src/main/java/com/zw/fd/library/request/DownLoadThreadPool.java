package com.zw.fd.library.request;

import android.util.SparseArray;

import com.zw.fd.library.FileDownLoaderHelper;
import com.zw.fd.library.R;
import com.zw.fd.library.monitor.MonitorManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenwei on 2017/9/7.
 */

public class DownLoadThreadPool implements IRunningRequestPool {
    //TODO 自己设置
    private final int THREAD_COUNT = 1;

    private ExecutorService threadPool;
    private LinkedBlockingDeque<Runnable> linkedBlockingQueue;
    private SparseArray<RequestRunnable> runningRequestList;

    public DownLoadThreadPool() {
        linkedBlockingQueue = new LinkedBlockingDeque<>();
        threadPool = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT,
                0L, TimeUnit.MILLISECONDS,
                linkedBlockingQueue);

        runningRequestList = new SparseArray<>();
    }

    public void submit(RequestRunnable requestRunnable) {
        if (isRunning(requestRunnable)) {
            MonitorManager.getInstance().onWarning(requestRunnable.getId(),
                    FileDownLoaderHelper.getAPPLICATION().getString(R.string.warn_is_running));
            return;
        }
        if (bringToHead(requestRunnable)) {
            return;
        }

        requestRunnable.setRunningRequestPool(this);
        threadPool.submit(requestRunnable);
    }

    private boolean isRunning(RequestRunnable requestRunnable){
        if (runningRequestList.get(requestRunnable.getId()) != null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 将某个runnable移动至队列头部
     * @param requestRunnable
     * @return true 则存在次元素且移动至头部，false不存在次元素
     */
    private boolean bringToHead(RequestRunnable requestRunnable){
        Runnable first = linkedBlockingQueue.peek();
        if (first != null && ((RequestRunnable)first).getId() == requestRunnable.getId())
            return true;

        for (Runnable runnable : linkedBlockingQueue) {
            if (runnable instanceof RequestRunnable
                    && ((RequestRunnable) runnable).getId() == requestRunnable.getId()) {
                linkedBlockingQueue.remove(runnable);
                linkedBlockingQueue.addFirst(runnable);
                return true;
            }
        }
        return false;
    }


    public void pause(int id) {
        RequestRunnable runnable = runningRequestList.get(id);
        if (runnable != null) {
            runnable.cancel();
        }
    }

    @Override
    public void add(RequestRunnable runnable) {
        runningRequestList.put(runnable.getId(), runnable);
    }

    @Override
    public void remove(RequestRunnable runnable) {
        runningRequestList.remove(runnable.getId());
    }
}
