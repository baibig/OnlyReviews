package com.baibig.onlyreviews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baibig.onlyreviews.R;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentComing extends Fragment {
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.home_viewpager,null);
        Log.i("tag", "fragmentcoming");
        return mView;
    }
}
