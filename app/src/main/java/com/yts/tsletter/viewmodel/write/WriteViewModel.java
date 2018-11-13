package com.yts.tsletter.viewmodel.write;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.yts.tsletter.R;
import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.data.realm.RealmService;
import com.yts.tsletter.ui.activity.ReadActivity;
import com.yts.tsletter.ui.dialog.AlertDialogCreate;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SendBroadcast;
import com.yts.tsletter.viewmodel.BaseViewModel;

import java.util.ArrayList;

public class WriteViewModel extends BaseViewModel {
    public TSLiveData<Write> mWrite = new TSLiveData<>(new Write());
    public TSLiveData<ArrayList<Object>> mWriteList = new TSLiveData<>();

    public TSLiveData<Boolean> isAdLoad = new TSLiveData<>(false);

    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    public void setWrite(Write write) {
        mWrite.setValue(write);
    }

    public void setWriteList(Write write) {
        mWrite.setValue(write);

        ArrayList<Object> writeList = new ArrayList<>();
        writeList.add(write);
        if (write.getContentList() != null) {
            writeList.addAll(write.getContentList());
        }
        mWriteList.setValue(writeList);
    }

    public void setIsAdLoad(boolean isAdLoad) {
        this.isAdLoad.setValue(isAdLoad);
    }

    public void setRewardedVideoAd(RewardedVideoAd rewardedVideoAd) {
        this.mRewardedVideoAd = rewardedVideoAd;
    }

    public void setInterstitialAd(InterstitialAd interstitialAd) {
        this.mInterstitialAd = interstitialAd;
    }

    public void startReadActivity(View view) {
        Context context = view.getContext();
        Intent read = new Intent(context, ReadActivity.class);
        read.putExtra(Keys.WRITE, mWrite.getValue());
        context.startActivity(read);
    }

    public void deleteWrite(View view) {
        final Context context = view.getContext();
        new AlertDialogCreate(context).deleteWrite(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RealmService.deleteWrite(mRealm, mWrite.getValue());
                SendBroadcast.deleteWrite(context);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });
    }

    public void onPreview(View view) {
        if (mRewardedVideoAd != null) {
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                return;
            }

        }
        if (mInterstitialAd != null) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                return;
            }
        }
        rewarded();
    }


    public void rewarded() {
        try {
            Write write = mWrite.getValue();
            write.setReceiveDate(System.currentTimeMillis() - 100);
            mWrite.setValue(write);
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }

}
