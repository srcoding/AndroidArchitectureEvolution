package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.network.callback.RetrofitCallback;
import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.net.GetUserInfoAPI;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoRepository implements IUserInfoSource {

    @Override
    public void getUserInfo(String userId, final RetrofitManager.ResultCallback<MyResponse<UserInfoBean>> callback) {
        RetrofitManager.getInstance().getRequestApi(GetUserInfoAPI.class).getUserInfo(userId)
                .enqueue(new RetrofitCallback<>(callback));
    }
}
