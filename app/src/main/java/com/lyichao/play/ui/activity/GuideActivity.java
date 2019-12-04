package com.lyichao.play.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lyichao.play.R;
import com.lyichao.play.ui.adapter.GuideFragmentAdapter;
import com.lyichao.play.ui.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.indicator)
    LinearLayout mIndicator;
    @BindView(R.id.activity_guide)
    RelativeLayout mActivityGuide;
    @BindView(R.id.img_icon1)
    ImageView mImgIcon1;
    @BindView(R.id.img_icon2)
    ImageView mImgIcon2;
    @BindView(R.id.img_icon3)
    ImageView mImgIcon3;

    private GuideFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {


        List<Fragment> fragment = new ArrayList<>();

        fragment.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide1));
        fragment.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide2));
        fragment.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide3));


        //创建一个GuideFragmentAdapter实例，并赋值给mAdapter
        mAdapter = new GuideFragmentAdapter(getSupportFragmentManager());

        //添加数据
        mAdapter.setFragments(fragment);

        //设置viewpager的初始页
        mViewpager.setCurrentItem(0);

        //预加载viewpager的数量
        mViewpager.setOffscreenPageLimit(mAdapter.getCount());

        //将mAdapter和viewpager进行关联
        mViewpager.setAdapter(mAdapter);

        mViewpager.addOnPageChangeListener(this);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {
            mImgIcon1.setImageResource(R.color.color_white);
            mImgIcon2.setImageResource(R.color.colorAccent);
            mImgIcon3.setImageResource(R.color.colorAccent);
        }else if (position == 1 ){
            mImgIcon1.setImageResource(R.color.colorAccent);
            mImgIcon2.setImageResource(R.color.color_white);
            mImgIcon3.setImageResource(R.color.colorAccent);
        }else if (position == 2 ){
            mImgIcon1.setImageResource(R.color.colorAccent);
            mImgIcon2.setImageResource(R.color.colorAccent);
            mImgIcon3.setImageResource(R.color.color_white);
        }

        mBtnEnter.setVisibility((position == mAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
