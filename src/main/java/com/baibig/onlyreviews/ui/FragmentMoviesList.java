package com.baibig.onlyreviews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.baibig.onlyreviews.HomePagerAdapter;
import com.baibig.onlyreviews.R;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentMoviesList extends Fragment{
    private View mView;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.home_tab,null);
        mViewPager= (ViewPager) mView.findViewById(R.id.home_viewpagers);
        mHomePagerAdapter=new HomePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mHomePagerAdapter);
        mPagerSlidingTabStrip= (PagerSlidingTabStrip) mView.findViewById(R.id.home_tabs);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        return mView;
    }
}
