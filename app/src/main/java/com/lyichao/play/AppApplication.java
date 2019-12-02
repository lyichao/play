package com.lyichao.play;

import android.app.Application;
import android.content.Context;

import com.lyichao.play.di.component.AppComponent;
import com.lyichao.play.di.component.DaggerAppComponent;
import com.lyichao.play.di.module.AppModule;
import com.lyichao.play.di.module.HttpModule;

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    //获取AppApplication对象
    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    //获取mAppComponent对象
    public AppComponent getAppComponent(){

        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();

    }
}
