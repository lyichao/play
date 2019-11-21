package com.lyichao.play.ui.fragment;

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

import com.lyichao.play.R;
import com.lyichao.play.ui.adapter.RecomendAppAdapter;

import java.util.ArrayList;
import java.util.List;

import bean.AppInfo;
import bean.PageBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import http.ApiService;
import http.HttpManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RecommendFragment extends Fragment {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

//    private RecomendAppAdapter mAdapter;
    private RecomendAppAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void  initData(){
        HttpManager manager = new HttpManager();

        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);


        apiService.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                Log.d(TAG, "success!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                PageBean<AppInfo> pageBean = response.body();
                List<AppInfo> datas = (List<AppInfo>) pageBean.getDatas();
                initRecylerView(datas);
            }


            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                Log.d(TAG, "fail!!!!!!!!!!!!");

            }
        });

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

}
