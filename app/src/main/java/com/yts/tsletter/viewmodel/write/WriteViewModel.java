package com.yts.tsletter.viewmodel.write;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.viewmodel.BaseViewModel;

import java.util.ArrayList;

public class WriteViewModel extends BaseViewModel {
    public TSLiveData<Write> mWrite = new TSLiveData<>(new Write());
    public TSLiveData<ArrayList<Object>> mWriteList = new TSLiveData<>();


    public void setWriteList(Write write) {
        mWrite.setValue(write);

        ArrayList<Object> writeList = new ArrayList<>();
        writeList.add(write);
        if (write.getContentList() != null) {
            writeList.addAll(write.getContentList());
        }
        mWriteList.setValue(writeList);
    }

}
