package com.example.cqupt_on_hand.Mess.ImageStorage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.example.cqupt_on_hand.Mess.webconnection;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * Created by 郝书逸 on 2018/4/13.
 */

public class ImageStorage{
    private String path;
    private ImageView imageView;
    private static LruCache<String, Bitmap> mCache;
    private static Handler mHandler;
    private static ExecutorService mThreadPool;
    private Context mContext;
    public ImageStorage(Context context){
        mContext = context;
    }
    public void setup(String path, ImageView imageView){
        if (mCache == null){
            // 最大使用的内存空间
            int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8192);
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }
        /*if (mHandler == null) {
            mHandler = new Handler();
        }
        if (mThreadPool == null) {
            // 最多同时允许的线程数为3个
            mThreadPool = Executors.newFixedThreadPool(3);
        }*/
        display(path,imageView);
    }
    private void display(String path, ImageView imageView) {
        // 1.去内存中取
        Bitmap bitmap = mCache.get(path);
        if (bitmap != null) {
            // 直接显示
            imageView.setImageBitmap(bitmap);
            return;
        }
// TODO: 2018/4/13 硬盘存
        // 2.去硬盘上取
        bitmap = loadBitmapFromLocal(path);
        if (bitmap != null) {
            // 直接显示
            imageView.setImageBitmap(bitmap);
            return;
        }

        // 3. 去网络获取图片
        loadBitmapFromNet(path,imageView);
    }

    private Bitmap loadBitmapFromLocal(String url) {
        File file = new File(getCacheDir(), url);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            mCache.put(url, bitmap);
            return bitmap;
        }
        return null;
    }
    private static void loadBitmapFromNet(String path,ImageView imageView) {
        webconnection webconnection = new webconnection(path,1);
        Bitmap bitmap = webconnection.getBitmap();
        imageView.setImageBitmap(bitmap);
        //mCache.put(path,bitmap);
    }
    private String getCacheDir() {
        String state = Environment.getExternalStorageState();
        File dir = null;
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            dir = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + mContext.getPackageName()
                    + "/picTest");
        } else {
            dir = new File(mContext.getCacheDir(), "/picTest");
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }

}
