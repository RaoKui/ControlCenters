package com.yxkj.kzzx.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.w3c.dom.ProcessingInstruction;

import java.io.File;

/**
 * 打开WPS工具类
 * Created by RaoKui on 2017/7/20.
 */

public class OpenWPSHelper {
    /**
     * WPS包名
     */
    private static final String WPS_PACKAGE_NAME = "cn.wps.moffice_eng";
    /**
     * 启动WPS的类名
     */
    private static final String WPS_ACTIVITY_NAME = "cn.wps.moffice.documentmanager.PreStartActivity2";
    /**
     * 关闭WPS打开的应用包名
     */
    private static final String APP_PACKAGE = "com.yxkj.kzzx";
    /**
     * 关闭WPS打开的应用类名
     */
    private static final String APP_ACTIVITY = "com.example.a20151203.testvoice";

    private static final String OPEN_MODE = "OpenMode";

    private static final String SEND_CLOSE_BROAD = "SendCloseBroad";

    private static final String CLEAR_TRACE = "ClearTrace";


    /**
     * 打开文件
     *
     * @param context
     * @param path
     * @return
     */
    public static boolean openFile(Context context, String path) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(OPEN_MODE, "Normal");
        //打开模式
        bundle.putBoolean(SEND_CLOSE_BROAD, true);
        //关闭时是否发送广播
        bundle.putString(APP_PACKAGE, APP_ACTIVITY);
        //第三方应用的包名，用于对改应用合法性的验证
        bundle.putBoolean(CLEAR_TRACE, true);
        //清除打开记录
        //bundle.putBoolean(CLEAR_FILE, true);
        //关闭后删除打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setClassName(WPS_PACKAGE_NAME, WPS_ACTIVITY_NAME);

        File file = new File(path);
        if (file == null || !file.exists()) {
            Logger.d("文件不存在！");
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
            return false;
        }

        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
