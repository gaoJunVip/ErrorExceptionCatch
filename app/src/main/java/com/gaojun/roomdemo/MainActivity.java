package com.gaojun.roomdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 首界面
 *
 * @author GaoJun
 * @time 2021/1/12 1:18 AM
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvClick = findViewById(R.id.tv_click_save_catch_log);
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvClick = null;
                tvClick.setSelected(false);
            }
        });
    }
}