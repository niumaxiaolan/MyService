package cn.niumxiaol.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button service_start = null;
    private Button service_stop = null;
    private Button service_bind = null;
    private Button service_unbind = null;

    private MyService_bind.DownloadBinder downloadBinder = null;
    private ServiceConnection connection = null;
    private MyLinstener myLinstener = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service_start = findViewById(R.id.service_start);
        service_stop = findViewById(R.id.service_stop);
        service_bind = findViewById(R.id.service_bind);
        service_unbind = findViewById(R.id.service_unbind);

        myLinstener  = new MyLinstener();
        service_start.setOnClickListener(myLinstener);
        service_stop.setOnClickListener(myLinstener);
        service_bind.setOnClickListener(myLinstener);
        service_unbind.setOnClickListener(myLinstener);

        //创建绑定MyBindService的链接器
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                downloadBinder = (MyService_bind.DownloadBinder) service;
                downloadBinder.startdownload();
                int progress = downloadBinder.getProgress();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("MainActivity", "onServiceDisconnected: 方法被调用");
            }
        };
    }

    private class  MyLinstener implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.service_start:
                    Intent startintent = new Intent(MainActivity.this,MyService.class);
                    startService(startintent);
                    break;
                case R.id.service_stop:
                    Intent stopintent = new Intent(MainActivity.this,MyService.class);
                    stopService(stopintent);
                    break;
                case R.id.service_bind:
                    //绑定并启动
                    Intent bind_intent = new Intent(MainActivity.this,MyService_bind.class);
                    bindService(bind_intent,connection,BIND_AUTO_CREATE);
                    break;
                case R.id.service_unbind:
                    //解除
                    unbindService(connection);
                    break;
                default:break;
            }
        }
    }
}