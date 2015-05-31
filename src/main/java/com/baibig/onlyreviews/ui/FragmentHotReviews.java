package com.baibig.onlyreviews.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baibig.onlyreviews.HomeActivity;
import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.data.Constant;
import com.baibig.onlyreviews.data.ReviewsRSSHandler;
import com.baibig.onlyreviews.model.ReviewsRSSFeed;
import com.baibig.onlyreviews.model.ReviewsRSSItem;
import com.baibig.onlyreviews.utils.MovieParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentHotReviews extends Fragment implements MovieParser.ListResultCallBack,
        AdapterView.OnItemClickListener{
    private View mView;
    private ListView mListView;
    private ReviewsListAdapter adapter;
    private List<ReviewsRSSItem> list=new ArrayList<>();
    private String url=Constant.HOTREVIEWS;
    private OnReviewListener onReviewListener;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle=getArguments();
        if (bundle!=null){
            url=bundle.getString("request_url");
        }
        mView=inflater.inflate(R.layout.fragment_hotreviews,null);
        mListView= (ListView) mView.findViewById(R.id.hotReviewsLV);
        adapter=new ReviewsListAdapter(getActivity(),R.layout.reviews_list_item,list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        MovieParser.getReviewsFromXml(url,this);
        return mView;
    }
    @Override
    public void onListResult(List<?> data) {
        list.clear();
        list.addAll((Collection<? extends ReviewsRSSItem>) data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onReviewListener= (OnReviewListener) activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("reviewclick","clicked");
        ReviewsRSSItem item= (ReviewsRSSItem) adapterView.getItemAtPosition(i);
        onReviewListener.onReview(item);

    }
    public interface OnReviewListener{
        void onReview(ReviewsRSSItem item);
    }
}
