package com.lyichao.play.commom.exception;

public class ApiException extends BaseException{
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
