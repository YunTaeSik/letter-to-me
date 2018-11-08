package com.yts.tsletter.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Write extends RealmObject implements Parcelable {
    @PrimaryKey
    private long date;

    private long receiveDate;
    private String receiveDateYear;
    private String receiveDateMonth;
    private String receiveDateDay;

    private String title;
    private String text;
    private RealmList<Content> contentList = new RealmList<>();

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(long receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveDateYear() {
        return receiveDateYear;
    }

    public void setReceiveDateYear(String receiveDateYear) {
        this.receiveDateYear = receiveDateYear;
    }

    public String getReceiveDateMonth() {
        return receiveDateMonth;
    }

    public void setReceiveDateMonth(String receiveDateMonth) {
        this.receiveDateMonth = receiveDateMonth;
    }

    public String getReceiveDateDay() {
        return receiveDateDay;
    }

    public void setReceiveDateDay(String receiveDateDay) {
        this.receiveDateDay = receiveDateDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RealmList<Content> getContentList() {
        return contentList;
    }


    public void addContentList(List<Content> contentList) {
        this.contentList.addAll(contentList);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
        dest.writeLong(this.receiveDate);
        dest.writeString(this.receiveDateYear);
        dest.writeString(this.receiveDateMonth);
        dest.writeString(this.receiveDateDay);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeList(this.contentList);
    }

    public Write() {
    }

    protected Write(Parcel in) {
        this.date = in.readLong();
        this.receiveDate = in.readLong();
        this.receiveDateYear = in.readString();
        this.receiveDateMonth = in.readString();
        this.receiveDateDay = in.readString();
        this.title = in.readString();
        this.text = in.readString();
        this.contentList = new RealmList<>();
        in.readList(this.contentList, Content.class.getClassLoader());
    }

    public static final Parcelable.Creator<Write> CREATOR = new Parcelable.Creator<Write>() {
        @Override
        public Write createFromParcel(Parcel source) {
            return new Write(source);
        }

        @Override
        public Write[] newArray(int size) {
            return new Write[size];
        }
    };
}

