package com.rushyendher.top10_news;

/**
 * Created by rushi on 10-01-2017.
 */

public class ArticleInfo {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlToImage;
    private String mPublishedDate;

    public ArticleInfo(String mAuthor, String mTitle, String mDescription, String mUrl,
                       String mUrlToImage, String mPublishedDate) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
        this.mUrlToImage = mUrlToImage;
        this.mPublishedDate = mPublishedDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmUrlToImage() {
        return mUrlToImage;
    }

    public String getmPublishedDate() {
        return mPublishedDate;
    }
}
