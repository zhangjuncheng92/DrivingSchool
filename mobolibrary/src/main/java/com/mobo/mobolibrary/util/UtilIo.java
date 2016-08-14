package com.mobo.mobolibrary.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/30.
 */
public class UtilIo {


    /**
     * 将json字符串保存到指定文件中
     *
     * @param json     json字符串
     * @param filePath 文件路径
     */
    public static void dumpJsonString(String json, String filePath) {

        final File jsonFile = new File(filePath);
        if (!jsonFile.exists()) {
            createFile(filePath);
        }
        try {
            final FileOutputStream fos = new FileOutputStream(jsonFile, true);
            final BufferedReader br = new BufferedReader(new StringReader(json));
            writeDate(fos);
            String line = null;
            while ((line = br.readLine()) != null) {
                byte[] buffer = line.getBytes();
                fos.write(buffer, 0, buffer.length);
            }
            br.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件，如果要创建的文件父路径不存在会创建父路径
     *
     * @param filePath 要创建的文件路径
     * @return 如果文件存在直接返回true，如果发生异常，或者创建失败，则返回false
     */
    public static boolean createFile(String filePath) {
        final File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        final File parent = file.getParentFile();
        try {
            if (!parent.exists()) {
                parent.mkdirs();
            }
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // 追加日期
    private static void writeDate(FileOutputStream fos) throws IOException {
        final byte[] enter = "\n".getBytes();
        final byte[] date = UtilDate.formatDate(new Date()).getBytes();

        fos.write(enter, 0, enter.length);
        fos.write(date, 0, date.length);
        fos.write(enter, 0, enter.length);
    }

    /**
     * 从输入流中读取byte数组
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] readData(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int len = -1;
        byte[] buff = new byte[1024];
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        byte[] result = os.toByteArray();
        is.close();
        os.close();
        return result;
    }

    /**
     * 从输入流中读取byte数组，此方法负责关闭传递过来的流。
     *
     * @param is 要读取的流
     * @return 从流中读取的字节数组
     * @throws IOException
     */
    public static byte[] readBytes(InputStream is) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        int len = -1;
        final byte[] buff = new byte[1024];
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        final byte[] result = os.toByteArray();
        is.close();
        os.close();
        return result;
    }

    /**
     * 从输入流中读取字符串，此方法负责关闭传递过来的流。
     *
     * @param is 要读取的流
     * @return 从流中读取的字符串
     * @throws IOException
     */
    public static String readString(InputStream is) throws IOException {
        return new String(readBytes(is), "utf-8");
    }

    /**
     * 将Stream写入到指定文件
     *
     * @param is       流
     * @param filePath 指定的文件路径
     * @return 成功返回true，失败返回false
     */
    public static boolean saveFile(InputStream is, String filePath) {
        final File file = new File(filePath);
        if (!createFile(file.getPath())) {
            return false;
        }
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = is.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            is.close();
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从文件中读取字符串
     *
     * @param path 文件路径
     * @return 读取的字符串或者NULL
     */
    public static String readStringFromPath(String path) {
        final File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            return readString(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到指定月份的天数
     */
    public static int getMonthOfDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static Map<String, String> typeMapping = new HashMap<String, String>();

    static {
        typeMapping.put("String", "VARCHAR");
        typeMapping.put("int", "INTEGER");
        typeMapping.put("Integer", "INTEGER");
        typeMapping.put("Object", "VARCHAR");
    }

    public static String getDbTypeFromTypeClass(Class<?> type) {
        String typeName = type.getSimpleName();
        return typeMapping.get(typeName);
    }

    /**
     * mData 原json数据 array 新增json数据 joinJSONArray 合并多个json数据
     */
    public static JSONArray joinJSONArray(JSONArray mData, JSONArray array) {
        StringBuffer buffer = new StringBuffer();
        try {
            int len = mData.length();
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) mData.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            len = array.length();
            if (len > 0)
                buffer.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            buffer.insert(0, "[").append("]");
            return new JSONArray(buffer.toString());
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressLint("DefaultLocale")
    public static int getResIdByName(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }

        Resources resources = context.getResources();
        int indentify = resources.getIdentifier(context.getPackageName() + ":string/" + name.toLowerCase(), null, null);
        if (indentify > 0) {
            return indentify;
        }

        return -1;
    }

    public static String getFileNameByUrl(String headimgpath) {
        String name = headimgpath.substring(headimgpath.lastIndexOf("/") + 1, headimgpath.length());
        System.out.println("name" + name);
        return name;
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


}
