package com.example.administrator.realm;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Administrator on 2017-11-17.
 */
public class RealmApp extends Application {

    // 렘은 스트림으로 동작하지 않음 - 찾아볼 것
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }

}
