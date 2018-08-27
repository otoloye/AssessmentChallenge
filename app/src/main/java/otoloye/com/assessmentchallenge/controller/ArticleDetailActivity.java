package otoloye.com.assessmentchallenge.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import otoloye.com.assessmentchallenge.MyApp;
import otoloye.com.assessmentchallenge.R;

public class ArticleDetailActivity extends AppCompatActivity {

    @BindView(R.id.article_image)
    ImageView articleImage;

    @BindView(R.id.article_author)
    TextView articleAuthor;

    @BindView(R.id.article_title)
    TextView articleTitle;

    @BindView(R.id.article_description)
    TextView articleDescription;

    @BindView(R.id.article_publish_date)
    TextView articlePublishDate;

    @BindView(R.id.article_link)
    TextView articleLink;

//    @BindView(R.id.webview)
//    WebView webView;

    @Inject
    Picasso picasso;
    String article_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Details Activity");
        ((MyApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        String article_image = getIntent().getExtras().getString("article_image");
        String article_author = getIntent().getExtras().getString("article_author");
        String article_title = getIntent().getExtras().getString("article_title");
        String article_description = getIntent().getExtras().getString("article_description");
        String article_publish_date = getIntent().getExtras().getString("article_publish_date");
        article_link = getIntent().getExtras().getString("article_url");

        articleAuthor.setText(article_author);
        articleTitle.setText(article_title);
        articleDescription.setText(article_description);
        articlePublishDate.setText(article_publish_date);
        articleLink.setText(article_link);

        articleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webView = new WebView(ArticleDetailActivity.this);
                        webView.loadUrl(article_link);
            }
        });


        picasso.load(article_image).into(articleImage);
    }
}