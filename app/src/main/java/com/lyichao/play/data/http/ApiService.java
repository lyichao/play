package com.lyichao.play.data.http;

import com.lyichao.play.bean.AppInfo;
import com.lyichao.play.bean.BaseBean;
import com.lyichao.play.bean.PageBean;
import com.lyichao.play.bean.requestBean.LoginRequestBean;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";




//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @POST("login")
    public Observable<BaseBean> login(@Body LoginRequestBean bean);

    @FormUrlEncoded //FormBody
    @POST("login")
    public void login2(@Field("phone") String phone);

    @GET("toplist")
    public Observable<BaseBean> toplist(@Query("page") int page);

}
