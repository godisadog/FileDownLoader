package com.zw.fd.library.request;

/**
 * Created by zhenwei on 2017/9/5.
 */

public interface IDownLoad {
    void start(RequestBuilder requestBuilder);

    void start(Request request);

    void start(int taskId);

    void start(int taskId, int groupId);

    void pause(int taskId);

    void pause(int taskId, int groupId);

    void pauseAll();

    void delete(int taskId);

    void delete(int taskId, int groupId);

    void deleteAll();
}
