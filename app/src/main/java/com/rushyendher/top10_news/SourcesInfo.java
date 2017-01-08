package com.rushyendher.top10_news;

import java.util.List;

/**
 * Created by rushi on 08-01-2017.
 */

public class SourcesInfo {
    private String mSourceId;
    private String mName;
    private String mDescription;
    private String mURL;
    private String mCategory;
    private String mUrlToLogo;
    private List<String> mSortByAvailability;

    public SourcesInfo(String mSourceId, String mName, String mDescription, String mURL,
                       String mCategory, String mUrlToLogo, List<String> mSortByAvailability) {
        this.mSourceId = mSourceId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mURL = mURL;
        this.mCategory = mCategory;
        this.mUrlToLogo = mUrlToLogo;
        this.mSortByAvailability = mSortByAvailability;
    }

    public String getmSourceId() {
        return mSourceId;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmURL() {
        return mURL;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmUrlToLogo() {
        return mUrlToLogo;
    }

    public List<String> getmSortByAvailability() {
        return mSortByAvailability;
    }
}
