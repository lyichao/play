package com.lyichao.play.presenter;

import java.util.List;

import com.lyichao.play.bean.AppInfo;
import com.lyichao.play.bean.PageBean;
import com.lyichao.play.commom.rx.RxHttpResponseCompat;
import com.lyichao.play.data.RecommendModel;
import com.lyichao.play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecommendPresenter extends BasePresenter<RecommendModel,RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {

        mModel.getApps()
//                //被订阅者切换到io线程中，处理网络请求（耗时操作）
//                .subscribeOn(Schedulers.io())
//                //订阅者切换到主线程中更新数据
//                .observeOn(AndroidSchedulers.mainThread())

                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new Subscriber<PageBean<AppInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {

                    }
                });

//        mView.showLoading();
//
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//
//                if(response != null){
//                    mView.showResult((List<AppInfo>) response.body().getDatas());
//                }else {
//                    mView.showNoDatas();
//                }
//                mView.dimissLoading();
//
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//
//                mView.dimissLoading();
//                mView.showError(t.getMessage());
//            }
//        });
    }
}
