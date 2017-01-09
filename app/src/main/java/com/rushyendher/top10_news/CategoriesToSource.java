package com.rushyendher.top10_news;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rushi on 09-01-2017.
 */

public class CategoriesToSource {
    private String mCategory;
    private ArrayList<SourcesInfo> sourcesInfos;

    public CategoriesToSource(String mCategory, ArrayList<SourcesInfo> sourcesInfos) {
        this.mCategory = mCategory;
        this.sourcesInfos = sourcesInfos;
    }

    public String getmCategory() {
        return mCategory;
    }

    public ArrayList<SourcesInfo> getSourcesInfos() {
        return sourcesInfos;
    }
}
