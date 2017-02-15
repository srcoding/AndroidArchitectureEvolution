package com.example.shaorui.androidarchitectureevolution.network.exception;


import java.io.IOException;

/**
 * Created by shaorui on 17/2/15.
 */
public class ServerException extends IOException {
    private int code;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public interface ExceptionType {
        /**
         * serve json转换异常
         */
        int JSON_EXCEPTION = 1;

        /**
         * serve token异常
         */
        int TOKEN_EXCEPTION = 401;

        /**
         * client response实体未用IResponse包裹
         */
        int RAW_TYPE_EXCETPTION = -1;

        /**
         * client 其他异常
         */
        int OTHER_EXCEPTION = -100;

    }
}
