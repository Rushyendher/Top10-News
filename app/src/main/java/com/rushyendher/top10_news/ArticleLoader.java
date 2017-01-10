package com.rushyendher.top10_news;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by rushi on 11-01-2017.
 */

public class ArticleLoader extends AsyncTaskLoader<List<ArticleInfo>> {

    private String mUrl;

    public ArticleLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<ArticleInfo> loadInBackground() {
        if(mUrl == null)
            return null;
        URL url = QueryUtils.createURL(mUrl);
        String jsonResponse = null;
        try {
            jsonResponse = QueryUtils.makeHttpConnection(url);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        List<ArticleInfo> articleInfos = QueryUtils.parseArticleJSON(jsonResponse);

        return articleInfos;
    }
}
