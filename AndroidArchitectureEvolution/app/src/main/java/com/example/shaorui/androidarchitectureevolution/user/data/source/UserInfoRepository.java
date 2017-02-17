package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.net.GetUserInfoAPI;

import retrofit2.Call;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoRepository implements IUserInfoSource {

    @Override
    public Call<MyResponse<UserInfoBean>> getUserInfo(String userId) {
        return RetrofitManager.getInstance().getRequestApi(GetUserInfoAPI.class).getUserInfo(userId);
    }
}
