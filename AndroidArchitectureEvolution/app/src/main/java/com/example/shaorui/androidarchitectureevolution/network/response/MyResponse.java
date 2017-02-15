package com.example.shaorui.androidarchitectureevolution.network.response;

/**
 * Created by shaorui on 17/2/15.
 * {
 * "code":200,     // (int)必选    返回码
 * "message":"",   // (String) 可选   错误信息返回的说明
 * "data":"",      // (object) 必选   数据信息
 * }
 */

public class MyResponse<T> implements IResponse<T> {

    public static final int CODE_OK = 200;

    private int code;
    private String message;
    private T data;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccessful() {
        return code == CODE_OK;
    }

}
