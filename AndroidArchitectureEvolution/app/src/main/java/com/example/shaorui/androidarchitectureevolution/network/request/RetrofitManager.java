package com.example.shaorui.androidarchitectureevolution.network.request;


import com.example.shaorui.androidarchitectureevolution.network.converter.FastjsonConverterFactory;
import com.example.shaorui.androidarchitectureevolution.network.mock.UserInfoMockInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by shaorui on 17/2/15.
 *
 * //bean
 * class UserInfo {
 *     String name;
 *     ing age;
 *     String desc;
 * }
 *
 * //interface
 * interface UserInfoAPI {
 *     @Get("getInfo")
 *     Call<MyResponse<UserInfo>> getUserInfo(@Query("userId") String userId);
 * }
 *
 * Call<MyResponse<UserInfo>> call = RetrofitManager.getInstance().getRequestApi(UserInfoAPI.class).getUserInfo();
 * call.enquene(callback)/call.execute();
 *
 * 或者定义成支持rxjava
 * interface UserInfoAPI {
 *     @Get("getInfo")
 *     Observable<MyResponse<UserInfo>>  getUserInfo(@Query("userId") String userId);
 * }
 */
public class RetrofitManager {
    private static final String TAG = "RetrofitManager";

    //务必以/结尾
    public static final String BASE_URL = "https://test.meizu.com/serve/";

    private static final Object LOCK = new Object();

    private static RetrofitManager sInstance;

    private Retrofit mMyApiBuilder;

    private RetrofitManager() {
        //log intercept
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //default param intercept
        DefaultParamsInterceptor defaultParamsInterceptor = new DefaultParamsInterceptor();

        //mall
        OkHttpClient myOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logInterceptor)
                .addInterceptor(new UserInfoMockInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(defaultParamsInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        mMyApiBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(FastjsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(myOkHttpClient)
                .build();
    }

    public static RetrofitManager getInstance() {
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new RetrofitManager();
            }
            return sInstance;
        }
    }

    public <T> T getRequestApi(Class<T> tClass) {
        return mMyApiBuilder.create(tClass);
    }


    /**
     * 为每个请求都统一添加默认参数
     */
    private static class DefaultParamsInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            HttpUrl.Builder wrappedUrlBuilder = originalRequest.url()
                    .newBuilder()
                    .scheme(originalRequest.url().scheme())
                    .host(originalRequest.url().host())
                    .addQueryParameter(InnerConstant.KEY_IMEI, "imei")
                    .addQueryParameter(InnerConstant.KEY_APP_VERSION, "2.1")
                    .addQueryParameter(InnerConstant.KEY_NETWORK_TYPE, "wifi");

            Request authorizedRequest = originalRequest.newBuilder()
                    .method(originalRequest.method(), originalRequest.body())
                    .url(wrappedUrlBuilder.build())
                    .build();

            return chain.proceed(authorizedRequest);
        }

        /**
         * 默认参数的缩写
         */
        private interface InnerConstant {
            String KEY_IMEI = "i";
            String KEY_APP_VERSION = "av";
            String KEY_NETWORK_TYPE = "nt";
        }
    }

    /**
     * Retrofit请求的Callback
     * @param <T>
     */
    public interface ResultCallback<T> {

        void onFailure(Call<T> call, Throwable t);

        void onResponse(T response);
    }
}
