package com.yts.tsletter.viewmodel.write;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.viewmodel.BaseViewModel;

public class WriteHeaderViewModel extends BaseViewModel {
    public TSLiveData<Write> mWrite = new TSLiveData<>();

    public void setWrite(Write write) {
        mWrite.setValue(write);
    }
}
