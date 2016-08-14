package com.mobo.mobolibrary.util;

import java.util.List;

/**
 * Created by Administrator on 2015/5/30.
 */
public class UtilUri {


    /**
     * 转换为uri
     */
    public static String getLocalUri(String filepath) {
        return "file://" + filepath;
    }

    /**
     * 批量转换为uri
     */
    public static List<String> getLocalUri(List<String> filepath) {
        for (int i = 0; i < filepath.size(); i++) {
            String beforeUri = filepath.get(i);
            String afterUri = getLocalUri(beforeUri);
            filepath.set(i, afterUri);
        }
        return filepath;
    }

    /**
     * 转换为uri
     */
    public static String getPathByUri(String filepath) {
        return filepath.replace("file://", "");
    }
}
