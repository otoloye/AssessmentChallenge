package otoloye.com.assessmentchallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import otoloye.com.assessmentchallenge.R;
import otoloye.com.assessmentchallenge.controller.ArticleDetailActivity;
import otoloye.com.assessmentchallenge.model.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private List<Article> articles;
    private Picasso picasso;

    public ArticleAdapter(Context context, List<Article> items, Picasso picasso) {
        this.context = context;
        this.articles = items;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        holder.articleAuthor.setText(articles.get(position).getAuthor());
        holder.articleTitle.setText(articles.get(position).getTitle());

        picasso.load(articles.get(position).getUrlToImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView articleAuthor;
        TextView articleTitle;

        ViewHolder(final View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.article_image);
            articleAuthor = itemView.findViewById(R.id.article_author);
            articleTitle = itemView.findViewById(R.id.article_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Article clickDataItem = articles.get(pos);
                        Intent intent = new Intent(context, ArticleDetailActivity.class);
                        intent.putExtra("article_image", clickDataItem.getUrlToImage());
                        intent.putExtra("article_title", clickDataItem.getTitle());
                        intent.putExtra("article_author", clickDataItem.getAuthor());
                        intent.putExtra("article_description", clickDataItem.getDescription());
                        intent.putExtra("article_url", clickDataItem.getUrl());
                        intent.putExtra("article_publish_date", clickDataItem.getPublishedAt());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
