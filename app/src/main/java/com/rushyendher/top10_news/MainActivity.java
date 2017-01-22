package com.rushyendher.top10_news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<SourcesInfo>> {

    private static final String SOURCE_URL = "https://newsapi.org/v1/sources";
    private static final int SOURCE_LOADER_ID = 1;

    private ArrayAdapter<String> mAdapter;
    private Map<String, ArrayList<SourcesInfo> > sourceInfo = null;
    private ListView mCategoriesListView;
    private ArrayList<CategoriesToSource> categoriesToSources = null;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateUI();



        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected())
        {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(SOURCE_LOADER_ID,null,this);
        }
        else
        {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("I am not connected :(");
        }


        mCategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CategoriesToSource categoriesToSource = categoriesToSources.get(position);

                Intent intent = new Intent(MainActivity.this,SourcesActivity.class);
                intent.putExtra("SOURCE",categoriesToSource.getmCategory());

                intent.putParcelableArrayListExtra("SOURCES",categoriesToSource.getSourcesInfos());
                startActivity(intent);
            }
        });

    }

    private void UpdateUI()
    {
        mCategoriesListView = (ListView)findViewById(R.id.category_list);
        mAdapter = new ArrayAdapter<String>(this,R.layout.category_list_view,new ArrayList<String>());
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mCategoriesListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<SourcesInfo>> onCreateLoader(int id, Bundle args) {
        return new SourceLoader(this,SOURCE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<SourcesInfo>> loader, List<SourcesInfo> data) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();
        if(data != null && !data.isEmpty())
        {
            createCategories(data);
            if(categoriesToSources != null)
            {
                for(int i = 0;i<categoriesToSources.size();i++)
                    mAdapter.add(categoriesToSources.get(i).getmCategory());
            }
        }
        sourceInfo.clear();
    }

    private void createCategories(List<SourcesInfo> data)
    {
        categoriesToSources = new ArrayList<>();
        sourceInfo = new HashMap<>();
        for(int i = 0;i<data.size();i++)
        {
            String category = data.get(i).getmCategory();
            SourcesInfo source = data.get(i);
            if(sourceInfo.containsKey(category))
                sourceInfo.get(category).add(source);
            else
            {
                ArrayList<SourcesInfo> firstSource = new ArrayList<>();
                firstSource.add(source);
                sourceInfo.put(category,firstSource);
            }
        }
        for (String key : sourceInfo.keySet())
        {
            categoriesToSources.add(new CategoriesToSource(key,sourceInfo.get(key)));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<SourcesInfo>> loader) {
        mAdapter.clear();
    }
}
