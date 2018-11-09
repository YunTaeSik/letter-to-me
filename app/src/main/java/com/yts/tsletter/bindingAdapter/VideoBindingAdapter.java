package com.yts.tsletter.bindingAdapter;

import android.content.Context;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yts.tsletter.data.model.Content;

import androidx.databinding.BindingAdapter;

public class VideoBindingAdapter {

    @BindingAdapter({"setVideoView"})
    public static void setVideoView(VideoView view, Content content) {
        Context context = view.getContext();
        if (content != null && content.isVideo()) {
            MediaController mediaController = new MediaController(context);
            view.setMediaController(mediaController);

            view.setVideoPath(content.getPath());
            view.start();
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
