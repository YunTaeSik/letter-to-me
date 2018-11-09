package com.yts.tsletter.viewmodel.write;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.yts.tsletter.data.TSLiveData;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.data.realm.RealmService;
import com.yts.tsletter.ui.ReadActivity;
import com.yts.tsletter.ui.dialog.AlertDialogCreate;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SendBroadcast;
import com.yts.tsletter.viewmodel.BaseViewModel;

import java.util.ArrayList;

public class WriteViewModel extends BaseViewModel {
    public TSLiveData<Write> mWrite = new TSLiveData<>(new Write());
    public TSLiveData<ArrayList<Object>> mWriteList = new TSLiveData<>();

    public void setWrite(Write write) {
        mWrite.setValue(write);
    }

    public void setWriteList(Write write) {
        mWrite.setValue(write);

        ArrayList<Object> writeList = new ArrayList<>();
        writeList.add(write);
        if (write.getContentList() != null) {
            writeList.addAll(write.getContentList());
        }
        mWriteList.setValue(writeList);
    }

    public void startReadActivity(View view) {
        Context context = view.getContext();
        Intent read = new Intent(context, ReadActivity.class);
        read.putExtra(Keys.WRITE, mWrite.getValue());
        context.startActivity(read);
    }

    public void deleteWrite(View view) {
        final Context context = view.getContext();
        new AlertDialogCreate(context).deleteWrite(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RealmService.deleteWrite(mRealm, mWrite.getValue());
                SendBroadcast.deleteWrite(context);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });
    }

}
