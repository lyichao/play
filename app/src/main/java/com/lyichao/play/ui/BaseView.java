package com.lyichao.play.ui;

public interface BaseView {

    //显示加载窗
    void showLoading();
    //隐藏加载窗
    void dismissLoading();
    //
    void showError(String msg);

}
