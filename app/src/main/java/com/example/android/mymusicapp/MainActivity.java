package com.example.android.mymusicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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

        // Creation of the Song List and playing Song to be available for all the other activities
        ArrayList<Song> Songs = new ArrayList<Song>();
        Songs.add(new Song("God's Plan", "God", 1.26, "Drake"));
        Songs.add(new Song("All The Stars", "World", 1.37, "Kendrick Lamar"));
        Songs.add(new Song("RockStar", "Music", 1.45, "Post Malone"));
        Songs.add(new Song("Havana", "First", 1.55, "Camila Cabello"));
        Songs.add(new Song("Say Something", "Friends", 2.25, "Justin Timberlake"));
        Songs.add(new Song("For You", "Myself", 3.46, "Rita Ora"));
        Songs.add(new Song("Congratulations", "Music", 2.16, "Post Malone"));
        Songs.add(new Song("Make It Rain", "Perfect", 1.48, "Ed Sheeran"));
        Songs.add(new Song("Attention", "Best", 2.36, "Charlie Puth"));
        Songs.add(new Song("Perfect", "Perfect", 3.22, "Ed Sheeran"));

        Song playingNowSong = Songs.get(0);

        // Save them to the Shared Preferences
        mySharedPreferences.savePlayingSong(playingNowSong, "playingNow", this);
        mySharedPreferences.saveSongList(Songs, "songList", this);
    }
}
