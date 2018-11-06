package com.yts.tsletter.viewmodel.write;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.viewmodel.BaseViewModel;

public class ContentViewModel extends BaseViewModel {
    public TSLiveData<Content> mContent = new TSLiveData<>();

    public void setContent(Content content) {
        mContent.setValue(content);
    }
}
