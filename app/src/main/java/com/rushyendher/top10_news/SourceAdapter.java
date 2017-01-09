package com.rushyendher.top10_news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rushi on 09-01-2017.
 */

public class SourceAdapter extends ArrayAdapter<SourcesInfo> {
    public SourceAdapter(Context context, List<SourcesInfo> sources) {
        super(context, 0,sources);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.source_list_view, parent, false);
        }
        SourcesInfo source = getItem(position);

        TextView sourceTitle = (TextView)listView.findViewById(R.id.source_title);
        sourceTitle.setText(source.getmName());

        return listView;
    }
}
