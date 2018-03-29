package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class SearchActivity extends AppCompatActivity {
    ListView list;
    SongAdapterSearchDownload adapter;
    ArrayList<Song> songs;
    ArrayList<Song> searchedSongs;      // an ArrayList that will store the results of the search to match the Song List
    Song playingNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_song_list);

        playingNow = mySharedPreferences.getPlayingSong("playingNow", this);
        searchedSongs = new ArrayList<Song>();
        songs = mySharedPreferences.getSongList("songList", this);
        list = (ListView) findViewById(R.id.listview);

        adapter = new SongAdapterSearchDownload(this, searchedSongs, playingNow);
        list.setAdapter(adapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);           // inititate a search view

        // perform set on query text listener event
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText, songs, searchedSongs);
                return false;
            }
        });
    }
}