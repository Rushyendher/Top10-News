package com.rushyendher.top10_news;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ArticleInfo>> {

    private final static String APP_ID = "143ffd5cb6fb4e1aaaee3d05c7b066fa";
    private String article_URL;
    private static final int SOURCE_LOADER_ID = 1;
    private ListView articlesListView;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        String sourceId = getIntent().getStringExtra("SOURCE_ID");
        article_URL = "https://newsapi.org/v1/articles?source=" + sourceId + "&apiKey=" + APP_ID;

        updateUI();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(SOURCE_LOADER_ID,null,this);

    }

    private void updateUI()
    {
        articlesListView = (ListView)findViewById(R.id.activity_articles_list);
        adapter = new ArticleAdapter(this,new ArrayList<ArticleInfo>());
        articlesListView.setAdapter(adapter);
    }

    @Override
    public Loader<List<ArticleInfo>> onCreateLoader(int id, Bundle args) {
        return new ArticleLoader(this,article_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<ArticleInfo>> loader, List<ArticleInfo> data) {
        adapter.clear();
        if(data != null && !data.isEmpty())
        {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ArticleInfo>> loader) {
        adapter.clear();
    }
}
