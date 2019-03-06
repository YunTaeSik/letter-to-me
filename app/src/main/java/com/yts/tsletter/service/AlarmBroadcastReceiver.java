package com.yts.tsletter.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yts.tsletter.R;
import com.yts.tsletter.ui.activity.IntroActivity;
import com.yts.tsletter.utils.Keys;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmBroadcastReceiver", "onReceive");

        Intent intro = new Intent(context, IntroActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, Keys.PENDING_INTENT_ID_INTRO, intro, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_love_p_64px)
                .setContentTitle(context.getString(R.string.arrived_letter_title))
                .setContentText(context.getString(R.string.arrived_letter_content))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTICATION_ID, builder.build());
    }
}
