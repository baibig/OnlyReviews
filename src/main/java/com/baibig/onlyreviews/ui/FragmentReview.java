package com.baibig.onlyreviews.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baibig.onlyreviews.R;
import com.baibig.onlyreviews.model.Review;
import com.baibig.onlyreviews.model.ReviewsRSSItem;
import com.baibig.onlyreviews.utils.MovieParser;


import java.util.List;

/**
 * Created by HJ on 2015/5/30.
 */
public class FragmentReview extends Fragment implements MovieParser.ListResultCallBack{
    private Review review=new Review();
    private ReviewsRSSItem reviewsRSSItem;
    private View mView;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mTime;
    private TextView mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            reviewsRSSItem= (ReviewsRSSItem) bundle.getSerializable("review_snapshot");
        }
        review.setTitle(reviewsRSSItem.getTitle());
        review.setTime(reviewsRSSItem.getPubDate());
        review.setAuthor(reviewsRSSItem.getCreator());
        MovieParser.getReviewFromHtml(reviewsRSSItem.getLink(), this);
        Log.i("sequence",1+"");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_review,null);
        mTime= (TextView) mView.findViewById(R.id.fragment_review_time);
        mTitle= (TextView) mView.findViewById(R.id.fragment_review_title);
        mAuthor= (TextView) mView.findViewById(R.id.fragment_review_author);
        mContent= (TextView) mView.findViewById(R.id.fragment_review_content);
        mTitle.setText(review.getTitle());
        mTime.setText(review.getTime());
        mAuthor.setText(review.getAuthor());
        mContent.setText(review.getContent());
        Log.i("sequence", 2 + "");
        return mView;
    }

    @Override

    public void onListResult(List<?> list) {
        Review r= (Review) list.get(0);
        review.setContent(r.getContent());
        mContent.setText(review.getContent());
        Log.i("sequence", 3 + "");
    }
}
