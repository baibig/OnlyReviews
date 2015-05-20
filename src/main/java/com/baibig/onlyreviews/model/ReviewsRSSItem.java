package com.baibig.onlyreviews.model;

/**
 * Created by HJ on 2015/5/17.
 */
public class ReviewsRSSItem {
    private String title;
    private String link;
    private String description;
    private String creator;
    private String pubDate;
    private String content_encoded;

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
