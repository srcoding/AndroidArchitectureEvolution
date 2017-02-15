package com.example.shaorui.androidarchitectureevolution.network.exception;

/**
 * Created by shaorui on 17/2/15.
 */

public class TokenException extends ServerException {

    public TokenException() {
        super(ExceptionType.TOKEN_EXCEPTION, "Invalid Token");
    }
}
