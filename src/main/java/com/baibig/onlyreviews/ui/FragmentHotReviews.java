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

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by HJ on 2015/5/11.
 */
public class FragmentHotReviews extends Fragment {
    private View mView;
    private ListView mListView;
    private ReviewsListAdapter adapter;
    private static ReviewsRSSFeed feed;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_hotreviews,null);

        mListView= (ListView) mView.findViewById(R.id.hotReviewsLV);


        /**
         * 利用Volley发送http请求获取评论返回xml数据
         */
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        StringRequest request=new StringRequest(
                Constant.HOTREVIEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        feed=new ReviewsRSSFeed();
                        feed=reviewsXmlReslove(s);
                        List<ReviewsRSSItem> list=new ArrayList<>();
                        list.addAll(feed.getAllItems());
                        adapter=new ReviewsListAdapter(getActivity(),R.layout.reviews_list_item,list);
                        mListView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("tag",volleyError.getMessage());
                    }
                }
        );
        queue.add(request);
        Log.i("tag","fragmenthotreviews");
        return mView;
    }

    private ReviewsRSSFeed reviewsXmlReslove(String s) {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            SAXParser parser=factory.newSAXParser();
            XMLReader xmlReader=parser.getXMLReader();
            ReviewsRSSHandler handler=new ReviewsRSSHandler();
            xmlReader.setContentHandler(handler);
            InputSource is=new InputSource(new StringReader(s));
            xmlReader.parse(is);
            return handler.getFeed();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
