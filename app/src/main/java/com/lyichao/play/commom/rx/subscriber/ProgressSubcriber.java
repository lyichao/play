package com.lyichao.play.commom.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.lyichao.play.commom.exception.BaseException;
import com.lyichao.play.commom.rx.RxErrorHandle;
import com.lyichao.play.ui.BaseView;

public abstract class ProgressSubcriber<T> extends ErrorHandleSubscriber<T>{



    private BaseView mView;

    public ProgressSubcriber(Context context, BaseView view){
        super(context);
        this.mView = view;
    }

    @Override
    public void onStart() {
        if(isShowDialog()){
            mView.showLoading();

        }
    }

    @Override
    public void onCompleted() {
        mView.dismissLoading();

    }

    @Override
    public void onError(Throwable e) {
//        super.onError(e);
        BaseException baseException = mRxErrorHandle.handleError(e);

        mView.showError(baseException.getDisplayMessage());
    }



    protected boolean isShowDialog(){

        return true;
    }


}
