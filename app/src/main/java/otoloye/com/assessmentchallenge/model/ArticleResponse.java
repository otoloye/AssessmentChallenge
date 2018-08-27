package otoloye.com.assessmentchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleResponse {

    private List<Article> articles;

    public List<Article> getItems() {
        return articles;
    }

    public void setItems(List<Article> items) {
        this.articles = items;
    }
}
