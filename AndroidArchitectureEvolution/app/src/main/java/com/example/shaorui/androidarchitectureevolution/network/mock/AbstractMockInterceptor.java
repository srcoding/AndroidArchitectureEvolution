package com.example.shaorui.androidarchitectureevolution.network.mock;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shaorui on 17/2/16.
 * 仅用于mock调试
 *
 * okHttpClient.addIntercept(new MockIntercept())
 */
public abstract class AbstractMockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(chain.request().url().uri().toString().startsWith(interceptUrl())) {
            return new Response.Builder()
                    .code(200)
                    .message("success")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
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
