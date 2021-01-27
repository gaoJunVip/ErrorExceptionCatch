package com.gaojun.roomdemo.throeblelog;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * 获取崩溃信息并进行保存操作
 *
 * @author GaoJun
 * @time 2021/1/27 11:22
 */
public class CrashInfoMonitorDetail {
    // 崩溃日志的具体内容
    private static String crashInfo;

    private CrashInfoMonitorDetail() {

    }

    // 获取错误等信息
    public static CrashInfoMonitorDetail produce(Throwable throwable, Thread thread, Context context) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(out);
        out.toString();
        print.append("crahtime:" + System.currentTimeMillis()).append("\n");
        print.append(getSysytemInfor());
        print.append("theadName:" + thread.getName() + "\n").append("threadID:" + thread.getId() + "\n");
        crashInfo = getSysytemInfor() + "threadName:" + thread.getName() + "\n" + "threadID:" + thread.getId() + "\n" + "ErrorInformation:" + throwable.getMessage();
        print.append(throwable.getMessage()).append("\n");
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        try {
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement stackTraceElement = stackTrace[i];
                String trace = stackTraceElement.toString();
                print.append(trace + "\n");
                crashInfo += trace + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("crashInfor", crashInfo);
        throwable.printStackTrace(print);
        return new CrashInfoMonitorDetail();
    }

    // 把错误信息填充进崩溃文件中
    public void writeToFile(File file) {

        PrintWriter printer = null;
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file, true));
            printer = new PrintWriter(out);
            printer.println(crashInfo);
            printer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printer != null) {
                printer.close();
            }
        }
    }

    // 获取手机的一些设备参数
    public static String getSysytemInfor() {
        StringBuffer sb = new StringBuffer();
        sb.append("主板：" + Build.BOARD + "\n");
        sb.append("系统启动程序版本号：" + Build.BOOTLOADER + "\n");
        sb.append("系统定制商：" + Build.BRAND + "\n");
        sb.append("cpu指令集：" + Build.CPU_ABI + "\n");
        sb.append("cpu指令集2：" + Build.CPU_ABI2 + "\n");
        sb.append("设置参数：" + Build.DEVICE + "\n");
        sb.append("显示屏参数：" + Build.DISPLAY + "\n");
        sb.append("无线电固件版本：" + Build.getRadioVersion() + "\n");
        sb.append("硬件识别码：" + Build.FINGERPRINT + "\n");
        sb.append("硬件名称：" + Build.HARDWARE + "\n");
        sb.append("HOST:" + Build.HOST + "\n");
        sb.append("修订版本列表：" + Build.ID + "\n");
        sb.append("硬件制造商：" + Build.MANUFACTURER + "\n");
        sb.append("版本：" + Build.MODEL + "\n");
        sb.append("硬件序列号：" + Build.SERIAL + "\n");
        sb.append("手机制造商：" + Build.PRODUCT + "\n");
        sb.append("描述Build的标签：" + Build.TAGS + "\n");
        sb.append("TIME:" + Build.TIME + "\n");
        sb.append("builder类型：" + Build.TYPE + "\n");
        sb.append("USER:" + Build.USER + "\n");
        return sb.toString();
    }

    public String getString() {
        return crashInfo;
    }

}
