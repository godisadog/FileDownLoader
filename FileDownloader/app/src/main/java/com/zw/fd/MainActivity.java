package com.zw.fd;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zw.fd.library.FileDownloader;
import com.zw.fd.library.IDownCallback;
import com.zw.fd.library.IDownInterface;
import com.zw.fd.library.IRequestDownLoadListener;
import com.zw.fd.library.service.DownLoadService;

public class MainActivity extends AppCompatActivity {

    private String LIULISHUO_APK_URL = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
    private String path;
    IDownInterface downInterface;

    private boolean isStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = getExternalCacheDir().getAbsolutePath();

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart) {
                    isStart = false;
                    ((Button)findViewById(R.id.start)).setText("开始");
                    stop();
                }else {
                    isStart = true;
                    ((Button)findViewById(R.id.start)).setText("暂停");
                    start();
                }
            }
        });


    }

    private void start(){
        FileDownloader.get().create().url(LIULISHUO_APK_URL).path(path).downLoadListener(new IRequestDownLoadListener() {
            @Override
            public void onWait() {
                Log.d("zhenwei", "onWait =");
            }

            @Override
            public void prepare() {
                Log.d("zhenwei", "prepare =");
            }

            @Override
            public void onStart() {
                Log.d("zhenwei", "onStart =");
            }

            @Override
            public void onProgress(long progress) {
                Log.d("zhenwei", "progress =" + progress);
                mHandler.obtainMessage(0, progress).sendToTarget();
            }

            @Override
            public void onSpeed(long bytePerSecond) {
                Log.d("zhenwei", "onSpeed =");
            }

            @Override
            public void onPause() {
                Log.d("zhenwei", "onPause =");
            }

            @Override
            public void onComplete() {
                Log.d("zhenwei", "onComplete =");
            }

            @Override
            public void onFail() {
                Log.d("zhenwei", "onFail =");
            }
        }).build().start();
    }

    private void stop(){
        FileDownloader.get().pause(0);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ((TextView)findViewById(R.id.onProgress)).setText("" + (long)msg.obj);
        }
    };


}
