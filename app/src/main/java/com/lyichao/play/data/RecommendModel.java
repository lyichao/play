package com.lyichao.play.data;

import com.lyichao.play.bean.AppInfo;
import com.lyichao.play.bean.BaseBean;
import com.lyichao.play.bean.PageBean;
import com.lyichao.play.data.http.ApiService;
import retrofit2.Callback;
import rx.Observable;

public class RecommendModel {

    private ApiService mApiService;

    public RecommendModel(ApiService apiService){

        this.mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps(){

//        HttpManager manager = new HttpManager();
//
//        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);


//        mApiService.getApps("{'page':0}").enqueue(callback);

        return  mApiService.getApps("{'page':0}");
    }
}
