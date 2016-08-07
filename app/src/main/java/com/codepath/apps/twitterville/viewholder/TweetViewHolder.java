package com.codepath.apps.twitterville.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codepath.apps.twitterville.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alishaalam on 8/5/16.
 */
public class TweetViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_user)
    public TextView vTweetUsername;

    @Nullable @BindView(R.id.tv_name)
    public TextView vTweetName;

    @Nullable @BindView(R.id.tv_body)
    public TextView vTweetBody;

    @Nullable @BindView(R.id.tv_age)
    public TextView vTweetAge;


    public TweetViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
