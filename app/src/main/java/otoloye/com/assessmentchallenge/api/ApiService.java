package otoloye.com.assessmentchallenge.api;

import otoloye.com.assessmentchallenge.model.Article;
import otoloye.com.assessmentchallenge.model.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("top-headlines")
    Call<ArticleResponse> getHeadlines(@Query("apiKey") String apikey, @Query("country") String country);
}
