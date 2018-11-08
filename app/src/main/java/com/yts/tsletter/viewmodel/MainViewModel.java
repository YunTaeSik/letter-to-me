package com.yts.tsletter.viewmodel;

import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.data.realm.RealmService;

import java.util.Calendar;
import java.util.List;


public class MainViewModel extends BaseViewModel {
    public TSLiveData<List<Write>> mWriteList = new TSLiveData<>();
    public TSLiveData<String> mCurrentYear = new TSLiveData<>();

    public void setCurrentYear(Calendar calendar) {
        mCurrentYear.setValue(String.valueOf(calendar.get(Calendar.YEAR)));
    }

    public void setCurrentYear(String currentYear) {
        mCurrentYear.setValue(currentYear);
    }

    public void refreshWriteList() {
        mWriteList.setValue(RealmService.getWriteList(mRealm, mCurrentYear.getValue()));
    }

    public void getWriteList(String year) {
        mWriteList.setValue(RealmService.getWriteList(mRealm, year));
    }


    public void startLottieAnimation(View view) {
        if (view instanceof LottieAnimationView) {
            ((LottieAnimationView) view).playAnimation();
        }
    }
}
