package com.yts.tsletter.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;


import com.yts.tsletter.R;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogCreate {
    private AlertDialog.Builder alertDialog;
    private Context mContext;

    public AlertDialogCreate(Context context) {
        mContext = context;
        alertDialog = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog_Alert);
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    public void saveWrite(DialogInterface.OnClickListener clickListener) {
        try {
            alertDialog.setTitle(mContext.getString(R.string.letter));
            alertDialog.setMessage(mContext.getString(R.string.message_save_write));
            alertDialog.setPositiveButton(R.string.save, clickListener);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWrite(DialogInterface.OnClickListener clickListener) {
        try {
            alertDialog.setTitle(mContext.getString(R.string.letter));
            alertDialog.setMessage(mContext.getString(R.string.message_delete_write));
            alertDialog.setPositiveButton(R.string.delete, clickListener);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
