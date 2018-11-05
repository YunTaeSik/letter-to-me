package com.yts.tsletter.viewmodel;

import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class MainViewModel extends BaseViewModel {


    public void startLottieAnimation(View view) {
        if (view instanceof LottieAnimationView) {
            ((LottieAnimationView) view).playAnimation();
        }
    }
}
