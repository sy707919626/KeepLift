package com.keeplift.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2019/3/20.
 */

public class SinglePixelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.START|Gravity.TOP);

        WindowManager.LayoutParams attr =  window.getAttributes();

        attr.width = 1;
        attr.height = 1;

        attr.x = 0;
        attr.y = 0;

        window.setAttributes(attr);

        //弱引用
        KeepManager.getInstance().setKeep(this);
    }
}
