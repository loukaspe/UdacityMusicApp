package com.example.android.mymusicapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class BrowseActivity extends AppCompatActivity {
    ArrayList<Song> Songs;
    SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

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

        // Create an {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        adapter = new SongAdapter(this, Songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // Song_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(adapter);

        // Code for the Playing Now Song to be the same for all the activities of the app, using the written function of mySharedPreferences
        // Checking if there is already a playingSong that has been passed to this activity from the PlayingNowActivity
        Song playingNow = mySharedPreferences.getPlayingSong("playingNow", this);
        if (playingNow == null) {
            playingNow = Songs.get(0);
        }

        mySharedPreferences.savePlayingSong(playingNow, "playingNow", this);

        // Code for the list of Songs to be the same for all the activities of the app. It can only be passed from this activity to the PlayingNow
        mySharedPreferences.saveSongList(Songs, "songList", this);

    }

    //Function to keep the boolean values of Downloaded of the songs
    public boolean[] getSongsDownloadedState() {
        int size = Songs.size();
        boolean[] array = new boolean[size];
        for (int i = 0; i < size; i++) {
            array[i] = Songs.get(i).getDownloaded();
        }
        return array;
    }

//    // Functions Override to keep the Song List after Screen Rotation
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        // Save UI state changes to the savedInstanceState.
//        // This bundle will be passed to onCreate if the process is
//        // killed and restarted.
//        savedInstanceState.putBooleanArray("boolean", getSongsDownloaded());
//        // etc.
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        for (int i = 0; i < Songs.size(); i++) {
//            boolean temp = savedInstanceState.getBooleanArray("boolean")[i];
//            if (temp == false) {
//                adapter.setSongsDownloaded(false, Songs.get(i), adapter.download);
//            } else {
//                adapter.setSongsDownloaded(true, Songs.get(i), adapter.download);
//
//            }
//        }
//    }

}