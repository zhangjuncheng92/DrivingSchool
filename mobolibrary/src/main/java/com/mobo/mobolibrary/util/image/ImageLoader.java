package com.mobo.mobolibrary.util.image;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mobo.mobolibrary.logs.Logs;


/**
 * 图片请求任务管理
 */
public class ImageLoader {
    private static ImageLoader me;

    static {
        me = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return me;
    }


    public void displayImage(SimpleDraweeView imageView, String uri) {
        Logs.i(uri);
        if (imageView != null && uri != null) {
            imageView.setImageURI(Uri.parse(uri));
        }
    }

}