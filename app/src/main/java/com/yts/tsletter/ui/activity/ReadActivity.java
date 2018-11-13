package com.yts.tsletter.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.yts.tsletter.BaseActivity;
import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.databinding.ReadBinding;
import com.yts.tsletter.ui.adapter.ReadAdapter;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.viewmodel.write.WriteViewModel;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReadActivity extends BaseActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;

    private ReadBinding binding;
    private WriteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read);
        model = ViewModelProviders.of(this).get(WriteViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        Write write = getIntent().getParcelableExtra(Keys.WRITE) != null ? (Write) getIntent().getParcelableExtra(Keys.WRITE) : new Write();
        model.setWriteList(write);

        observe();

        //리워드 광고
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
        model.setRewardedVideoAd(mRewardedVideoAd);

        //전면광고
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build());
                if (model != null) {
                    model.rewarded();
                }
            }
        });
        model.setInterstitialAd(mInterstitialAd);
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.reward_ad_id), new AdRequest.Builder().addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build());
    }

    public void observe() {
        model.mWriteList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.listRead;
                ReadAdapter adapter = (ReadAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setReadList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    adapter = new ReadAdapter(objects);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        if (model != null) {
            model.setIsAdLoad(true);
        }

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        if (model != null) {
            model.setIsAdLoad(false);
        }
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        if (model != null) {
            model.rewarded();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.e("onRewardedVideoAdFailedToLoad", String.valueOf(i));
        if (model != null) {
            model.setIsAdLoad(true);
        }

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
