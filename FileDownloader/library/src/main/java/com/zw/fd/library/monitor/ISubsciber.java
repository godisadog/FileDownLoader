package com.zw.fd.library.monitor;

import com.zw.fd.library.persistance.DownStatusModel;

/**
 * Created by zhenwei on 2017/9/6.
 */

public interface ISubsciber {
    void notifyChange(DownStatusModel msg);
}
