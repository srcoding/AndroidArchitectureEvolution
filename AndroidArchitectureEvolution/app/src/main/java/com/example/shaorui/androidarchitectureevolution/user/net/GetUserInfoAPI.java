package com.example.shaorui.androidarchitectureevolution.user.net;

import com.alibaba.fastjson.JSON;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shaorui on 17/2/15.
 * retrofit获取用户信息的接口
 */
public interface GetUserInfoAPI {

    @GET("getInfo")
    Observable<MyResponse<UserInfoBean>> getUserInfo(@Query("userId") String userId);

    /**
     * mock调试
     */
    class MockGetUserInfoAPI implements GetUserInfoAPI {
        private static final String FAKE_RESPONSE_JSON = "{\"name\":\"shaorui\"," +
                "\"age\":18," +
                "\"desc\":\"我爱打篮球\"}";
        @Override
        public Observable<MyResponse<UserInfoBean>> getUserInfo(@Query("userId") String userId) {
            MyResponse<UserInfoBean> response = new MyResponse<>();
            response.setCode(200);
            response.setMessage("success");
            response.setData(JSON.parseObject(FAKE_RESPONSE_JSON, UserInfoBean.class));
            return Observable.just(response);
        }
    }
}
