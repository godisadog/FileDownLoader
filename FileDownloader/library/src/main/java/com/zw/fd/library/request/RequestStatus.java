package com.zw.fd.library.request;

/**
 * Created by zhenwei on 2017/9/4.
 */

public enum RequestStatus {

    WAIT(0),
    PREPARE(1),
    START(2),
    PROGRESS(3),
    PAUSE(4),
    COMPLETE(5),
    ERROR(-1)
    ;


    private int status;
    RequestStatus(int status){
        this.status = status;
    }
}
