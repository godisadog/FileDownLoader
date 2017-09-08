package com.zw.fd.library.persistance;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhenwei on 2017/9/5.
 */

public class DownStatusModel implements Parcelable {
    public static final String TABLE_NAME = "down_status";

    public static final String ID = "_id";
    public static final String URL = "url";
    public static final String FILE_PATH = "file_path";
    public static final String STATUS = "status";
    public static final String SOFAR = "sofar";
    public static final String TOTAL = "total";
    public static final String ETAG = "etag";
    public static final String ERR_CODE = "err_code";
    public static final String ERR_MSG = "err_msg";

    private int id;
    private String url;
    private String filePath;
    private int status;
    private long sofar;
    private long total;
    private String eTag;
    private int errCode;
    private String errMsg;

    public DownStatusModel(){

    }

    public DownStatusModel(Parcel in) {
        readFromParcel(in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getSofar() {
        return sofar;
    }

    public void setSofar(long sofar) {
        this.sofar = sofar;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "DownStatusModel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", filePath='" + filePath + '\'' +
                ", status=" + status +
                ", sofar=" + sofar +
                ", total=" + total +
                ", eTag='" + eTag + '\'' +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(filePath);
        dest.writeInt(status);
        dest.writeLong(sofar);
        dest.writeLong(total);
        dest.writeString(eTag);
        dest.writeString(errMsg);
    }

    public void readFromParcel(Parcel in){
        id = in.readInt();
        url = in.readString();
        filePath = in.readString();
        status = in.readInt();
        sofar = in.readLong();
        total = in.readLong();
        eTag = in.readString();
        errMsg = in.readString();
    }

    public static final Creator<DownStatusModel> CREATOR = new Creator<DownStatusModel>() {
        @Override
        public DownStatusModel createFromParcel(Parcel source) {
            return new DownStatusModel(source);
        }

        @Override
        public DownStatusModel[] newArray(int size) {
            return new DownStatusModel[size];
        }
    };

}
