package com.lyichao.play.ui.fragment;

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

import com.lyichao.play.R;
import com.lyichao.play.ui.adapter.RecomendAppAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bean.AppInfo;
import bean.PageBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import di.DaggerRecommendComponent;
import di.RecommendModule;
import http.ApiService;
import http.HttpManager;
import presenter.RecommendPresenter;
import presenter.contract.RecommendContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RecommendFragment extends Fragment implements RecommendContract.View{
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private RecomendAppAdapter mAdapter;

//    private RecommendContract.Presenter mPresenter;

    @Inject
    ProgressDialog mProgressDialog;

    @Inject
    RecommendContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        ButterKnife.bind(this, view);

        DaggerRecommendComponent.builder().recommendModule(new RecommendModule(this)).build().inject(this);

        initData();
        return view;
    }

    private void  initData(){

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
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dimissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
