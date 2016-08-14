package com.zjc.drivingschool.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.mobo.mobolibrary.logs.BasicUtils;
import com.mobo.mobolibrary.logs.Logs;
import com.zjc.drivingschool.app.MApp;
import com.zjc.drivingschool.db.SharePreferences.SharePreferencesUtil;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * HttpUtils which use asynchoronous method to help you use network method without
 * using an addtional Thread
 * <p>{@link #get(String, RequestParams, AsyncHttpResponseHandler)}</p>
 * <p>{@link #post(String, RequestParams, AsyncHttpResponseHandler)}</p>
 * <p>{@link #getUseCookie(Context, String, HashMap, AsyncHttpResponseHandler)}</p>
 * <p>{@link #getWithCookie(Context, String, RequestParams, AsyncHttpResponseHandler)}</p>
 * <p>{@link #postWithCookie(Context, String, RequestParams, AsyncHttpResponseHandler)}</p>
 * <p>{@link #postUseCookie(Context, String, HashMap, AsyncHttpResponseHandler)}</p>
 */
public class HttpUtilsAsync {
    private static AsyncHttpClient client;
    private static int timeout = 20 * 1000;
    private static int retry = 1;

    static {
        client = new AsyncHttpClient();
    }

    public static void setTimeout(int timeout) {
        client.setTimeout(timeout);
    }

    public static void setReTry(int retry) {
        HttpUtilsAsync.retry = retry;
    }

    public static AsyncHttpClient getInstance() {
        return client;
    }

    /**
     * Perform a HTTP GET request with {@link RequestParams}
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.getHttpClient();
        client.setTimeout(timeout);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Perform a HTTP GET request with {@link RequestParams}
     *公共API的的请求方式
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void getEducationHealth(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("apikey","ab34223db3982366975167f31126d3a3");
        client.getHttpClient();
        client.setTimeout(timeout);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Perform a HTTP POST request with {@link RequestParams}
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setMaxRetriesAndTimeout(retry, timeout);
        if (SharePreferencesUtil.getInstance().isLogin()) {
            params.put("token", SharePreferencesUtil.getInstance().readUser().getToken());
        }
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Logs.i("HttpUtilsAsync", url.toString() + "?" + params.toString());
    }


    /**
     * Perform a HTTP POST request with {@link RequestParams}
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void postInfinite(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(90*1000);
        if (SharePreferencesUtil.getInstance().isLogin()) {
            params.put("token", SharePreferencesUtil.getInstance().readUser().getToken());
        }
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Logs.i("HttpUtilsAsync", url.toString() + "?" + params.toString());
    }

    /**
     * Perform a HTTP GET request, without any parameters.
     *
     * @param url
     * @param responseHandler
     */
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(timeout);
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    /**
     * Perform a HTTP GET request with cookie which generate by own context
     *
     * @param context
     * @param url
     * @param responseHandler
     */
    public static void getWithCookie(Context context, String url, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        //  myCookieStore.clear();
        client.setCookieStore(myCookieStore);
        client.get(getAbsoluteUrl(url), responseHandler);
//        Iterator iterator = myCookieStore.getCookies().iterator();
//        while (iterator.hasNext()) {
//            Cookie cookie = (Cookie) iterator.next();
//            Logs.d("cookie name--" + cookie.getName());
//            Logs.d("cookie value--" + cookie.getValue());
//        }

//        BasicCookieStore basicCookieStore = new BasicCookieStore();
//
//        client.setCookieStore(basicCookieStore);
//        client.get(getAbsoluteUrl(url), responseHandler);
//        Iterator iterator = basicCookieStore.getCookies().iterator();
//        while (iterator.hasNext()) {
//            Cookie cookie = (Cookie) iterator.next();
//            Logs.d("sssss" + cookie.getName());
//            Logs.d("sssss" + cookie.getValue());
//        }


    }

    /**
     * Perform a HTTP GET request with cookies which are defined in hashmap
     *
     * @param context
     * @param url
     * @param hashMap
     * @param responseHandler
     */
    public static void getUseCookie(Context context, String url, HashMap hashMap, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        if (BasicUtils.judgeNotNull(hashMap)) {
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Cookie cookie = new BasicClientCookie(key.toString(), value.toString());
                myCookieStore.addCookie(cookie);
            }
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.setCookieStore(myCookieStore);
        client.get(getAbsoluteUrl(url), responseHandler);
    }


    /**
     * Perform a HTTP GET request with cookie which generate by own context
     *
     * @param context
     * @param url
     * @param responseHandler
     */
    public static void getWithCookie(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        client.setCookieStore(myCookieStore);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Perform a HTTP POST request, without any parameters.
     *
     * @param url
     * @param responseHandler
     */
    public static void post(String url, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), responseHandler);
    }

    /**
     * Perform a HTTP POST request with cookie which generate by own context
     *
     * @param url
     * @param responseHandler
     */
    public static void postWithCookie(String url, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MApp.getInstance());
        myCookieStore.clear();
        client.setCookieStore(myCookieStore);
        client.post(getAbsoluteUrl(url), responseHandler);
    }

    /**
     * Perform a HTTP POST request with cookie which generate by own context
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void postWithCookie(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MApp.getInstance());
        client.setCookieStore(myCookieStore);
        client.post(getAbsoluteUrl(url), params, responseHandler);
        Logs.i("HttpUtilsAsync", url.toString() + "?" + params.toString());
    }

    /**
     * Perform a HTTP POST request with cookies which are defined in hashmap
     *
     * @param url
     * @param hashMap
     * @param responseHandler
     */
    public static void postUseCookie(String url, HashMap hashMap, AsyncHttpResponseHandler responseHandler) {
        PersistentCookieStore myCookieStore = new PersistentCookieStore(MApp.getInstance());
        if (BasicUtils.judgeNotNull(hashMap)) {
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Cookie cookie = new BasicClientCookie(key.toString(), value.toString());
                myCookieStore.addCookie(cookie);
            }
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.setCookieStore(myCookieStore);
        client.post(getAbsoluteUrl(url), responseHandler);
    }

    /**
     * To get the true url.
     * If you want to use some relative url,you should override this method.
     *
     * @param relativeUrl
     * @return the absolute url
     */
    protected static String getAbsoluteUrl(String relativeUrl) {
        return relativeUrl;
    }

    /**
     * To get the url with params.
     *
     * @param originUrl
     * @param hashMap
     * @return the url with params
     */
    public static String getUrlFromHashMap(String originUrl, HashMap hashMap) {
        String returnUrl = originUrl;
        if (BasicUtils.judgeNotNull(hashMap)) {
            returnUrl = returnUrl + "?";
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                returnUrl += key + "=" + value + "&";
            }
            if (returnUrl.endsWith("&")) {
                returnUrl = returnUrl.substring(0, returnUrl.length() - 1);
            }
        }
        Logs.d(returnUrl);
        return returnUrl;
    }

    /**
     * Upload files with {@link SyncHttpClient}
     *
     * @param url
     * @param paramsList
     * @param fileParams
     * @param files
     * @param responseHandler
     */
    public static void uploadFiles(String url, List<NameValuePair> paramsList, String fileParams, List<File> files, AsyncHttpResponseHandler responseHandler) throws Exception {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        RequestParams params = new RequestParams();

        if (BasicUtils.judgeNotNull(paramsList)) {
            for (NameValuePair nameValuePair : paramsList) {
                params.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        if (BasicUtils.judgeNotNull(files))
            params.put(fileParams, files);

        syncHttpClient.setTimeout(timeout);
        syncHttpClient.post(url, params, responseHandler);
    }

    /**
     * Upload file with {@link SyncHttpClient}
     *
     * @param url
     * @param paramsList
     * @param fileParams
     * @param file
     * @param responseHandler
     * @throws FileNotFoundException
     */
    public static void uploadFile(String url, List<NameValuePair> paramsList, String fileParams, File file, AsyncHttpResponseHandler responseHandler) throws FileNotFoundException {
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        RequestParams params = new RequestParams();
        if (BasicUtils.judgeNotNull(paramsList)) {
            for (NameValuePair nameValuePair : paramsList) {
                params.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        if (BasicUtils.judgeNotNull(file))
            params.put(fileParams, file);
        syncHttpClient.setTimeout(timeout);
        syncHttpClient.post(url, params, responseHandler);
    }
}
