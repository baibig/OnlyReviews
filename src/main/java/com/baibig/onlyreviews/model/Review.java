package com.baibig.onlyreviews.model;

/**
 * Created by HJ on 2015/5/12.
 */
public class Review {
    String id;
    String title;

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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getUseful_count() {
        return useful_count;
    }

    public void setUseful_count(int useful_count) {
        this.useful_count = useful_count;
    }

    public int getUseless_count() {
        return useless_count;
    }

    public void setUseless_count(int useless_count) {
        this.useless_count = useless_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    String alt;
    String created_at;
    String updated_at;
    String subject_id;
    Author author;
    String summary;
    Rating rating;
    int useful_count;
    int useless_count;
    int comments_count;

}
