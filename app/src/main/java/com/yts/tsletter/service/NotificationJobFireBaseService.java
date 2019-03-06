package com.yts.tsletter.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.yts.tsletter.data.realm.RealmService;
import com.yts.tsletter.utils.DateFormat;
import com.yts.tsletter.utils.Keys;
import com.yts.tsletter.utils.SharedPrefsUtils;

import java.util.Calendar;

import io.realm.Realm;


public class NotificationJobFireBaseService extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d("NotificationJobService", "onStartJob");

        long createNotiTime = SharedPrefsUtils.getLongPreference(this, Keys.CREATE_NOTI_TIME, 0);
        long currentTime = System.currentTimeMillis();

        if (SharedPrefsUtils.getBooleanPreference(this, Keys.ENABLE_ALARM, true) &&
                !DateFormat.isCompareDay(createNotiTime, currentTime) &&
                RealmService.getWriteListSize(Realm.getDefaultInstance(), System.currentTimeMillis()) > 0) {
            SharedPrefsUtils.setLongPreference(this, Keys.CREATE_NOTI_TIME, currentTime);

            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Intent intent = new Intent(this, AlarmBroadcastReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Keys.PENDING_INTENT_ID_ALARM_BROADCAST_RECEIVER, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                manager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else {
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }

        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // Answers the question: "Should this job be retried?"
    }
}
