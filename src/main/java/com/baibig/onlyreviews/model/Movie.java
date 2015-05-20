package com.baibig.onlyreviews.model;

import android.media.Image;

/**
 * Created by HJ on 2015/5/12.
 */
public class Movie {
    String id;
    String title;
    String original_title;
    String alt;
    Avatars images;
    Rating rating;
    String year;
    String subtype;
    Actor[] casts;
    Actor[] directors;
    String[] genres;
    int collect_count;

    public Actor[] getCasts() {
        return casts;
    }

    public void setCasts(Actor[] casts) {
        this.casts = casts;
    }

    public Actor[] getDirectors() {
        return directors;
    }

    public void setDirectors(Actor[] directors) {
        this.directors = directors;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getImages() {
        return images;
    }

    public void setImages(Avatars images) {
        this.images = images;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
