package com.example.messagingapp.share_preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
    private static final String MY_SHARED_PREFERENCES ="MY_SHARE_PREFERENCES";
    private Context mContext;

    public SharePreference(Context context) {
        this.mContext= context;
    }

    public void SharedPreference(Context context) {
        this.mContext = context;
    }

    public void putBooleanValue(String key, boolean value){
        SharedPreferences sharedPreferences =  mContext.getSharedPreferences(MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public  Boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences =  mContext.getSharedPreferences(MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public void putStringValue(String key, String value){
        SharedPreferences sharedPreferences =  mContext.getSharedPreferences(MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getStringValue(String key){
        SharedPreferences sharedPreferences =  mContext.getSharedPreferences(MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
