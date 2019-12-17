package com.lyichao.play.ui.fragment;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lyichao.play.AppApplication;
import com.lyichao.play.R;
import com.lyichao.play.di.component.AppComponent;
import com.lyichao.play.presenter.RecommendPresenter;
import com.lyichao.play.ui.adapter.RecomendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import com.lyichao.play.bean.AppInfo;
import butterknife.BindView;
import butterknife.ButterKnife;
//import com.lyichao.play.di.component.DaggerRecommendComponent;
import com.lyichao.play.di.component.DaggerRecommendComponent;
import com.lyichao.play.di.module.RecommendModule;
import com.lyichao.play.presenter.contract.RecommendContract;

import static android.content.ContentValues.TAG;

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View{
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private RecomendAppAdapter mAdapter;


    @Inject
    ProgressDialog mProgressDialog;


    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);

    }

    @Override
    public void init() {

        mPresenter.requestDatas();

    }

    public void initRecylerView(List<AppInfo> datas){


        //为RecyclerView设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecomendAppAdapter(getActivity(),datas);
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    public void showResult(List<AppInfo> datas) {

        initRecylerView(datas);
        Log.d(TAG, "showResult:=======>");
    }

    @Override
    public void showNoDatas() {
        Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器异常："+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }
}
