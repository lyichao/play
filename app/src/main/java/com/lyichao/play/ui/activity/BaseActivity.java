package com.lyichao.play.ui.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.lyichao.play.AppApplication;
import com.lyichao.play.R;
import com.lyichao.play.di.component.AppComponent;
import com.lyichao.play.presenter.BasePresenter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    private Unbinder mUnbinder;

    private AppApplication mApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //自定义字体库
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        mUnbinder = ButterKnife.bind(this);
        this.mApplication = (AppApplication) getApplication();

        setupActivityComponent(mApplication.getAppComponent());

        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

//    protected void startActivity(Class c){
//        this.startActivity(new Intent(this,c));
//    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();
}
