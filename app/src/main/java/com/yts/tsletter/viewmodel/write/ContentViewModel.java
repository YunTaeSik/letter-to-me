package com.yts.tsletter.viewmodel.write;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsletter.callback.WriteEditCallback;
import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.ui.activity.ImageViewerActivity;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.viewmodel.BaseViewModel;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

public class ContentViewModel extends BaseViewModel {
    public TSLiveData<Content> mContent = new TSLiveData<>();
    public TSLiveData<Boolean> isEdit = new TSLiveData<>(false);

    private int position;
    private WriteEditCallback mWriteEditCallback;

    public void setWriteEditCallback(WriteEditCallback writeEditCallback) {
        this.mWriteEditCallback = writeEditCallback;
    }

    public void setContent(Content content) {
        mContent.setValue(content);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setIsEdit(boolean edit) {
        isEdit.setValue(edit);
    }

    public void text(CharSequence charSequence) {
        if (mWriteEditCallback != null) {
            mWriteEditCallback.onContentTextChange(position - 1, charSequence.toString());
        }
    }

    public void startImageViewer(View view) {
        if (isEdit.getValue() != null && !isEdit.getValue()) {
            Context context = view.getContext();
            Content content = mContent.getValue();

            Intent imageViewer = new Intent(context, ImageViewerActivity.class);
            ViewCompat.setTransitionName(view, Keys.TRANS_NAME + position);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, view, ViewCompat.getTransitionName(view));

            imageViewer.putExtra(Keys.TRANS_NAME, Keys.TRANS_NAME + position);
            imageViewer.putExtra(Keys.CONTENT, content);

            context.startActivity(imageViewer, options.toBundle());
        }
    }
}
