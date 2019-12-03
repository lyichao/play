package com.lyichao.play.presenter;

import java.util.List;

import com.lyichao.play.bean.AppInfo;
import com.lyichao.play.bean.PageBean;
import com.lyichao.play.data.RecommendModel;
import com.lyichao.play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {

        mView.showLoading();

        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {

                if(response != null){
                    mView.showResult((List<AppInfo>) response.body().getDatas());
                }else {
                    mView.showNoDatas();
                }
                mView.dimissLoading();

            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {

                mView.dimissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
