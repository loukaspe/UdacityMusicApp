package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class BrowseActivity extends AppCompatActivity {
    // Variables for the Songs' list, the Playing Song and the adapter
    ArrayList<Song> Songs;
    SongAdapterBrowse adapter;
    Song playingNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_song_list);

        // The Song List is retrieved from the Shared Preferences
        Songs = mySharedPreferences.getSongList("songList", this);

        // Code for the Playing Now Song to be the same for all the activities of the app, using the written function of mySharedPreferences
        // Checking if there is already a playingSong that has been passed to this activity from the PlayingNowActivity
        playingNow = mySharedPreferences.getPlayingSong("playingNow", this);
        if (playingNow == null) {
            playingNow = Songs.get(0);
        }

        // Find the List of the XML and set an adapter to it
        adapter = new SongAdapterBrowse(this, Songs, playingNow);
        ListView listView = (ListView) findViewById(R.id.listBrowse);
        listView.setAdapter(adapter);

        // In the end Save again the Songs' list and the Playing Song
        mySharedPreferences.savePlayingSong(playingNow, "playingNow", this);
        mySharedPreferences.saveSongList(Songs, "songList", this);
    }
}