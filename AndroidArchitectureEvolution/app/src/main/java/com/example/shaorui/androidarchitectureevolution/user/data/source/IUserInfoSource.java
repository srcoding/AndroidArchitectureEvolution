package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.request.OkHttpClientManager;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IUserInfoSource {
    void getUserInfo(String url, OkHttpClientManager.ResultCallback<UserInfoBean> callback);
}
