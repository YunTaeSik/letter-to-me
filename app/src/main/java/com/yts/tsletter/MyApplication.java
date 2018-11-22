package com.yts.tsletter;

import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.yts.tsletter.data.Migration;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("TsLetter.realm")
                .schemaVersion(0)
                .migration(new Migration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);


        MobileAds.initialize(this, getString(R.string.ad_app_id));
        setTheme(R.style.AppThemeTwo);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
