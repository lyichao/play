package com.lyichao.play.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    //appModule构造方法
    public AppModule(Application application){
        this.mApplication = application;

    }

    //提供application实例对象
    //因为是全局使用，所以只需要初始化一次就行，也就是只需单例就好
    @Provides
    @Singleton
    public Application providesApplication(){
        //因为android系统本身就会对application对象进行初始化，
        //所以也就没办法通过new出来，因此只能通过构造方法的方式带进来
        return mApplication;
    }

    @Provides
    @Singleton
    public Gson providesGson(){
        return new Gson();
    }
}
