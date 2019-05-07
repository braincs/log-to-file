package com.liye.logdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.liye.log.LogUtils;

/**
 * Created by Shuai
 * 06/05/2019.
 */

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final String[] requiredPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();
        LogUtils.d(TAG, "--start--");
        try{
            LogUtils.w(TAG, "--prepare to do sth dangerous--");
            int res = 1/0;

        }catch (Exception e){
            LogUtils.e(TAG, e.toString());
        }
        LogUtils.d(TAG, "--after dangerous method call--");


    }

    private void getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : requiredPermissions) {
                if (checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(requiredPermissions, 0);
                    return;
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.close();
    }
}
