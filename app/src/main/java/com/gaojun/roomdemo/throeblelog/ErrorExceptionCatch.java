package com.gaojun.roomdemo.throeblelog;

import android.app.Application;

import com.lazy.library.logging.Logcat;

import androidx.annotation.NonNull;


/**
 * 拦截系统的崩溃操作,由我们自己进行处理
 *
 * @author GaoJun
 * @time 2021/1/27 11:23
 */
public class ErrorExceptionCatch implements Thread.UncaughtExceptionHandler {

    private static ErrorExceptionCatch exceptionCatch;

    private ErrorExceptionHandle errHandle;

    public static ErrorExceptionCatch initialize(Application mContext) {
        if (null == exceptionCatch) {
            synchronized (ErrorExceptionCatch.class) {
                if (null == exceptionCatch) {
                    exceptionCatch = new ErrorExceptionCatch(mContext);
                }
            }
        }
        return exceptionCatch;
    }

    // 设置本程序的异常崩溃由此类处理
    private ErrorExceptionCatch(Application context) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        errHandle = new ErrorExceptionHandle(context, uncaughtExceptionHandler);
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        Logcat.e(throwable);
        errHandle.excute(thread, throwable);
    }
}
