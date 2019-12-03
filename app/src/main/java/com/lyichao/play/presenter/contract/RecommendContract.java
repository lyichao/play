package com.lyichao.play.presenter.contract;

import com.lyichao.play.ui.BaseView;

import com.lyichao.play.bean.AppInfo;
import com.lyichao.play.presenter.BasePresenter;
import java.util.List;

public interface RecommendContract {

    interface View extends BaseView{

        //显示结果
        void showResult(List<AppInfo> datas);
        //无数据
        void showNoDatas();
        //报错、异常
        void showError(String msg);

    }



}
