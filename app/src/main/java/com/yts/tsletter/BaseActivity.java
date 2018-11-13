package com.yts.tsletter;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {
    public CompositeDisposable mCompositeDisposable;
    public AdRequest mAdRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
