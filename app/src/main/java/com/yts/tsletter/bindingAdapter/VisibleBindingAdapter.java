package com.yts.tsletter.bindingAdapter;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.utils.DateFormat;

import org.w3c.dom.Text;

import java.util.List;

import androidx.appcompat.widget.AppCompatEditText;
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
    public static void setVisible(TextView view, Boolean isLoading) {
        boolean loading = isLoading != null ? isLoading : false;
        if (loading) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter({"setVisible", "setEdit"})
    public static void setVisible(AppCompatEditText view, Content content, Boolean isEdit) {
        boolean edit = isEdit != null ? isEdit : false;
        String text = content.getText();
        if (!edit) {
            view.setEnabled(false);
            if ((text == null || text.length() == 0)) {
                view.setVisibility(View.INVISIBLE);
            }
        } else {
            view.setEnabled(true);
            view.setVisibility(View.VISIBLE);
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
