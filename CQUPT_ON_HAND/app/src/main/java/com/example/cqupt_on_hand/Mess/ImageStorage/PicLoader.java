package com.example.cqupt_on_hand.Mess.ImageStorage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PicLoader {
    private LruCache<String, Bitmap> lruCache;
    private Context mContext;
    private Handler mHandler;

    public PicLoader(Context context) {
        this.mContext = context;
        if (mHandler == null) {
            mHandler = new Handler();
        }
        if (lruCache == null) {
            int maxSize = (int) (Runtime.getRuntime().freeMemory() / 4);
            lruCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }
    }

    public void show(ImageView imageView, String Url) {
        Bitmap bitmap = lruCache.get(Url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        bitmap = loadFromDisc(Url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        loadBitmapFromNet(imageView, Url);

    }


    private Bitmap loadFromDisc(String url) {
        InputStream inputStream = null;
        String[] fileNames = url.split("/");
        String fileName = fileNames[fileNames.length-1];
        try {
            inputStream = new FileInputStream(mContext.getCacheDir().getPath()+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    private void loadBitmapFromNet(final ImageView imageView, final String Url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    final URL url = new URL(Url);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("GET");
                    InputStream in = connection.getInputStream();
                    if (connection.getResponseCode() == 200) {
                        final Bitmap bitmap = BitmapFactory.decodeStream(in);
                        lruCache.put(Url, bitmap);
                        saveToDisc(Url, bitmap);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                show(imageView, Url);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    private void saveToDisc(String url, Bitmap bitmap) {
        OutputStream outputStream = null;
        BufferedOutputStream bos = null;
        InputStream inputStream = null;
        File file = new File(mContext.getCacheDir().getPath());
        file.mkdirs();
        try {
            String[] fileNames = url.split("/");
            String fileName = fileNames[fileNames.length - 1];
            outputStream = new FileOutputStream(mContext.getCacheDir().getPath() + fileName);
            bos = new BufferedOutputStream(outputStream);
            /**
             * 以下三行的代码表示：
             * 将Bitmap对象转换为inputStream
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            inputStream = new ByteArrayInputStream(baos.toByteArray());

            byte[] data = new byte[1024];
            while (inputStream.read(data) != -1) {
                bos.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                outputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





}
