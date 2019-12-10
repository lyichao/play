package com.lyichao.play.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

import com.lyichao.play.commom.rx.RxErrorHandle;
import com.lyichao.play.data.http.ApiService;
import com.lyichao.play.di.module.AppModule;
import com.lyichao.play.di.module.HttpModule;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandle getRxErrorHandle();


}
