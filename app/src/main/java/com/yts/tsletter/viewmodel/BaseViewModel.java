package com.yts.tsletter.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.google.android.gms.ads.AdRequest;
import com.yts.tsletter.R;
import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.ui.activity.WriteEditActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import io.realm.Realm;

public class BaseViewModel extends ViewModel {
    public AdRequest mAdRequest;

    public TSLiveData<Boolean> isLoading = new TSLiveData<>(false);
    protected Realm mRealm = Realm.getDefaultInstance();


    public void setAdRequest() {
        this.mAdRequest = new AdRequest.Builder().addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build();
    }

    public void finish(View view) {
        Context context = view.getContext();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).onBackPressed();
        }
    }

    public void startWriteActivity(View view) {
        Context context = view.getContext();
        Intent write = new Intent(context, WriteEditActivity.class);
        context.startActivity(write);
    }
}
