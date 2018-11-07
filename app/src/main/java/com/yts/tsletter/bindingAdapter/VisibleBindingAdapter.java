package com.yts.tsletter.bindingAdapter;

import android.view.View;
import android.widget.ProgressBar;

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
}
