package com.zw.fd.library.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by zhenwei on 2017/8/31.
 */

public interface IRequestConnection {

    void connect() throws IOException;

    void addHeaders(HashMap<String, String> headers);

    InputStream inputStream() throws IOException;

    int getReponseCode() throws IOException;

    long getContentLength();

    String getResponseHeader(String key);

    void close();

}
