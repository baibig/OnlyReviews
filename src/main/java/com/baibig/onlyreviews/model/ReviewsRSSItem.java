package com.baibig.onlyreviews.model;

import java.io.Serializable;

/**
 * Created by HJ on 2015/5/17.
 */
public class ReviewsRSSItem implements Serializable {
    private String title;
    private String link;
    private String description;
    private String creator;
    private String pubDate;
    private String content_encoded;
    private String comment;
    private String img;
    private String movie_id;
    private String creator_url;

    public String getCreator_url() {
        return creator_url;
    }

    public void setCreator_url(String creator_url) {
        this.creator_url = creator_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContent_encoded() {
        return content_encoded;
    }

    public void setContent_encoded(String content_encoded) {
        this.content_encoded = content_encoded;
    }
}
