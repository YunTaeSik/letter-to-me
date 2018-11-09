package com.yts.tsletter.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;


import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.ui.activity.WriteEditActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import io.realm.Realm;

public class BaseViewModel extends ViewModel {
    public TSLiveData<Boolean> isLoading = new TSLiveData<>(false);
    protected Realm mRealm = Realm.getDefaultInstance();

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
