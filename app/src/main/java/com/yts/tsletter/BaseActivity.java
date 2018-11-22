package com.yts.tsletter;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SharedPrefsUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {
    public CompositeDisposable mCompositeDisposable;
    public AdRequest mAdRequest;
/*
    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(R.style.AppThemeTwo, true);
        return theme;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int theme = SharedPrefsUtils.getIntegerPreference(this, Keys.APP_THEME, 1);
        if (theme == 0) {
            setTheme(R.style.AppTheme);
        } else if (theme == 1) {
            setTheme(R.style.AppThemeTwo);
        } else if (theme == 2) {
            setTheme(R.style.AppThemeThree);
        }


        mCompositeDisposable = new CompositeDisposable();
        mAdRequest = new AdRequest.Builder().addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
