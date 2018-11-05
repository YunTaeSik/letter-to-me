package com.yts.tsletter.data.model;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Write extends RealmObject {
    @PrimaryKey
    private long date;

    private long receiveDate;

    private String title;
    private String text;
    private RealmList<Content> contentList;

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

    public void setContentList(RealmList<Content> contentList) {
        this.contentList = contentList;
    }
}

