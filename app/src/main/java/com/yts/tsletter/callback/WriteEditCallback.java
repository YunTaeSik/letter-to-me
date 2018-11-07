package com.yts.tsletter.callback;

public interface WriteEditCallback {
    void onReceiveDateChange();

    void onTitleChange(String title);

    void onTextChange(String text);

    void onContentTextChange(int position, String text);
}
