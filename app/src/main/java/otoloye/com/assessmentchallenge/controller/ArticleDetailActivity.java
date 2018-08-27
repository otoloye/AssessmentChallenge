package otoloye.com.assessmentchallenge.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Inject
    Picasso picasso;

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
        String article_link = getIntent().getExtras().getString("article_url");

        articleAuthor.setText("Author: " + article_author);
        articleTitle.setText("Title: " + article_title);
        articleDescription.setText("Description: " + article_description);
        articlePublishDate.setText("Publish Date: " + article_publish_date);

        articleLink.setText("Link: " + article_link);
        Linkify.addLinks(articleLink, Linkify.WEB_URLS);

        picasso.load(article_image).into(articleImage);
    }
}
