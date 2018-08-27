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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import otoloye.com.assessmentchallenge.MyApp;
import otoloye.com.assessmentchallenge.R;
import otoloye.com.assessmentchallenge.model.Article;

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
        getSupportActionBar().setTitle("Article Details Activity");
        ((MyApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        Article article = getIntent().getParcelableExtra("article");

        articleAuthor.setText("Author: " + article.getAuthor());
        articleTitle.setText("Title: " + article.getTitle());
        articleDescription.setText("Description: " + article.getDescription());


        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = null;
        try {
            date = simpleDateFormat.parse(article.getPublishedAt());
            simpleDateFormat.applyPattern("EEE, MMM d, ''yy");
            articlePublishDate.setText(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        articleLink.setText("Link: " + article.getUrl());
        Linkify.addLinks(articleLink, Linkify.WEB_URLS);

        picasso.load(article.getUrlToImage()).into(articleImage);
    }
}
