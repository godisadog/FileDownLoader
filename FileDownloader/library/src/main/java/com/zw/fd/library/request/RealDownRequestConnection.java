package com.zw.fd.library.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by zhenwei on 2017/8/31.
 */

public class RealDownRequestConnection implements IRequestConnection {

    private URL url;
    private Options options;

    private URLConnection urlConnection;

    public RealDownRequestConnection(String url) throws IOException {
        this(new URL(url), null);
    }

    public RealDownRequestConnection(URL url, Options options) throws IOException {
        this.url = url;
        this.options = options;

        urlConnection = url.openConnection();

        if (options != null) {
            urlConnection.setConnectTimeout(options.getConnectTimeOut());
            urlConnection.setReadTimeout(options.getReadTimeOut());
        }
    }

    @Override
    public void connect() throws IOException {
        urlConnection.connect();
    }

    @Override
    public void addHeaders(HashMap<String, String> headers) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                String value = headers.get(key);
                urlConnection.addRequestProperty(key, value);
            }
        }
    }

    @Override
    public int getReponseCode() throws IOException {
        if (urlConnection instanceof HttpURLConnection) {
            return ((HttpURLConnection) urlConnection).getResponseCode();
        }
        return 0;
    }

    @Override
    public long getContentLength() {
        return urlConnection.getContentLength();
    }

    @Override
    public InputStream inputStream() throws IOException {
        return urlConnection.getInputStream();
    }

    @Override
    public String getResponseHeader(String key) {
        return urlConnection.getHeaderField(key);
    }

    @Override
    public void close() {
//        urlConnection.
    }
}
