package com.yts.tsletter.utils;

import android.content.Context;
import android.content.Intent;

public class SendBroadcast {
    public static final String SAVE_WRITE = "saveWrite";
    public static final String DELETE_WRITE = "deleteWrite";

    public static void saveWrite(Context context) {
        Intent send = new Intent(SAVE_WRITE);
        context.sendBroadcast(send);
    }

    public static void deleteWrite(Context context) {
        Intent send = new Intent(DELETE_WRITE);
        context.sendBroadcast(send);
    }
}
