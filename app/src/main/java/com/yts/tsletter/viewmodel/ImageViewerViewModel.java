package com.yts.tsletter.viewmodel;

import android.content.Context;

import com.yts.tsletter.data.model.Content;

public class ImageViewerViewModel extends BaseViewModel {
    public Content mContent;

    public ImageViewerViewModel(Context context, Content content) {
        this.mContent = content;
    }
}
