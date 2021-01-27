package com.gaojun.roomdemo.throeblelog;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;


/**
 * 创建崩溃日志的文件夹和文件,并判断崩溃时的app操作
 *
 * @author GaoJun
 * @time 2021/1/27 11:23
 */
public class ErrorExceptionHandle {

    private Context context;

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private File crashFile;

    // 新建的时候，随即的开始建造崩溃文件夹和崩溃文件
    public ErrorExceptionHandle(Application context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {

        this.context = context;

        this.uncaughtExceptionHandler = uncaughtExceptionHandler;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = context.getExternalFilesDir("crashFile");
            if (!file.exists()) {
                // 创建崩溃捕捉所在文件夹
                file.mkdirs();
            }
            crashFile = new File(file, getCrashFileName());

            if (!crashFile.exists()) {
                try {
                    crashFile.createNewFile();//创建崩溃捕捉文件
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    // 用来执行崩溃时具体的操作
    public void excute(Thread thread, Throwable throwable) {
        CrashInfoMonitorDetail crashInfoMonitorDetail = CrashInfoMonitorDetail.produce(throwable, thread, context);
        crashInfoMonitorDetail.writeToFile(crashFile);
        signOut(thread, throwable);

    }

    // 强制退出软件
    public void signOut(Thread thread, Throwable throwable) {
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    //获取崩溃文件名称，具体是年月日组成的文件名
    private String getCrashFileName() {
        StringBuilder stringBuilder = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        stringBuilder.append("crash_");
        stringBuilder.append(year + "-");
        stringBuilder.append(month + "-");
        stringBuilder.append(date);
        stringBuilder.append(".txt");
        return stringBuilder.toString();
    }

}
