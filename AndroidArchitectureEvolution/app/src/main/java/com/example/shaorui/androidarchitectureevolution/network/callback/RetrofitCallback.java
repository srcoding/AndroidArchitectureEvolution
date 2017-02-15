package com.example.shaorui.androidarchitectureevolution.network.callback;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shaorui on 17/2/15.
 * 此Callback只在不用rxjava时使用
 */
public class RetrofitCallback<T> implements Callback<T> {

    private RetrofitManager.ResultCallback<T> mResultCallback;

    public RetrofitCallback(RetrofitManager.ResultCallback<T> resultCallback) {
        if (resultCallback == null) {
            throw new NullPointerException("resultCallback can not be null");
        }
        mResultCallback = resultCallback;
    }
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        mResultCallback.onResponse(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mResultCallback.onFailure(call, t);
    }
}
