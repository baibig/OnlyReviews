package com.baibig.onlyreviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baibig.onlyreviews.app.AppController;
import com.baibig.onlyreviews.ui.FragmentComing;
import com.baibig.onlyreviews.ui.FragmentPlaying;
import com.baibig.onlyreviews.ui.FragmentTop;

/**
 * Created by HJ on 2015/5/11.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] title={"Top 250","热映电影","即将上映"};
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
                if (mFragmentTop==null) {
                    mFragmentTop = (FragmentTop) AppController.getInstance().getFragment("top");
                }
                return mFragmentTop;
            case 1:
                if (mFragmentPlaying==null) {
                    mFragmentPlaying = (FragmentPlaying) AppController.getInstance().getFragment("playing");
                }
                return mFragmentPlaying;
            case 2:
                if (mFragmentComing==null) {
                    mFragmentComing = (FragmentComing) AppController.getInstance().getFragment("coming");
                }
                return mFragmentComing;

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
