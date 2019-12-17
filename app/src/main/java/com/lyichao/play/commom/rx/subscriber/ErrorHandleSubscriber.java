package com.lyichao.play.commom.rx.subscriber;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.lyichao.play.commom.exception.ApiException;
import com.lyichao.play.commom.exception.BaseException;
import com.lyichao.play.commom.exception.ErrorMessageFactory;
import com.lyichao.play.commom.rx.RxErrorHandle;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

public abstract class ErrorHandleSubscriber<T> extends DefaultSubscriber<T>{


    protected RxErrorHandle mRxErrorHandle = null;
    protected Context mContext;

    public ErrorHandleSubscriber(Context context){
        this.mContext = context;
        mRxErrorHandle = new RxErrorHandle(mContext);
    }

    @Override
    public void onError(Throwable e) {

        e.printStackTrace();

        BaseException exception = mRxErrorHandle.handleError(e);

        mRxErrorHandle.showErrorMessage(exception);

    }
}
