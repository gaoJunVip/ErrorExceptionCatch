package com.gaojun.roomdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.lazy.library.logging.Logcat;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 首界面
 *
 * @author GaoJun
 * @time 2021/1/12 1:18 AM
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView view = new TextView(this);
        view = null;
        view.setSelected(false);
    }
}