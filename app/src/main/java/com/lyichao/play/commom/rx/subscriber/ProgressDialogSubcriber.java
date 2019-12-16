package com.lyichao.play.commom.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;

import com.lyichao.play.commom.rx.RxErrorHandle;
import com.lyichao.play.ui.BaseView;

public abstract class ProgressDialogSubcriber<T> extends ErrorHandleSubscriber<T>{

    Context mContext;

    private ProgressDialog mProgressDialog;

    private BaseView mBaseView;

    public ProgressDialogSubcriber(BaseView view, RxErrorHandle rxErrorHandle){
        super(rxErrorHandle);
        this.mBaseView = view;
    }

    @Override
    public void onStart() {
        if(isShowDialog()){
            showProgressDialog();

        }
    }

    @Override
    public void onCompleted() {
        if(isShowDialog()){
            dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if(isShowDialog()){
            dismissProgressDialog();
        }
    }



    protected boolean isShowDialog(){

        return true;
    }

    private void showProgressDialog(){
        mBaseView.showLoading();
    }

    private void dismissProgressDialog(){
        mBaseView.dimissLoading();
    }
}
