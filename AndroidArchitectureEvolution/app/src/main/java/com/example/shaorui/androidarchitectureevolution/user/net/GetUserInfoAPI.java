package com.example.shaorui.androidarchitectureevolution.user.net;

import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shaorui on 17/2/15.
 */
public interface GetUserInfoAPI {

    @GET("getInfo")
    Call<MyResponse<UserInfoBean>> getUserInfo(@Query("userId") String userId);
}
