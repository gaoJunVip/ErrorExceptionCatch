package com.gaojun.roomdemo;

import com.gaojun.roomdemo.throeblelog.ErrorExceptionCatch;
import com.lazy.library.logging.Builder;
import com.lazy.library.logging.Logcat;

import androidx.multidex.MultiDexApplication;

/**
 * @author azhe
 * @time 2021/1/26 10:52
 */
public class ModelApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Logcat
        initLogCat();
        // 初始化本地自定义日志捕捉
        ErrorExceptionCatch.initialize(this);
    }

    private void initLogCat() {
        Builder builder = Logcat.newBuilder();
        // 设置Log 保存的文件夹
        String downloadPath = getExternalFilesDir("roomDemo").getAbsolutePath();
        builder.logSavePath(downloadPath);
        // 设置输出日志等级
        if (BuildConfig.DEBUG) {
            builder.logCatLogLevel(Logcat.SHOW_ALL_LOG);
            // 设置输出文件日志等级
            builder.fileLogLevel(Logcat.SHOW_ALL_LOG);
        } else {
            builder.logCatLogLevel(Logcat.SHOW_INFO_LOG | Logcat.SHOW_WARN_LOG | Logcat.SHOW_ERROR_LOG);
            // 设置输出文件日志等级
            builder.fileLogLevel(Logcat.SHOW_INFO_LOG | Logcat.SHOW_WARN_LOG | Logcat.SHOW_ERROR_LOG);
        }
        // 删除过了几天无用日志条目
        builder.deleteUnusedLogEntriesAfterDays(7);

        // 是否自动保存日志到文件中
        builder.autoSaveLogToFile(true);
        // 是否显示打印日志调用堆栈信息
        builder.showStackTraceInfo(true);
        // 是否显示文件日志的时间
        builder.showFileTimeInfo(true);
        // 是否显示文件日志的进程以及Linux线程
        builder.showFilePidInfo(true);
        // 是否显示文件日志级别
        builder.showFileLogLevel(true);
        // 是否显示文件日志标签
        builder.showFileLogTag(true);
        // 是否显示文件日志调用堆栈信息
        builder.showFileStackTraceInfo(true);
        Logcat.initialize(this, builder.build());
    }
}
