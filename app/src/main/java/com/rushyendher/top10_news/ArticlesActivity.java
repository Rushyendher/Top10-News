package com.rushyendher.top10_news;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ArticleInfo>> {

    private final static String APP_ID = "143ffd5cb6fb4e1aaaee3d05c7b066fa";
    private String article_URL;
    private static final int SOURCE_LOADER_ID = 1;
    private ListView articlesListView;
    private ArticleAdapter adapter;
    private List<ArticleInfo> articleInfoList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        String sourceId = getIntent().getStringExtra("SOURCE_ID");
        article_URL = "https://newsapi.org/v1/articles?source=" + sourceId + "&apiKey=" + APP_ID;

        updateUI();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(SOURCE_LOADER_ID,null,this);

        articlesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArticleInfo articleInfo = articleInfoList.get(position);
                Intent intent = new Intent(ArticlesActivity.this,DescriptionActivity.class);
                intent.putExtra("AUTHOR",articleInfo.getmAuthor());
                intent.putExtra("TITLE",articleInfo.getmTitle());
                intent.putExtra("DESCRIPTION",articleInfo.getmDescription());
                intent.putExtra("URL",articleInfo.getmUrl());
                intent.putExtra("URL_TO_IMAGE",articleInfo.getmUrlToImage());
                startActivity(intent);
            }
        });
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
        articleInfoList = new ArrayList<>();
        if(data != null && !data.isEmpty())
        {
            adapter.addAll(data);
            articleInfoList.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ArticleInfo>> loader) {
        adapter.clear();
    }
}
