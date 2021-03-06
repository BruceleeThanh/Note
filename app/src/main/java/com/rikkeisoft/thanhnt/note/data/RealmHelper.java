package com.rikkeisoft.thanhnt.note.data;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by ThanhNT on 8/15/2017.
 */

public class RealmHelper<T extends RealmObject> {

    protected Realm realm;
    protected final Class<T> type;

    public RealmHelper(Class<T> type){
        this.type = type;
        realm = Realm.getDefaultInstance();
    }

    public T getById(int id){
        return realm.where(type).equalTo("id", id).findFirst();
    }

    public List<T> getAll(){
        return realm.where(type).findAll();
    }

    public T createOfUpdate(T t){
        T result;
        realm.beginTransaction();
        result = realm.copyToRealmOrUpdate(t);
        realm.commitTransaction();
        Log.i("Realm create or update:", "success");
        return result;
    }

    public void deleteById(int id){
        RealmResults<T> results = realm.where(type).equalTo("id", id).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void deleteAll(){
        RealmResults<T> results = realm.where(type).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

}
