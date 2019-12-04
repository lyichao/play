package com.lyichao.play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideFragmentAdapter extends FragmentPagerAdapter{

    List<Fragment> mFragments;

    //构造方法
    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    //初始化fragments
    public void setFragments(List<Fragment> fragments) {

        if (fragments == null) {
            mFragments = new ArrayList<>();
        }else {
            mFragments = fragments;
        }

    }

    //获取Fragment
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    //获取Fragment个数
    @Override
    public int getCount() {
        return mFragments.size();
    }
}
