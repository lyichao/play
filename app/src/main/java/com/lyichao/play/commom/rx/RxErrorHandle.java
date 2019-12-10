package com.lyichao.play.commom.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.lyichao.play.commom.exception.ApiException;
import com.lyichao.play.commom.exception.BaseException;
import com.lyichao.play.commom.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

public class RxErrorHandle {

    private Context mContext;

    public RxErrorHandle(Context context){

        this.mContext = context;

    }

    public BaseException handleError(Throwable e){
        BaseException exception = new BaseException();

        if(e instanceof ApiException){

            exception.setCode(((ApiException) e).getCode());

        }else if(e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);

        }else if (e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);

        }else if (e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);

        }else if (e instanceof HttpException){
            exception.setCode(BaseException.HTTP_ERROR);

        }else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }

        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));

        return exception;

    }

    public void showErrorMessage(BaseException e){

        Toast.makeText(mContext, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }
}
