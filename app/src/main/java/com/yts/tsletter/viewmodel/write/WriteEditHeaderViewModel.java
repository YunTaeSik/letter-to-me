package com.yts.tsletter.viewmodel.write;


import com.yts.tsletter.callback.WriteEditCallback;

public class WriteEditHeaderViewModel extends WriteHeaderViewModel {
    private WriteEditCallback mWriteEditCallback;

    public void setWriteEditCallback(WriteEditCallback writeEditCallback) {
        this.mWriteEditCallback = writeEditCallback;
    }

    public void onReceiveDateChange() {
        if (mWriteEditCallback != null) {
            mWriteEditCallback.onReceiveDateChange();
        }
    }

    public void title(CharSequence charSequence) {
        if (mWriteEditCallback != null) {
            mWriteEditCallback.onTitleChange(charSequence.toString());
        }
    }

    public void text(CharSequence charSequence) {
        if (mWriteEditCallback != null) {
            mWriteEditCallback.onTextChange(charSequence.toString());
        }
    }
}
