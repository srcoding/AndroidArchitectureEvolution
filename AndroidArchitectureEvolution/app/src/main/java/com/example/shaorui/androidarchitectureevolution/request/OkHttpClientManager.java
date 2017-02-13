package com.example.shaorui.androidarchitectureevolution.request;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;

/**
 * Created by shaorui on 17/2/12.
 */
public class OkHttpClientManager {
    private static final String TAG = "OkHttpClientManager";

    private static final Object LOCK = new Object();

    private static OkHttpClientManager sOkHttpClientManager = null;

    private Handler mHandler;

    private OkHttpClient mOkHttpClient;

    private OkHttpClientManager() {
        mHandler = new Handler(Looper.myLooper());

        mOkHttpClient = new OkHttpClient();
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

    }

    public static OkHttpClientManager getInstance() {
        if (sOkHttpClientManager == null) {
            synchronized (LOCK) {
                sOkHttpClientManager = new OkHttpClientManager();
            }
        }
        return sOkHttpClientManager;
    }


    /**
     * 同步的Get请求
     *
     * @param url
     * @return Response
     */
    private Response _getAsyn(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return 字符串
     */
    private String _getAsString(String url) throws IOException {
        Response execute = _getAsyn(url);
        return execute.body().string();
    }


    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    private void _getAsyn(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }

    //*************对外公布的方法************

    public static Response getAsyn(String url) throws IOException {
        return getInstance()._getAsyn(url);
    }

    private void deliveryResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                sendFailedStringCallback(e, callback);
            }

            @Override
            public void onResponse(final Response response) {
                try {
                    final String string = response.body().string();
                    if (callback.mType == String.class) {
                        sendSuccessResultCallback(string, callback);
                    } else {
                        Object o = JSON.parseObject(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }


                } catch (IOException e) {
                    sendFailedStringCallback(e, callback);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Exception e, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(e);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }

    /*
     * mock
     */

    public <T> void execute(final String url, final ResultCallback<T> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "request url = " + url);
                SystemClock.sleep(1000);
                UserInfoBean responseBean = JSON.parseObject(UserInfoBean.MOCK_STR, callback.mType);
                runOnUIThread((T) responseBean);

            }

            private void runOnUIThread(final T responseBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(responseBean);
                    }
                });
            }
        }).start();
    }

    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            mType = getSuperClassTypeParameter(getClass());
        }

        public abstract void onError(Exception e);

        public abstract void onResponse(T response);

        private Type getSuperClassTypeParameter(Class<?> subClass) {
            Type mySuperClass = subClass.getGenericSuperclass();
            return ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
        }
    }
}
