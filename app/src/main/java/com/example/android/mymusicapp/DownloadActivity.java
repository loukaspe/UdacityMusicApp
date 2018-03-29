package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class DownloadActivity extends AppCompatActivity {

    // Variables for the List of the Activity, the Songs' List, the Downloaded Songs's List and
    // the Playing Song
    ListView list;
    SongAdapterSearchDownload adapter;
    ArrayList<Song> songs;
    ArrayList<Song> downloadedSongs;
    Song playingNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_song_list);

        // Initialise each variable; The Songs and the Playing Song are from the Shared Preferences
        playingNow = mySharedPreferences.getPlayingSong("playingNow", this);
        downloadedSongs = new ArrayList<Song>();
        songs = mySharedPreferences.getSongList("songList", this);

        // Find the List and set an adapter to it
        list = (ListView) findViewById(R.id.listDownload);
        adapter = new SongAdapterSearchDownload(this, downloadedSongs, playingNow);
        list.setAdapter(adapter);

        // Then add the Downloaded Songs from the Song List to it through my addToDownloads function
        adapter.addToDownloads(songs, downloadedSongs);
    }
}