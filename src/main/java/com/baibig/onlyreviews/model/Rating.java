package com.baibig.onlyreviews.model;

/**
 * Created by HJ on 2015/5/12.
 */
public class Rating {
    int min;
    int max;
    float average;
    int star;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float value) {
        this.average = value;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
