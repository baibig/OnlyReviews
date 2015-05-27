package com.baibig.onlyreviews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.app.AppController;
import com.baibig.onlyreviews.model.Movie;
import com.baibig.onlyreviews.utils.MovieParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentPlaying extends Fragment implements MovieParser.ListResultCallBack{
    private View mView;
    private ListView mListView;
    private MovieListAdapter mAdapter;
    private List<Movie> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_playing,null);
        mListView= (ListView) mView.findViewById(R.id.playing_list_lv);
        mAdapter=new MovieListAdapter(getActivity(),R.layout.top_list_item,list);
        mListView.setAdapter(mAdapter);
        MovieParser.getMoviesFromHtml("nowplaying", this);
        Log.i("tag","fragmentplaying");
        return mView;

    }

    @Override
    public void onListResult(List<Movie> data) {
        list.clear();
        list.addAll(data);
        mAdapter.notifyDataSetChanged();
    }
}
