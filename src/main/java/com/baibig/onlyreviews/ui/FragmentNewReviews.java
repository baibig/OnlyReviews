package com.baibig.onlyreviews.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baibig.onlyreviews.HomeActivity;
import com.baibig.onlyreviews.R;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentNewReviews extends Fragment {
    private View mView;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_newreviews,null);
        mListView= (ListView) mView.findViewById(R.id.newReviewsLV);
        Log.i("tag", "fragmentnewreviews"+mListView.toString());
        return mView;
    }
}
