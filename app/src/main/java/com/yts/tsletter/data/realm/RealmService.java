package com.yts.tsletter.data.realm;


import com.yts.tsletter.data.model.Write;

import io.realm.Realm;

public class RealmService {
    public static void saveWrite(Realm realm, Write write) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(write);
        realm.commitTransaction();
    }
}
