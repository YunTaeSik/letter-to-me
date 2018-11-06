package com.yts.tsletter.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;

import com.google.android.gms.common.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CreateFile {
    public static File createVideo(Context context, Uri uri) {
        try {
            ContextWrapper contextWrapper = new ContextWrapper(context);
            File file = contextWrapper.getCacheDir(); // 프로바이더의 이름이 같아야함 provider.xml
            if (!file.exists()) {
                file.mkdirs();
            }
            File video = new File(file, System.currentTimeMillis() + ".mp4");

            InputStream inputStream = context.getContentResolver().openInputStream(uri);

            OutputStream out = new FileOutputStream(video);
            out.write(IOUtils.readInputStreamFully(inputStream));
            out.close();
            inputStream.close();
            return video;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
