package com.liye.logdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.liye.log.LogUtils;

import java.io.File;

/**
 * Created by Shuai
 * 06/05/2019.
 */

public class MegApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
    }

    private void initLog() {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(dir + File.separator + "Meg-Logs");
        if (!file.exists()) file.mkdirs();
        LogUtils.setLogDir(file.getAbsolutePath());
        LogUtils.setLogLevel(LogUtils.LogLevel.DEBUG);
    }
}
