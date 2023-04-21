package cn.niumxiaol.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService_bind extends Service {

    public DownloadBinder downloadBinder = new DownloadBinder();


    public MyService_bind() {
        Log.d("MyService_bind","MyService_bind:构造方法被调用");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d("MyserviceBind", "onBind: 方法被调用");
        return downloadBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate被调用");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand被调用");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MyService","MyService_bind:的onDestroy被调用");
        super.onDestroy();


    }

    public class DownloadBinder extends Binder{
        void startdownload(){
            Log.d("MyserviceBind", "startdownload: 开始下载");
        }

        int getProgress(){
            Log.d("MyserviceBind", "getprogress: 查看下载进度");
            return 0;
        }
    }
}