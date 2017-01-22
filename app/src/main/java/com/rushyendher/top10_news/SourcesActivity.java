package com.rushyendher.top10_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        final ArrayList<SourcesInfo> sources = getIntent().getParcelableArrayListExtra("SOURCES");
        SourceAdapter sourceAdapter = new SourceAdapter(this,sources);
        ListView listView = (ListView)findViewById(R.id.activity_sources_list);
        listView.setAdapter(sourceAdapter);

        String title = getIntent().getStringExtra("SOURCE");
        setTitle(title + " sources");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SourcesInfo sourcesInfo = sources.get(position);
                Intent intent = new Intent(SourcesActivity.this,ArticlesActivity.class);
                intent.putExtra("SOURCE_ID",sourcesInfo.getmSourceId());
                startActivity(intent);
            }
        });
    }
}
