package com.lyichao.play.di.module;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.google.gson.Gson;
import com.lyichao.play.AppApplication;
import com.lyichao.play.commom.http.CommonParamsInterceptor;
import com.lyichao.play.commom.rx.RxErrorHandle;
import com.lyichao.play.data.http.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(Application application, Gson gson){



        // log用拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
//        SSLSocketFactory sslSocketFactory = null;

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
//                .addInterceptor(new HeadInterceptor())
                .addInterceptor(logging)
                .addInterceptor(new CommonParamsInterceptor(application,gson))
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

                .build();


    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }

    @Provides
    @Singleton
    public ApiService providesApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public RxErrorHandle providesErrorHandle(Application application){

        return new RxErrorHandle(application);
    }
}
