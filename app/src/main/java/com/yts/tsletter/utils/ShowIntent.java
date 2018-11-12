package com.yts.tsletter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.yalantis.ucrop.UCrop;
import com.yts.tsletter.R;

import java.io.File;

import androidx.core.content.ContextCompat;

public class ShowIntent {
    public static void imageSelect(Context context, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void imageCroup(Context context, Intent data, int requestCode) {
        if (data != null && data.getData() != null) {
            UCrop.Options options = new UCrop.Options();
            options.setToolbarColor(ContextCompat.getColor(context, R.color.white));
            options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
            options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
            options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.colorAccent));
            options.setToolbarTitle(context.getString(R.string.edit_image));

            String fileName = String.valueOf(System.currentTimeMillis()) + ".png";
            UCrop.of(data.getData(), Uri.fromFile(new File(context.getCacheDir(), fileName)))
                    .withOptions(options)
                    .start(((Activity) context), requestCode);
        }
    }

    public static void videoSelect(Context context, int requestCode) {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void audioSelect(Context context, int requestCode) {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public static void emailSend(Context context) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        String[] address = {context.getString(R.string.contact_email)};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.contact_us));
        try {
            context.startActivity(email);
        } catch (Exception e) {
            ToastMake.make(context, context.getString(R.string.error_email));
        }
    }

    public static void invite(Context context) {
        Intent intent = new AppInviteInvitation.IntentBuilder(context.getString(R.string.shared_title))
                .setMessage(context.getString(R.string.shared_message))
                .setDeepLink(Uri.parse(context.getString(R.string.invitation_deep_link)))
                .setCallToActionText(context.getString(R.string.shared_call_to_action_text))
                .build();
        ((Activity) context).startActivityForResult(intent, RequestCode.INVITE);
    }

    public static void reviews(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yts.tsletter"));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
