package com.example.bulksms;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

public class MyApplication extends Application{

    @Override
    public void onCreate() {

        super.onCreate();

        //Initialize Realm
        Realm.init(this);

    }

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }
}
