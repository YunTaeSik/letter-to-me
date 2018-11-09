package com.yts.tsletter.viewmodel.write;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;

import com.yts.tsletter.R;
import com.yts.tsletter.data.model.Content;
import com.yts.tsletter.data.model.Write;
import com.yts.tsletter.data.realm.RealmService;
import com.yts.tsletter.ui.dialog.AlertDialogCreate;
import com.yts.tsletter.ui.dialog.DateSelectDialog;
import com.yts.tsletter.utils.RequestCode;
import com.yts.tsletter.utils.SendBroadcast;
import com.yts.tsletter.utils.ShowIntent;
import com.yts.tsletter.utils.ToastMake;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        Write write = mWrite.getValue();
        if (write != null) {
            List<Content> contentList = new ArrayList<>();
            Content content = new Content();
            content.setPath(path);
            content.setMimeType("audio");
            contentList.add(content);
            write.addContentList(contentList);
        }
        mWrite.setValue(write);
        setWriteList(write);
    }


    public void receiveDateChange(Context context) {
        if (mWrite.getValue() != null) {
            DateSelectDialog.start(context, mWrite.getValue().getReceiveDate(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    Write write = mWrite.getValue();

                    GregorianCalendar newCalendar = new GregorianCalendar();
                    newCalendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);

                    write.setReceiveDate(newCalendar.getTimeInMillis());
                    write.setReceiveDateYear(String.valueOf(year));
                    write.setReceiveDateMonth(String.valueOf(monthOfYear));
                    write.setReceiveDateDay(String.valueOf(dayOfMonth));

                    mWrite.setValue(write);
                    setWriteList(write);
                }
            });
        }
    }

    public void changeTitle(String title) {
        if (mWrite != null && mWrite.getValue() != null) {
            Write write = mWrite.getValue();
            write.setTitle(title);
        }
    }

    public void changeText(String text) {
        if (mWrite != null && mWrite.getValue() != null) {
            Write write = mWrite.getValue();
            write.setText(text);
        }
    }

    public void changeContentText(int position, String text) {
        if (mWrite != null && mWrite.getValue() != null) {
            Write write = mWrite.getValue();
            try {
                if (write.getContentList() != null && write.getContentList().size() > position) {
                    write.getContentList().get(position).setText(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void save(View view) {
        final Context context = view.getContext();

        final Write write = mWrite.getValue();
        if (write != null) {
            if (write.getReceiveDate() == 0) {
                ToastMake.make(context, R.string.error_receivedate);
                return;
            } else if (write.getTitle() == null || write.getTitle().length() == 0) {
                ToastMake.make(context, R.string.error_title);
                return;
            }
            new AlertDialogCreate(context).saveWrite(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    isLoading.setValue(true);
                    write.setDate(System.currentTimeMillis());

                    RealmService.saveWrite(mRealm, write);
                    isLoading.setValue(false);

                    SendBroadcast.saveWrite(context);

                    if (context instanceof Activity) {
                        ((Activity) context).finish();
                    }
                }
            });


        }
    }

}
