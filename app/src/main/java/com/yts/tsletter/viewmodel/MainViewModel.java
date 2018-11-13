package com.yts.tsletter.viewmodel;

import android.content.Context;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdRequest;
import com.whiteelephant.monthpicker.MonthPickerDialog;
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


    public void leftYear() {
        String currentYear = mCurrentYear.getValue();
        int year = Integer.parseInt(currentYear);
        if (year > 1) {
            year = year - 1;
            mCurrentYear.setValue(String.valueOf(year));
        }
    }

    public void changeDate(View view) {
        Context context = view.getContext();
        MonthPickerDialog.Builder monthPickerDialog = new MonthPickerDialog.Builder(context, new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                mCurrentYear.setValue(String.valueOf(selectedYear));
            }
        }, Integer.parseInt(mCurrentYear.getValue()), 1);
        monthPickerDialog.setMaxYear(Integer.parseInt(mCurrentYear.getValue()) + 100).showYearOnly().build().show();
    }

    public void rightYear() {
        String currentYear = mCurrentYear.getValue();
        int year = Integer.parseInt(currentYear);
        year = year + 1;
        mCurrentYear.setValue(String.valueOf(year));
    }
}
