package com.rushyendher.top10_news;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import javax.crypto.spec.DESedeKeySpec;

/**
 * Created by rushi on 08-01-2017.
 */

public class SourcesInfo implements Parcelable {
    private String mSourceId;
    private String mName;
    private String mDescription;
    private String mURL;
    private String mCategory;
    private String mUrlToLogo;

    public SourcesInfo(String mSourceId, String mName, String mDescription, String mURL,
                       String mCategory, String mUrlToLogo) {
        this.mSourceId = mSourceId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mURL = mURL;
        this.mCategory = mCategory;
        this.mUrlToLogo = mUrlToLogo;
    }

    protected SourcesInfo(Parcel in) {
        mSourceId = in.readString();
        mName = in.readString();
        mDescription = in.readString();
        mURL = in.readString();
        mCategory = in.readString();
        mUrlToLogo = in.readString();
    }

    public static final Creator<SourcesInfo> CREATOR = new Creator<SourcesInfo>() {
        @Override
        public SourcesInfo createFromParcel(Parcel in) {
            return new SourcesInfo(in);
        }

        @Override
        public SourcesInfo[] newArray(int size) {
            return new SourcesInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSourceId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeString(mURL);
        dest.writeString(mCategory);
        dest.writeString(mUrlToLogo);
    }
}
