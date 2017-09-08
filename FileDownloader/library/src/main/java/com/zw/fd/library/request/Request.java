package com.zw.fd.library.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.zw.fd.library.IRequestDownLoadListener;
import com.zw.fd.library.RequestDispatcher;

/**
 * Created by zhenwei on 2017/9/5.
 */

public class Request implements IRequest, Parcelable{

    private int id;
    private String url;
    private String storePath;
    private IRequestDownLoadListener downLoadListener;

    public Request(String url,
                   String storePath,
                   IRequestDownLoadListener downLoadListener) {
        this.url = url;
        this.storePath = storePath;
        this.downLoadListener = downLoadListener;
    }

    public Request(Parcel source){
        this.url = source.readString();
        this.storePath = source.readString();
        this.downLoadListener = null;
    }

    @Override
    public void start() {
        RequestDispatcher.getInstance().start(this);
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getStorePath() {
        return storePath;
    }

    public IRequestDownLoadListener getDownLoadListener() {
        return downLoadListener;
    }

    //parcel
    public static Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel source) {
            return new Request(source);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(storePath);
    }
}
