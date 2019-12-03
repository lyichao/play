package com.lyichao.play.di.module;


import android.app.ProgressDialog;

import com.lyichao.play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;
import com.lyichao.play.data.RecommendModel;
import com.lyichao.play.data.http.ApiService;
import com.lyichao.play.presenter.RecommendPresenter;
import com.lyichao.play.presenter.contract.RecommendContract;

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view){

        this.mView = view;
    }


    @Provides
    public RecommendContract.View providesView(){

        return mView;
    }

    @Provides
    public RecommendModel providesModel(ApiService apiService){
        return new RecommendModel(apiService);
    }

    @Provides
    public ProgressDialog providesProgressDialog(RecommendContract.View view){
        return new ProgressDialog(((RecommendFragment)view).getActivity());    }

}
