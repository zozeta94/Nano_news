package com.udacity.nano_news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by zozeta on 28/09/2017.
 */
public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;


    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        List<News> NewsList = QuiryUtils.fetchEarthquakeData(mUrl);
        return NewsList;
    }

}
