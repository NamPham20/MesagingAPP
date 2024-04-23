package com.example.messagingapp.share_preference;

import android.content.Context;
import android.provider.ContactsContract;

public class DataLocalManage {

    private static final String EMAIL="EMAIL";
    private static final String PASSWORD="PASSWORD";

    public static DataLocalManage instance;
    private SharePreference mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManage();
        instance.mySharedPreferences = new SharePreference(context);
    }

    public static DataLocalManage getInstance(){
        if ( instance == null){
            instance = new DataLocalManage();
        }
        return instance;
    }

    public static void saveEmail(String email){
        DataLocalManage.getInstance().mySharedPreferences.putStringValue(EMAIL,email);
    }
    public static String getEmail(){
        return DataLocalManage.getInstance().mySharedPreferences.getStringValue(EMAIL);
    }

    public static void savePassword(String email){
        DataLocalManage.getInstance().mySharedPreferences.putStringValue(PASSWORD,email);
    }
    public static String getPasword(){
        return DataLocalManage.getInstance().mySharedPreferences.getStringValue(PASSWORD);
    }



}
