package com.example.messagingapp.share_preference;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManage.init(getApplicationContext());
    }
}
