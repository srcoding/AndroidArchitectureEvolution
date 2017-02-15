package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IUserInfoSource {
    void getUserInfo(String userId, RetrofitManager.ResultCallback<MyResponse<UserInfoBean>> callback);
}
