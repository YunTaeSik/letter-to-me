package com.yts.tsletter.viewmodel;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SendBroadcast;
import com.yts.tsletter.utils.SharedPrefsUtils;
import com.yts.tsletter.utils.ShowIntent;


public class SettingViewModel extends BaseViewModel {
    public TSLiveData<String> version = new TSLiveData<>();
    public TSLiveData<Integer> theme = new TSLiveData<>(1);

    public void setVersion(Context context) {
        try {
            version.setValue(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setTheme(Context context) {
        try {
            theme.setValue(SharedPrefsUtils.getIntegerPreference(context, Keys.APP_THEME, 1));
        } catch (Exception e) {
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


    public void toggleTheme(View view) {
        if (this.theme != null && this.theme.getValue() != null) {
            Context context = view.getContext();

            int theme = this.theme.getValue();
            theme = (theme + 1) % 3;
            this.theme.setValue(theme);
            SharedPrefsUtils.setIntegerPreference(context, Keys.APP_THEME, theme);
            SendBroadcast.changeFont(context);
        }
    }
}
