package com.zw.fd.library.request;

/**
 * Created by zhenwei on 2017/9/7.
 */

public interface IRunningRequestPool {
    void add(RequestRunnable runnable);
    void remove(RequestRunnable runnable);
}
