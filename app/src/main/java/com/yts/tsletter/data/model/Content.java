package com.yts.tsletter.data.model;

import io.realm.RealmObject;

public class Content extends RealmObject {
    private String path;
    private String text;
    private String mimeType;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isImage() {
        if (mimeType != null && mimeType.contains("image")) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        if (mimeType != null && mimeType.contains("video")) {
            return true;
        }
        return false;
    }

    public boolean isAudio() {
        if (mimeType != null && mimeType.contains("audio")) {
            return true;
        }
        return false;
    }

}
