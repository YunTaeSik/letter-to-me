package com.yts.tsletter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsletter.ui.WriteActivity;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    public void startWriteActivity(View view) {
        Context context = view.getContext();
        Intent write = new Intent(context, WriteActivity.class);
        context.startActivity(write);
    }
}
