package com.example.shaorui.androidarchitectureevolution.network.rxfunc;

import com.example.shaorui.androidarchitectureevolution.network.exception.TokenException;

import rx.functions.Func2;

/**
 * Created by shaorui on 17/2/15.
 * rxjava retry(new InvalidTokenRetryFunc2)
 * 请求失败时重新尝试一次请求
 */

public class InvalidTokenRetryFunc2 implements Func2<Integer, Throwable, Boolean> {
    @Override
    public Boolean call(Integer integer, Throwable throwable) {
        boolean needRetry = false;
        if (throwable instanceof TokenException && integer == 1) {
            // TODO: 17/2/15 清除缓存token
            needRetry = true;
        }
        return needRetry;
    }
}
