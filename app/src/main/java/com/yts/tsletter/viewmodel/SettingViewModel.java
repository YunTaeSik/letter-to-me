package com.yts.tsletter.viewmodel;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.utils.ShowIntent;


public class SettingViewModel extends BaseViewModel {
    public TSLiveData<String> version = new TSLiveData<>();

    public void setVersion(Context context) {
        try {
            version.setValue(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onClickContactUs(View view) {
        ShowIntent.emailSend(view.getContext());
    }

    public void onClickReview(View view) {
        ShowIntent.reviews(view.getContext());
    }

    public void onClickInvite(View view) {
        ShowIntent.invite(view.getContext());
    }

}
