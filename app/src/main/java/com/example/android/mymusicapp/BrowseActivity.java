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

        // Code for the Playing Now Song to be the same for all the activities of the app, using the written function of mySharedPreferences
        // Checking if there is already a playingSong that has been passed to this activity from the PlayingNowActivity
        playingNow = mySharedPreferences.getPlayingSong("playingNow", this);
        if (playingNow == null) {
            playingNow = Songs.get(0);
        }

        // Create a list of Songs, if there isn't one already created
        if (mySharedPreferences.getSongList("songList", this) != null) {
            Songs = mySharedPreferences.getSongList("songList", this);
        } else {
            Songs = new ArrayList<Song>();
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