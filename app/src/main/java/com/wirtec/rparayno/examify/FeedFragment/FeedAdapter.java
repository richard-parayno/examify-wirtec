package com.wirtec.rparayno.examify.FeedFragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

/**
 * Created by Richard on 03/12/2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>  {
    private ArrayList<FeedCard> feedList;
    private ViewClickListener listener;

    public FeedAdapter(ArrayList<FeedCard> feedList, ViewClickListener listener) {
        this.feedList = feedList;
        this.listener = listener;
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {
        public TextView card_username;
        public TextView card_status;
        public ImageView card_thumbnail;
        public TextView card_content;


        public FeedViewHolder(View view) {
            super(view);
            card_username = (TextView) view.findViewById(R.id.card_name);
            card_thumbnail = (ImageView) view.findViewById(R.id.card_thumbnail);
            card_status = (TextView) view.findViewById(R.id.card_status);
            card_content = (TextView) view.findViewById(R.id.card_content);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onViewClick(v, getAdapterPosition());
                }
            });

        }
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_card, parent, false);

        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        FeedCard feedCard = feedList.get(position);
        holder.card_username.setText(feedCard.getUsername());
        holder.card_status.setText(feedCard.getUserstatus());
        holder.card_content.setText(feedCard.getContent());


    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }
}
