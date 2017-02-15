package com.example.shaorui.androidarchitectureevolution.network.mock;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shaorui on 17/2/16.
 * 仅用于mock调试
 *
 * okHttpClient.addIntercept(new MockIntercept())
 */
public abstract class AbstractMockIntercept implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(chain.request().url().uri().getPath().startsWith(interceptUrl())) {
            return new Response.Builder()
                    .code(200)
                    .message(mockResponse())
                    .body(ResponseBody.create(MediaType.parse("application/json"), mockResponse().getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            return chain.proceed(chain.request());
        }
    }

    protected abstract String interceptUrl();

    protected abstract String mockResponse();
}
