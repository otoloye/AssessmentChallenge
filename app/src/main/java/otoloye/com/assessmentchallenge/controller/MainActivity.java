package otoloye.com.assessmentchallenge.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import otoloye.com.assessmentchallenge.MyApp;
import otoloye.com.assessmentchallenge.R;
import otoloye.com.assessmentchallenge.adapter.ArticleAdapter;
import otoloye.com.assessmentchallenge.api.ApiService;
import otoloye.com.assessmentchallenge.model.Article;
import otoloye.com.assessmentchallenge.model.ArticleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    Picasso picasso;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<Article> articles;
    ArticleAdapter articleAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        initViews();

        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void initViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching users");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        swipeRefresh.setRefreshing(false);
        loadData();
    }

    private void loadData() {
        ApiService service = retrofit.create(ApiService.class);
        Call<ArticleResponse> call = service.getHeadlines("540bc24183d143e6959565ec8590f5e5", "us");
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                articles = response.body().getItems();
                articleAdapter = new ArticleAdapter(getApplicationContext(), articles, picasso);
                recyclerView.setAdapter(articleAdapter);
                swipeRefresh.setRefreshing(false);
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
    }
}
