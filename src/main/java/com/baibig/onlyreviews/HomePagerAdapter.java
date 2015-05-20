package com.baibig.onlyreviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baibig.onlyreviews.ui.FragmentComing;
import com.baibig.onlyreviews.ui.FragmentPlaying;
import com.baibig.onlyreviews.ui.FragmentTop;

/**
 * Created by HJ on 2015/5/11.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] title={"Top 250","即将上映","热映电影"};
    FragmentPlaying mFragmentPlaying;
    FragmentComing mFragmentComing;
    FragmentTop mFragmentTop;
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                mFragmentTop=new FragmentTop();
                return mFragmentTop;
            case 1:
                mFragmentComing=new FragmentComing();
                return mFragmentComing;
            case 2:
                mFragmentPlaying=new FragmentPlaying();
                return mFragmentPlaying;
        }
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
