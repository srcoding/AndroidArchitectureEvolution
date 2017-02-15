package com.example.shaorui.androidarchitectureevolution.network.response;

/**
 * Created by shaorui on 17/2/15.
 */

public interface IResponse<T> {
    /**
     * 响应code
     * @return
     */
    int getCode();

    /**
     * 响应msg
     * @return
     */
    String getMessage();

    /**
     * 数据体
     * @return
     */
    T getData();

    /**
     * 是否成功
     * @return
     */
    boolean isSuccessful();
}
