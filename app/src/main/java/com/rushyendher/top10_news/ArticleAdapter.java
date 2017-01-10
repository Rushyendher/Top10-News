package com.rushyendher.top10_news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rushi on 11-01-2017.
 */

public class ArticleAdapter extends ArrayAdapter<ArticleInfo> {

    public ArticleAdapter(Context context, List<ArticleInfo> sources) {
        super(context, 0,sources);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_view, parent, false);
        }

        ArticleInfo articleInfo = getItem(position);
        TextView articleTitleView = (TextView) listView.findViewById(R.id.article_title);
        articleTitleView.setText(articleInfo.getmTitle());

        return listView;
    }

}
