package com.rikkeisoft.thanhnt.note.utils;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("NoteDB").build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
