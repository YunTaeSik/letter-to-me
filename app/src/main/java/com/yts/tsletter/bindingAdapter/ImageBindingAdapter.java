package com.yts.tsletter.bindingAdapter;

import com.yts.tsletter.GlideApp;
import com.yts.tsletter.data.model.Content;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class ImageBindingAdapter {
    @BindingAdapter("setImage")
    public static void setImage(final AppCompatImageView view, Content content) {
        if (content != null && content.getPath() != null) {
            GlideApp.with(view.getContext()).load(content.getPath()).override(view.getMeasuredWidth(), view.getMeasuredHeight()).thumbnail(0.1f).centerCrop().into(view);
        }
    }

}
