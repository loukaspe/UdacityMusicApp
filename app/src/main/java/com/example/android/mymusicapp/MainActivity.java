package com.example.android.mymusicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the view that shows the Playing Now Activity
        Button playingNow = (Button) findViewById(R.id.playingNow);

        // Set a Click Listener fot that view
        playingNow.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                Intent playingNowIntent = new Intent(MainActivity.this, PlayingNowActivity.class);
                startActivity(playingNowIntent);
            }
        });

        // Find the view that shows the Search Activity
        Button search = (Button) findViewById(R.id.search);

        // Set a Click Listener fot that view
        search.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        // Find the view that shows the Browse Activity
        Button browse = (Button) findViewById(R.id.browse);

        // Set a Click Listener fot that view
        browse.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                Intent browseIntent = new Intent(MainActivity.this, BrowseActivity.class);
                startActivity(browseIntent);
            }
        });

        // Find the view that shows the Download Activity
        Button download = (Button) findViewById(R.id.download);

        // Set a Click Listener fot that view
        download.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                Intent downloadIntent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(downloadIntent);
            }
        });
    }
}
