package com.yts.tsletter.bindingAdapter;


import android.widget.TextView;

import com.yts.tsletter.utils.DateFormat;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {

    @BindingAdapter({"setTimeText"})
    public static void setTimeText(TextView view, long date) {
        view.setText(DateFormat.getDate(date, DateFormat.FULL_FORMAT));
    }

    @BindingAdapter({"setTimeText", "setTimePattern"})
    public static void setTimeText(TextView view, long date, String pattern) {
        if (date != 0) {
            view.setText(DateFormat.getDate(date, pattern));
        }
    }

}
