package com.rushyendher.top10_news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rushi on 08-01-2017.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static URL createURL(String stringURL)
    {
        URL url = null;
        if(stringURL == null || stringURL.length() == 0)
            return null;
        try {
            url = new URL(stringURL);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG,e.toString());
            return null;
        }
        return url;
    }

    private static String readInputStream(InputStream inputStream) throws IOException
    {
        if(inputStream == null)
            return null;

        StringBuilder builder = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String output = reader.readLine();
        while (output != null)
        {
            builder.append(output);
            output = reader.readLine();
        }
        return builder.toString();
    }

    public static String makeHttpConnection(URL url) throws IOException
    {
        if(url == null)
            return null;
        String jsonResponse = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == 200)
            {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            }
        }
        catch (IOException e){
            Log.e(LOG_TAG,e.toString());
        }
        finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        return jsonResponse;
    }

    public static List<SourcesInfo> parseSourceJSON(String jsonResponse)
    {
        if(jsonResponse == null)
            return null;
        List<SourcesInfo> sourcesInfos = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray sourcesArray = jsonObject.getJSONArray("sources");
            if(sourcesArray.length() > 0)
            {
                for(int i = 0;i<sourcesArray.length();i++)
                {
                    JSONObject source = sourcesArray.getJSONObject(i);
                    String id = source.getString("id");
                    String name = source.getString("name");
                    String description = source.getString("description");
                    String url = source.getString("url");
                    String category = source.getString("category");

                    JSONObject urlToLogo = source.getJSONObject("urlsToLogos");
                    String logoURL = urlToLogo.getString("small");

                    sourcesInfos.add(new SourcesInfo(id,name,description,url,category,logoURL));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sourcesInfos;
    }

    public static List<ArticleInfo> parseArticleJSON(String jsonResponse)
    {
        if(jsonResponse == null)
            return null;
        List<ArticleInfo> articleInfos = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray articles = jsonObject.getJSONArray("articles");

            for (int i = 0;i<articles.length();i++)
            {
                JSONObject article = articles.getJSONObject(i);
                String author = article.getString("author");
                String title = article.getString("title");
                String description = article.getString("description");
                String url = article.getString("url");
                String urlToImage = article.getString("urlToImage");
                String publishedDate = article.getString("publishedAt");

                articleInfos.add(new ArticleInfo(author,title,description,url,urlToImage,publishedDate));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleInfos;
    }
}