package com.example.android.mymusicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class SearchActivity extends AppCompatActivity {
    ListView list;
    SongAdapterSearch adapter;
    ArrayList<Song> songs;
    ArrayList<Song> searchedSongs;      // an ArrayList that will store the results of the search to match the Song List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        searchedSongs = new ArrayList<Song>();
        songs = mySharedPreferences.getSongList("songList", this);
        list = (ListView) findViewById(R.id.listview);

        adapter = new SongAdapterSearch(this, searchedSongs);
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