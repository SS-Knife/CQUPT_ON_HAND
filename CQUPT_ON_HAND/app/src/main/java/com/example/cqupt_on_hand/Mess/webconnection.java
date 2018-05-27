package com.example.cqupt_on_hand.Mess;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

/**
 * Created by 郝书逸 on 2018/4/13.
 */
public class webconnection {
    private String path;
    private Bitmap bitmap;
    private String jsonData;
    private JsonAnalyze jsonAnalyze;

    public webconnection(String path, int linktype){
        this.path = path;
        getdetail_drawable();
    }
    public void getdetail_drawable() {
        new Thread() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    URL url = new URL(path);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    Log.d(TAG, "run: ");
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream;
                        inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }

    public Bitmap getBitmap(){return bitmap;}
}
