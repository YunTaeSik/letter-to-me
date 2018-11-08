package com.yts.tsletter.bindingAdapter;

import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.card.MaterialCardView;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.utils.DateFormat;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;

public class VisibleBindingAdapter {
    @BindingAdapter({"setVisible"})
    public static void setVisible(ProgressBar view, Boolean isLoading) {
        boolean loading = isLoading != null ? isLoading : false;
        if (loading) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"setVisible"})
    public static void setVisible(MaterialCardView view, List<Write> list) {

        boolean visible = list == null || list.size() == 0;
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"setBlindVisible"})
    public static void setBlindVisible(ConstraintLayout view, Write write) {

        long date = write.getReceiveDate();
        boolean isBlindView = DateFormat.isBlindView(date);
        if (isBlindView) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
