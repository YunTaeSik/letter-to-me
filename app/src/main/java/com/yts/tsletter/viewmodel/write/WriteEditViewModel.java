package com.yts.tsletter.viewmodel.write;

import android.view.View;

import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.utils.RequestCode;
import com.yts.tsletter.utils.ShowIntent;

import java.util.ArrayList;
import java.util.List;

public class WriteEditViewModel extends WriteViewModel {

    public void selectImage(View view) {
        ShowIntent.imageSelect(view.getContext(), RequestCode.IMAGE_SELECT);
    }

    public void addImage(String path) {
        Write write = mWrite.getValue();
        if (write != null) {
            List<Content> contentList = new ArrayList<>();
            Content content = new Content();
            content.setPath(path);
            content.setMimeType("image");
            contentList.add(content);
            write.addContentList(contentList);
        }
        mWrite.setValue(write);
        setWriteList(write);
    }

    public void selectVideo(View view) {
        ShowIntent.videoSelect(view.getContext(), RequestCode.VIDEO_SELECT);
    }

    public void addVideo(String path) {
        Write write = mWrite.getValue();
        if (write != null) {
            List<Content> contentList = new ArrayList<>();
            Content content = new Content();
            content.setPath(path);
            content.setMimeType("video");
            contentList.add(content);
            write.addContentList(contentList);
        }
        mWrite.setValue(write);
        setWriteList(write);
    }

    public void selectAudio(View view) {
        ShowIntent.audioSelect(view.getContext(), RequestCode.AUDIO_SELECT);
    }

    public void addAudio(String path) {

    }


}
