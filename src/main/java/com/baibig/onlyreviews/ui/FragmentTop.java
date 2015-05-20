package com.baibig.onlyreviews.ui;

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
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentTop extends Fragment implements AdapterView.OnItemClickListener,
        Thread.UncaughtExceptionHandler{
    private View mView;
    private RefreshLayout mRefreshLayout;
    private ListView mTopListView;
    private MovieListAdapter mMovieListAdapter;
    private ArrayList<Movie> list;
    private int start=0;
    private int count=10;
    RequestQueue queue= AppController.getInstance().getRequestQueue();
    JsonObjectRequest request;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        initView(inflater);
        request=new JsonObjectRequest(Request.Method.POST, Constant.TOP250,
                getJsonparams(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJson(response);
                        Log.i("response",list.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("start",start+"");
                params.put("count",count+"");
                return params;
            }
        };
        queue.add(request);
        initRefreshLayout();

        Log.i("tag", "fragmenttop");
        return mView;
    }

    private void initRefreshLayout() {
        mRefreshLayout= (RefreshLayout) mView.findViewById(R.id.fragment_top_refresh);
        mRefreshLayout.setFooterView(getActivity(),mTopListView,R.layout.footerview);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                queue.add(request);
                Log.i("tag", "onLoading");
            }
        });
    }

    private void initView(LayoutInflater inflater) {
        mView=inflater.inflate(R.layout.fragment_top,null);
        mTopListView= (ListView) mView.findViewById(R.id.top_list_lv);
        list=new ArrayList<>();
        mMovieListAdapter=new MovieListAdapter(getActivity(),R.layout.top_list_item,list);
        mTopListView.setAdapter(mMovieListAdapter);
        mTopListView.setOnItemClickListener(this);
    }

    public JSONObject getJsonparams(){
        Map<String,String> map=new HashMap<>();
        map.put("start",start+"");
        map.put("count", count + "");
        JSONObject params=new JSONObject(map);
        return params;
    }

    public void parseJson(JSONObject response){
            Gson gson = new Gson();
            MoviesTop top = gson.fromJson(response.toString(), MoviesTop.class);
            start = top.getStart();
            count = top.getCount();
            start+=count;
            Movie[] movies = top.getSubjects();
            for (Movie m : movies) {
                list.add(m);
                Log.i("tag", m.getTitle());
            }
            mMovieListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.i("tag","uncaughtExveption"+throwable);

    }
}
