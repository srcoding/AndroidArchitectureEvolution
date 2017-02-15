package com.example.shaorui.androidarchitectureevolution.network.rxfunc;

import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;

import rx.functions.Func1;

/**
 * Created by shaorui on 17/2/15.
 * rxjava map(new GetResponseDataFunc1<[JavaBean]>)
 * 获取response中data实体对象
 */
public class GetResponseDataFunc1<T> implements Func1<MyResponse<T>, T> {
    @Override
    public T call(MyResponse<T> myResponse) {
        return myResponse.getData();
    }
}
