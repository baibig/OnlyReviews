package com.baibig.onlyreviews.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.app.AppController;
import com.baibig.onlyreviews.data.Constant;
import com.baibig.onlyreviews.model.Movie;
import com.baibig.onlyreviews.model.MoviesTop;
import com.baibig.onlyreviews.utils.MovieParser;
import com.baibig.onlyreviews.utils.MovieParser.ListResultCallBack;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentTop extends Fragment implements AdapterView.OnItemClickListener,
        ListResultCallBack,
        Thread.UncaughtExceptionHandler{
    private View mView;
    private RefreshLayout mRefreshLayout;
    private ListView mTopListView;
    private MovieListAdapter mMovieListAdapter;
    private ArrayList<Movie> list;
    private int start=0;
    private int count=10;
    private OnSwitchListener onSwitchListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onSwitchListener= (OnSwitchListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        initView(inflater);
        initRefreshLayout();
        start=0;

        MovieParser.getMoviesFromJson("top", start, count, this);
        Log.i("tag", "fragmenttop");
        return mView;
    }

    private void initRefreshLayout() {
        mRefreshLayout= (RefreshLayout) mView.findViewById(R.id.fragment_top_refresh);
        mRefreshLayout.setFooterView(getActivity(), mTopListView, R.layout.footerview);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                MovieParser.getMoviesFromJson("top", start, count, new ListResultCallBack() {
                    @Override
                    public void onListResult(List<?> data) {
                        list.addAll((Collection<? extends Movie>) data);
                        mMovieListAdapter.notifyDataSetChanged();
                    }
                });
                Log.i("tag", "onLoading");
            }
        });
    }

    private void initView(LayoutInflater inflater) {
        mView=inflater.inflate(R.layout.fragment_top,null);
        mTopListView= (ListView) mView.findViewById(R.id.top_list_lv);
        list=new ArrayList<>();
        list.clear();
        mMovieListAdapter=new MovieListAdapter(getActivity(),R.layout.top_list_item,list);
        mTopListView.setAdapter(mMovieListAdapter);
        mTopListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Movie movie= (Movie) adapterView.getItemAtPosition(i);
        Bundle bundle=new Bundle();
        bundle.putString("subject_id",movie.getId());
        onSwitchListener.onSwitch(bundle);

    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("tag", "uncaughtExveption" + throwable);


    }

    @Override
    public void onListResult(List<?> data) {

        list.addAll((Collection<? extends Movie>) data);
        mMovieListAdapter.notifyDataSetChanged();
        start+=count;

    }
    public interface OnSwitchListener{
        void onSwitch(Bundle b);
    }
}
