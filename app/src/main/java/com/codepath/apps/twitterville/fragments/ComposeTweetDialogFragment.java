package com.codepath.apps.twitterville.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.twitterville.R;
import com.codepath.apps.twitterville.activities.TwitterApplication;
import com.codepath.apps.twitterville.activities.TwitterClient;

/**
 * Created by alishaalam on 8/6/16.
 */
public class ComposeTweetDialogFragment extends DialogFragment implements View.OnClickListener{

    EditText mEtTweetBody;
    private TwitterClient client;
    Button btnPostTweet;


    public ComposeTweetDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ComposeTweetDialogFragment newInstance(String title) {
        ComposeTweetDialogFragment frag = new ComposeTweetDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient(); //get a singleton client
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose_tweet, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEtTweetBody = (EditText) view.findViewById(R.id.et_tweet_body);
        // Show soft keyboard automatically and request focus to field
        mEtTweetBody.requestFocus();
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Tweet");
        getDialog().setTitle(title);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btnPostTweet = (Button) view.findViewById(R.id.btn_send_tweet);
        btnPostTweet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String tweetBody = mEtTweetBody.getText().toString();
        OnTweetPostedListener listener = (OnTweetPostedListener) getActivity();
        listener.onPostTweet(tweetBody);
        dismiss();
    }

    // 1. Defines the listener interface with a method passing back filters as result to activity.
    public interface OnTweetPostedListener {
        void onPostTweet(String tweetBody);
    }

    /*public void onTweetSubmit(View view) {

        mEtTweetBody = (EditText) view.findViewById(R.id.et_tweet_body);
        final String tweetBody = mEtTweetBody.getText().toString();
        client.postTweet(tweetBody, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Toast.makeText(getActivity(), "Success in posting a tweet", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }*/

}
