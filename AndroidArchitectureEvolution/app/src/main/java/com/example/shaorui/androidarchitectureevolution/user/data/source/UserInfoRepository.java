package com.example.shaorui.androidarchitectureevolution.user.data.source;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.net.GetUserInfoAPI;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoRepository implements IUserInfoSource {

    @Override
    public Observable<MyResponse<UserInfoBean>> getUserInfo(String userId) {
        return new GetUserInfoAPI.MockGetUserInfoAPI().getUserInfo(userId).delay(1000, TimeUnit.MILLISECONDS);
//        return RetrofitManager.getInstance().getRequestApi(GetUserInfoAPI.class).getUserInfo(userId);
    }
}
