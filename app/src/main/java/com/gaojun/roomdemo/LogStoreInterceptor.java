package com.gaojun.roomdemo;

import android.util.Log;

import com.drake.logcat.Chain;
import com.drake.logcat.LogCat;
import com.drake.logcat.LogInterceptor;

/**
 * @author azhe
 * @time 2021/1/26 17:41
 */
public class LogStoreInterceptor implements LogInterceptor {
    @Override
    public void intercept(Chain chain) {
        if (new Throwable().equals(chain.getStack())) {
            Log.e("--->", "tag=" + chain.getTag() + ",stack =" + chain.getStack());
        }
        String message = chain.getMessage();
        Log.e("--->", "message =" + message + ",stack =" + chain.getStack());
    }
}
