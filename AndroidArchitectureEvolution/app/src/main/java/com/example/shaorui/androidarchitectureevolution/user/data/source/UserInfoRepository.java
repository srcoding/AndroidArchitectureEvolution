package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.request.OkHttpClientManager;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoRepository implements IUserInfoSource {
    @Override
    public void getUserInfo(String url, OkHttpClientManager.ResultCallback<UserInfoBean> callback) {
        OkHttpClientManager.getInstance().execute(url, callback);
    }
}
