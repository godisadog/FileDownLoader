package com.zw.fd.library.monitor;

import com.zw.fd.library.persistance.DownStatusModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class MonitorDispatcher {

    private static class MonitorDispatcherHolder{
        private static MonitorDispatcher instance = new MonitorDispatcher();
    }
    public static MonitorDispatcher getInstance(){
        return MonitorDispatcherHolder.instance;
    }

    private List<ISubsciber> subsciberList;
    private MonitorDispatcher() {
        subsciberList = new ArrayList<>();
    }

    public void subscribe(ISubsciber subsciber) {
        subsciberList.add(subsciber);
    }

    public void notifyChange(DownStatusModel msg) {
        for (ISubsciber subsciber : subsciberList){
            subsciber.notifyChange(msg);
        }
    }

}
