package com.lyichao.play.presenter;

import com.lyichao.play.ui.BaseView;

public class BasePresenter<M,V extends BaseView> {

    protected M mModel ;
    protected V mView ;

    public BasePresenter(M m,V v){
        this.mModel = m ;
        this.mView = v ;

    }

}
