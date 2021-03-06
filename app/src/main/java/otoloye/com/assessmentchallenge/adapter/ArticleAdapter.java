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

import butterknife.BindView;
import butterknife.ButterKnife;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false);
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

        @BindView(R.id.article_image)
        ImageView articleImage;
        @BindView(R.id.article_author)
        TextView articleAuthor;
        @BindView(R.id.article_title)
        TextView articleTitle;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Article clickDataItem = articles.get(pos);
                        Intent intent = new Intent(context, ArticleDetailActivity.class);
                        intent.putExtra("article", clickDataItem);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
