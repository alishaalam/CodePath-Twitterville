package com.codepath.apps.twitterville.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.codepath.apps.twitterville.R;
import com.codepath.apps.twitterville.models.Tweet;
import com.codepath.apps.twitterville.viewholder.TweetViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by alishaalam on 8/5/16.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {

    Context mContext;
    ArrayList<Tweet> mTweetsList = new ArrayList<Tweet>();
    private static final String TAG = TweetAdapter.class.getSimpleName();
    ImageLoader mImageLoader;



    public TweetAdapter(Context mContext, ArrayList<Tweet> mTweetsList) {
        this.mContext = mContext;
        this.mTweetsList = mTweetsList;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.tweet_item_content, parent, false);
        TweetViewHolder tweetViewHolder = new TweetViewHolder(view);
        return  tweetViewHolder;
    }

    @Override
    public void onBindViewHolder(TweetViewHolder tweetViewHolder, int position) {
        Tweet tweet = mTweetsList.get(position);
        if(tweet != null) {
            tweetViewHolder.vTweetUsername.setText(tweet.getUser().getScreenName());
            tweetViewHolder.vTweetName.setText(tweet.getUser().getName());
            tweetViewHolder.vTweetBody.setText(tweet.getBody());

            String tweetAge = getTweetAge(tweet.getTweetTime());
            tweetViewHolder.vTweetAge.setText(tweetAge);
        }
    }

    @Override
    public int getItemCount() {
        return mTweetsList.size();
    }

    public static String getTweetAge(String tweetTime) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"; // "created_at": "Tue Aug 28 21:16:23 +0000 2012",
        SimpleDateFormat sdf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sdf.setLenient(true);
        long formattedTimeInSecs = 0;

        try {
            formattedTimeInSecs = sdf.parse(tweetTime).getTime();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String ago = DateUtils.getRelativeTimeSpanString(formattedTimeInSecs, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        return ago;
    }
}
