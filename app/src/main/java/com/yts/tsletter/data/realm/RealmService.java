package com.yts.tsletter.data.realm;


import com.yts.tsletter.data.model.Write;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class RealmService {
    public static void saveWrite(Realm realm, Write write) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(write);
        realm.commitTransaction();
    }

    public static void deleteWrite(Realm realm, Write write) {
        realm.beginTransaction();
        realm.where(Write.class).equalTo("date", write.getDate()).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static List<Write> getWriteList(Realm realm, String year) {
        List<Write> writeList = realm.copyFromRealm(realm.where(Write.class).equalTo("receiveDateYear", year).findAll().sort("receiveDate", Sort.DESCENDING));
        return writeList;
    }


}
