package com.rushyendher.top10_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        ArrayList<SourcesInfo> sources = getIntent().getParcelableArrayListExtra("SOURCES");
        SourceAdapter sourceAdapter = new SourceAdapter(this,sources);
        ListView listView = (ListView)findViewById(R.id.activity_sources_list);
        listView.setAdapter(sourceAdapter);
    }
}
