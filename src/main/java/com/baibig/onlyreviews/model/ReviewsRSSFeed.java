package com.baibig.onlyreviews.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by HJ on 2015/5/17.
 */
public class ReviewsRSSFeed {
    private String title;
    private String pubDate;
    private int count;
    private List<ReviewsRSSItem> reviewsList;

    public ReviewsRSSFeed(){
        reviewsList=new ArrayList<>(0);
    }

    public int addItem(ReviewsRSSItem item){
        reviewsList.add(item);
        count++;
        return count;
    }

    public ReviewsRSSItem getItem(int position){
        return reviewsList.get(position);
    }

    public List getAllItems(){
        return reviewsList;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
