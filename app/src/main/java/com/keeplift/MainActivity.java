package com.keeplift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.keeplift.service.LocalService;
import com.keeplift.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过1像素Activity 保活
//        KeepManager.getInstance().registerKeep(this);

        //通过前台服务 保活
//        startService(new Intent(this, ForegroundService.class));

        //双进程守护
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        KeepManager.getInstance().unregisterKeep(this);
    }

}
