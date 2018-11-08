package com.yts.tsletter.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Content extends RealmObject implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.text);
        dest.writeString(this.mimeType);
    }

    public Content() {
    }

    protected Content(Parcel in) {
        this.path = in.readString();
        this.text = in.readString();
        this.mimeType = in.readString();
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
}
