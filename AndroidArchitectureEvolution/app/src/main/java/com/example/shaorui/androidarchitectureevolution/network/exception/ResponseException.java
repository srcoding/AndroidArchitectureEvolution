package com.example.shaorui.androidarchitectureevolution.network.exception;

import com.example.shaorui.androidarchitectureevolution.network.response.IResponse;

/**
 * Created by shaorui on 2017/2/15.
 * 返回code非200，但非报错异常
 * 客户端可能需要根据code判断进行不同的展示
 */
public class ResponseException extends ServerException {

    private IResponse mResponse;

    public ResponseException(IResponse response) {
        super(response.getCode(), response.getMessage());
        mResponse = response;
    }

    public IResponse getResponse() {
        return mResponse;
    }
}
