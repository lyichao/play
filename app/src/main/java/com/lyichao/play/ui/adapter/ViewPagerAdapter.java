package com.lyichao.play.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lyichao.play.ui.bean.FragmentInfo;
import com.lyichao.play.ui.fragment.CategoryFragment;
import com.lyichao.play.ui.fragment.GamesFragment;
import com.lyichao.play.ui.fragment.RankingFragment;
import com.lyichao.play.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<FragmentInfo> mFragment = new ArrayList<>(4);


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        initFragments();
    }

    private void initFragments(){

        mFragment.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragment.add(new FragmentInfo("排行版",RankingFragment.class));
        mFragment.add(new FragmentInfo("游戏",GamesFragment.class));
        mFragment.add(new FragmentInfo("分类",CategoryFragment.class));


    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragment.get(position).getFragment().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragment.get(position).getTitle();
    }
}
