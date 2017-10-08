package com.udacity.nano_news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>  {
    public static final String QUERY = "https://content.guardianapis.com/search?q=uk&api-key=f1237db2-8dfb-43cd-b7b3-030a2afde288";
    private static final int LOADER_ID = 1;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    public  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        final LoaderManager loaderManager = getLoaderManager();

        if (networkInfo != null && networkInfo.isConnected()) {

            loaderManager.initLoader(LOADER_ID, null, MainActivity.this);

            listView = (ListView) findViewById(R.id.list);

            mAdapter = new NewsAdapter(MainActivity.this, new ArrayList<News>());

            listView.setAdapter(mAdapter);

            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

            listView.setEmptyView(mEmptyStateTextView);
            listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    News N =mAdapter.getItem(position);
                    String url =N.getUrl();
                    Intent I = new Intent(Intent.ACTION_VIEW);
                    I.setData(Uri.parse(url));
                    startActivity(I);
                }
            });

        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("no internet connection");
        }



    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);
        // Create a new loader for the given URL
        return new NewsLoader(this, QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> NewsList) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText("no News were found");
        // Clear the adapter of previous data
        mAdapter.clear();
        if (NewsList != null && !NewsList.isEmpty()) {
            mAdapter.addAll(NewsList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}



