package com.yts.tsletter.bindingAdapter;


import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.ui.activity.MainActivity;
import com.yts.tsletter.utils.DateFormat;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SharedPrefsUtils;

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

    @BindingAdapter({"setWriteTitleTextColor"})
    public static void setWriteTitleTextColor(TextView view, Write write) {
        try {
            Context context = view.getContext();
            if (write.getContentList() != null && write.getContentList().size() > 0) {
                view.setTextColor(ContextCompat.getColor(context, R.color.white));
            } else {
                view.setTextColor(ContextCompat.getColor(context, R.color.textColor));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setWriteTextTextColor"})
    public static void setWriteTextTextColor(TextView view, Write write) {
        try {
            Context context = view.getContext();
            if (write.getContentList() != null && write.getContentList().size() > 0) {
                view.setTextColor(ContextCompat.getColor(context, R.color.white));
            } else {
                view.setTextColor(ContextCompat.getColor(context, R.color.gray_light));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setIsEdit"})
    public static void setIsEdit(EditText view, Boolean isEdit) {
        boolean edit = isEdit != null ? isEdit : false;
        view.setEnabled(edit);
    }

    @BindingAdapter({"setFontText"})
    public static void setFontText(TextView view, int theme) {
        Context context = view.getContext();
        String text = context.getString(R.string.basics);
        if (theme == 0) {
            text = context.getString(R.string.basics);
        } else if (theme == 1) {
            text = context.getString(R.string.nanum_pen);
        } else if (theme == 2) {
            text = context.getString(R.string.inklipquid);
        }
        view.setText(text);
    }
}
