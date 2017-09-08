package com.zw.fd.library;

import com.zw.fd.library.request.Request;
import com.zw.fd.library.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenwei on 2017/9/6.
 */

public class RequestList {
    private List<Request> pendingList;

    private List<Request> runningList;

    public void addPendingRequest(Request request){
        initPendingList();
        if (request != null) {
            pendingList.add(request);
        }
    }

    private void initPendingList(){
        if (pendingList  == null) {
            pendingList = new ArrayList<>();
        }
    }

    public void clearPendingList(){
        if (CollectionUtils.isNotEmpty(pendingList)) {
            pendingList.clear();
        }
    }

    public List<Request> getPendingList() {
        return pendingList;
    }
}
