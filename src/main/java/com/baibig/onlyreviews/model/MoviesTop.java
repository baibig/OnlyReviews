package com.baibig.onlyreviews.model;

/**
 * Created by HJ on 2015/5/19.
 */
public class MoviesTop {
    int count;
    int start;
    int total;
    Movie[] subjects;
    String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Movie[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Movie[] subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
