package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

import retrofit2.Call;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IUserInfoSource {
    Call<MyResponse<UserInfoBean>> getUserInfo(String userId);
}
