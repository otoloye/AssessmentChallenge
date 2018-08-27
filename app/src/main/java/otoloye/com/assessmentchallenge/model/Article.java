package otoloye.com.assessmentchallenge.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("publishedAt")
    private String publishedAt;


    public Article(String author, String title, String urlToImage, String description, String url, String publishedAt) {
        this.author = author;
        this.title = title;
        this.urlToImage = urlToImage;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
