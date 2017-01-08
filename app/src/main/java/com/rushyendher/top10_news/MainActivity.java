package com.rushyendher.top10_news;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<SourcesInfo>> {

    private static final String SOURCE_URL = "https://newsapi.org/v1/sources";
    private static final int SOURCE_LOADER_ID = 1;

    private ArrayAdapter<String> mAdapter;
    private List<String> mCategories = null;
    private Map<String, List<SourcesInfo> > sourceInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateUI();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(SOURCE_LOADER_ID,null,this);

    }

    private void UpdateUI()
    {
        ListView categoriesListView = (ListView)findViewById(R.id.category_list);
        mAdapter = new ArrayAdapter<String>(this,R.layout.category_list_view,new ArrayList<String>());
        categoriesListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<SourcesInfo>> onCreateLoader(int id, Bundle args) {
        return new SourceLoader(this,SOURCE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<SourcesInfo>> loader, List<SourcesInfo> data) {
        if(data != null && !data.isEmpty())
        {
            createCategories(data);
            if(mCategories != null)
                mAdapter.addAll(mCategories);
        }
    }

    private void createCategories(List<SourcesInfo> data)
    {
        mCategories = new ArrayList<String>();
        sourceInfo = new HashMap<>();
        for(int i = 0;i<data.size();i++)
        {
            String category = data.get(i).getmCategory();
            SourcesInfo source = data.get(i);
            if(sourceInfo.containsKey(category))
                sourceInfo.get(category).add(source);
            else
            {
                List<SourcesInfo> firstSource = new ArrayList<>();
                firstSource.add(source);
                sourceInfo.put(category,firstSource);
            }
        }
        for (String key : sourceInfo.keySet())
            mCategories.add(key);
    }

    @Override
    public void onLoaderReset(Loader<List<SourcesInfo>> loader) {
        mAdapter.clear();
    }
}
