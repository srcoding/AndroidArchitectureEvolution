package com.example.shaorui.androidarchitectureevolution.network.mock;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;

/**
 * Created by shaorui on 17-2-17.
 */

public class UserInfoMockInterceptor extends AbstractMockInterceptor {
    @Override
    protected String interceptUrl() {
        return RetrofitManager.BASE_URL + "getInfo";
    }

    @Override
    protected String mockResponse() {
        return "{\"code\":200," +
                "\"message\":\"success\"," +
                "\"data\":{" +
                "\"name\":\"shaorui\"," +
                "\"age\":18," +
                "\"desc\":\"我喜欢打篮球\"}" +
                "}";
    }
}
