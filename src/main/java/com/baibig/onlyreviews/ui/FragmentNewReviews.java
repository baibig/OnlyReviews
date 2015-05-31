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
import com.baibig.onlyreviews.data.Constant;
import com.baibig.onlyreviews.model.ReviewsRSSItem;
import com.baibig.onlyreviews.utils.MovieParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentNewReviews extends Fragment implements MovieParser.ListResultCallBack{
    private View mView;
    private ListView mListView;
    private ReviewsListAdapter adapter;
    private List<ReviewsRSSItem> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_hotreviews,null);
        mListView= (ListView) mView.findViewById(R.id.hotReviewsLV);
        adapter=new ReviewsListAdapter(getActivity(),R.layout.reviews_list_item,list);
        mListView.setAdapter(adapter);
        MovieParser.getReviewsFromXml(Constant.NEWREVIEWS, this);
        return mView;
    }
    @Override
    public void onListResult(List<?> data) {
        list.clear();
        list.addAll((Collection<? extends ReviewsRSSItem>) data);
        adapter.notifyDataSetChanged();
    }
}
