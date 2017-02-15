package com.example.shaorui.androidarchitectureevolution.network.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.example.shaorui.androidarchitectureevolution.network.exception.TokenException;
import com.example.shaorui.androidarchitectureevolution.network.exception.ResponseException;
import com.example.shaorui.androidarchitectureevolution.network.exception.ServerException;
import com.example.shaorui.androidarchitectureevolution.network.response.IResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by shaorui on 17/2/15.
 */
final class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset;

    public FastjsonResponseBodyConverter() {
    }

    public FastjsonResponseBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String valueString = value.string();

            Type rawType = getRawType(type);
            Class<?> rawTypeClass = (Class<?>) rawType;

            //统一限制response返回的数据被fastJson解析成对象的必须是IResponse子类
            if (IResponse.class.isAssignableFrom(rawTypeClass)) {
                T t = JSON.parseObject(valueString, type);
                IResponse response = (IResponse) t;

                if (response.getCode() == ServerException.ExceptionType.TOKEN_EXCEPTION) {
                    //401token异常
                    throw new TokenException();
                } else if (response.isSuccessful()) {
                    //200
                    return t;
                } else {
                    //非200异常，客户端根据responseCode判断进行不同的展示
                    throw new ResponseException(response);
                }
            } else {
                //未用IResponse实现类包裹
                throw new ServerException(ServerException.ExceptionType.RAW_TYPE_EXCETPTION, "请用IResponse的实现类对返回数据进行包裹");
            }

        } catch (JSONException exception) {
            //json转换异常
            throw new ServerException(ServerException.ExceptionType.JSON_EXCEPTION, "Parse Error" + exception.getMessage());
        } finally {
            value.close();
        }
    }

    /**
     * 获取泛型的类型
     * @param type
     * @return
     */
    private static Type getRawType(Type type) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return ((ParameterizedType) type).getRawType();
        } else {
            return type;
        }
    }
}
