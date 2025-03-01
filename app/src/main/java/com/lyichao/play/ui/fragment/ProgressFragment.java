package com.lyichao.play.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lyichao.play.AppApplication;
import com.lyichao.play.R;
import com.lyichao.play.di.component.AppComponent;
import com.lyichao.play.presenter.BasePresenter;
import com.lyichao.play.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView{


    private FrameLayout mRootView;
    private View mViewProgress;
    private FrameLayout mViewContent;
    private View mViewEmpty;
    private Unbinder mUnbinder;
    private TextView mTextError;
    private AppApplication mApplication;

    @Inject
    T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress,container,false);
        mViewProgress = mRootView.findViewById(R.id.view_progress);
        mViewContent = mRootView.findViewById(R.id.view_content);
        mViewEmpty = mRootView.findViewById(R.id.view_empty);
        mTextError = mRootView.findViewById(R.id.text_tip);

        mViewEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmptyViewClick();
            }
        });

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (AppApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        setRealContentView();
        init();


    }

    public void onEmptyViewClick(){

    }

    private void setRealContentView() {

        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(),mViewContent,true);
        mUnbinder  = ButterKnife.bind(this, realContentView);

    }

    public void showProgressView(){
//        mViewProgress.setVisibility(View.VISIBLE);
//        mViewContent.setVisibility(View.GONE);
//        mViewEmpty.setVisibility(View.GONE);

        showView(R.id.view_progress);

    }

    public void showContentView(){
        showView(R.id.view_content);
    }

    public void showEmptyView(){
        showView(R.id.view_empty);
    }

    public void showEmptyView(int resId){
        showEmptyView();
        mTextError.setText(resId);

    }

    public void showEmptyView(String msg){
        showEmptyView();
        mTextError.setText(msg);

    }

    //判断显示/隐藏对应的view
    public void showView(int viewId){
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if(mRootView.getChildAt(i).getId() == viewId){
                mRootView.getChildAt(i).setVisibility(View.VISIBLE);
            }else {
                mRootView.getChildAt(i).setVisibility(View.GONE);
            }
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    @Override
    public void showError(String msg) {
        showEmptyView(msg);
    }

    public abstract void init();

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);
}
