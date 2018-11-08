package com.yts.tsletter.bindingAdapter;

import android.view.View;

import com.yts.tsletter.GlideApp;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class ImageBindingAdapter {
    @BindingAdapter("setImage")
    public static void setImage(final AppCompatImageView view, Content content) {
        if (content != null && content.getPath() != null) {
            GlideApp.with(view.getContext()).load(content.getPath()).override(view.getMeasuredWidth(), view.getMeasuredHeight()).thumbnail(0.1f).centerCrop().into(view);
        }
    }

    @BindingAdapter({"setImage"})
    public static void setImage(final AppCompatImageView view, Write write) {
        if (write != null) {
            if (write.getContentList() != null && write.getContentList().size() > 0) {
                Content content = write.getContentList().get(0);
                setImage(view, content);
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }
}
