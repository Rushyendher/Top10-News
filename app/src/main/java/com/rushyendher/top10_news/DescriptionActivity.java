package com.rushyendher.top10_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private TextView authorTextview;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView urlTextView;
    private ImageView urlToImageTextView;

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getInfoFromPreviousActivity();
        findViewByIds();

        new ImageLoad(urlToImage,urlToImageTextView).execute();
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        urlTextView.setText(url);
        authorTextview.setText(author);
    }

    private void getInfoFromPreviousActivity()
    {
        //getting article info from headlines
        author  = getIntent().getStringExtra("AUTHOR");
        title   = getIntent().getStringExtra("TITLE");
        description = getIntent().getStringExtra("DESCRIPTION");
        url = getIntent().getStringExtra("URL");
        urlToImage = getIntent().getStringExtra("URL_TO_IMAGE");
    }

    private void findViewByIds()
    {
        //Finding views by iD's
        authorTextview = (TextView) findViewById(R.id.headline_author);
        titleTextView = (TextView) findViewById(R.id.headline_title);
        descriptionTextView = (TextView)findViewById(R.id.headline_description);
        urlTextView = (TextView)findViewById(R.id.headline_url);
        urlToImageTextView = (ImageView) findViewById(R.id.headline_image);
    }
}
