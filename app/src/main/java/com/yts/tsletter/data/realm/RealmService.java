package com.yts.tsletter.data.realm;


import com.yts.tsletter.data.model.Write;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
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

    public static List<Write> getWriteList(Realm realm, long date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(date);

        List<Write> writeList = realm.copyFromRealm(realm.where(Write.class)
                .equalTo("receiveDateYear", gregorianCalendar.get(Calendar.YEAR))
                .equalTo("receiveDateMonth", gregorianCalendar.get(Calendar.MONTH))
                .equalTo("receiveDateDay", gregorianCalendar.get(Calendar.DAY_OF_MONTH))
                .findAll());
        return writeList;
    }

    public static int getWriteListSize(Realm realm, long date) {
        int size = 0;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(date);


        RealmResults realmResults = realm.where(Write.class)
                .equalTo("receiveDateYear", String.valueOf(gregorianCalendar.get(Calendar.YEAR)))
                .equalTo("receiveDateMonth", String.valueOf(gregorianCalendar.get(Calendar.MONTH)))
                .equalTo("receiveDateDay", String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_MONTH)))
                .findAll();
        if (realmResults != null) {
            size = realmResults.size();
        }
        return size;
    }


}
