package com.yts.tsletter.bindingAdapter;


import android.content.Context;
import android.widget.TextView;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.utils.DateFormat;

import java.text.DateFormatSymbols;
import java.util.Locale;

import androidx.core.content.ContextCompat;
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

    @BindingAdapter({"setWeekTextColor"})
    public static void setWeekTextColor(TextView view, int week) {
        Context context = view.getContext();
        if (week == 1) {
            view.setTextColor(ContextCompat.getColor(context, R.color.pink));
        } else if (week == 7) {
            view.setTextColor(ContextCompat.getColor(context, R.color.blue));
        } else {
            view.setTextColor(ContextCompat.getColor(context, R.color.gray));
        }
    }

    @BindingAdapter({"setMonthText"})
    public static void setMonthText(TextView view, Write write) {
        try {
            int month = Integer.parseInt(write.getReceiveDateMonth());
            String text = new DateFormatSymbols(Locale.ENGLISH).getMonths()[month].toUpperCase();
            view.setText(text);

            int week = DateFormat.getWeek(write.getReceiveDate());
            setWeekTextColor(view, week);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Write write) {
        try {
            view.setText(write.getReceiveDateDay());
            int week = DateFormat.getWeek(write.getReceiveDate());
            setWeekTextColor(view, week);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
