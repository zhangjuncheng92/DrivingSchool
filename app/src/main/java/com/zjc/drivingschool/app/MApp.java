package com.zjc.drivingschool.app;

import android.os.Environment;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.mobo.mobolibrary.app.BaseApplication;
import com.mobo.mobolibrary.logs.Logs;
import com.zjc.drivingschool.api.HttpUtilsAsync;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;
import com.zjc.drivingschool.db.model.City;
import com.zjc.drivingschool.db.model.LatLngLocal;
import com.zjc.drivingschool.jpush.JPushUtil;
import com.zjc.drivingschool.utils.Constants;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

public class MApp extends BaseApplication {
    private static MApp me;

    public static MApp getInstance() {
        return me;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        me = this;
        setIsDebug(true);
        createFile();
        initFresco();
        initUniversalImage();
        JPushUtil.initJPush();
        SDKInitializer.initialize(this);

        if (SharePreferencesUtil.getInstance().readCity().getLatLngLocal() == null) {
            SharePreferencesUtil.getInstance().saveCity(new City("1", "武汉市", new LatLngLocal(30.543622, 114.433890)));
        }
    }

    private void initUniversalImage() {
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
//                .memoryCacheSize(2 * 1024 * 1024)
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
//                .diskCache(new UnlimitedDiscCache(new File(SyncStateContract.Constants.DIR_IMAGE)))
//                .defaultDisplayImageOptions(ImageOptions.getDisplayImageOptions())
//                .build();
//        ImageLoader.getInstance().init(config);
    }

    private void setIsDebug(boolean b) {
        JPushInterface.setDebugMode(b);    // 设置开启日志,发布时请关闭日志'
        Logs.setsIsLogEnabled(b);
        HttpUtilsAsync.getInstance().setLoggingEnabled(b);
    }

    private void initFresco() {
        //默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())//缓存图片基路径
                .setBaseDirectoryName(Constants.DIR_FRESCO)//文件夹名
//            .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
//            .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
//            .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
                .setMaxCacheSize(1024 * 1024 * 100)//默认缓存的最大大小。
                .build();
//
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, config);
    }

    private void createFile() {
        File temp = new File(Constants.BASE_DIR);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_CACHE);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_IMAGE);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
        temp = new File(Constants.DIR_FRESCO);// 自已项目 文件夹
        if (!temp.exists()) {
            temp.mkdir();
        }
    }
}
