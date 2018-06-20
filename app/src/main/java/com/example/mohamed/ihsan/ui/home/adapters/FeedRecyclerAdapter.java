package com.example.mohamed.ihsan.ui.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.ihsan.R;
import com.example.mohamed.ihsan.repositories.feed.Feed;

import java.util.ArrayList;

/**
 * Created by Mohamed on 01/06/2018.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Feed> feeds;

    public FeedRecyclerAdapter(Context context, ArrayList<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.recycler_feed, parent, false);

        return new FeedViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Feed feed = feeds.get(position);

        ((FeedViewHolder) holder).feedText.setText(feed.getMessageEn());
        ((FeedViewHolder) holder).feedDate.setText(feed.getDate());

    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView feedText;
        TextView feedDate;

        public FeedViewHolder(View itemView) {
            super(itemView);

            feedText = (TextView) itemView.findViewById(R.id.tv_feed_text);
            feedDate = (TextView) itemView.findViewById(R.id.tv_feed_date);
        }
    }
}
