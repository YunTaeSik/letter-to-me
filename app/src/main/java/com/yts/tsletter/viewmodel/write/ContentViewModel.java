package com.yts.tsletter.viewmodel.write;

import com.yts.tsletter.callback.WriteEditCallback;
import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.viewmodel.BaseViewModel;

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
}
