package com.example.android.mymusicapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class PlayingNowActivity extends AppCompatActivity {

    // variable to check if the song is paused or not
    boolean paused = false;
    // Object Variable for the current Song
    Song playingNowSong;
    // Song List
    ArrayList<Song> songs;
    // TextViews of the Layout for the PlayingSong
    TextView songsName;
    TextView songsAlbum;
    TextView songsSinger;
    TextView songsDuration;
    // Variable for the index of the playingSong in the Song List
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing_now_layout);

        // Code to retrieve the Song List in order to make the Next and Previous Songs to work
        songs = mySharedPreferences.getSongList("songList", this);

        // Code to set the Playing Now song Info to the Playing Now Song from the Shared Preferences
        playingNowSong = mySharedPreferences.getPlayingSong("playingNow", this);

        // Then Find each field in the XML and set it to the Playing Song's
        songsName = (TextView) findViewById(R.id.name);
        songsName.setText(playingNowSong.getName());

        songsAlbum = (TextView) findViewById(R.id.album);
        songsAlbum.setText(playingNowSong.getAlbum());

        songsSinger = (TextView) findViewById(R.id.singer);
        songsSinger.setText(playingNowSong.getSinger());

        songsDuration = (TextView) findViewById(R.id.duration);
        songsDuration.setText(String.valueOf(playingNowSong.getDuration()));

        // Then we save the PlayingNow song again here in case we changed it
        mySharedPreferences.savePlayingSong(playingNowSong, "playingNow", this);

        // Find the view that shows the PLay and the Pause Button: We want the the
        // user clicks this Button to change from Play to Pause and the opposite
        final Button playingNow = (Button) findViewById(R.id.playNPauseButton);

        // Set a Click Listener fot that view
        playingNow.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                Drawable playNPause;
                if (paused == false) {
                    playNPause = getResources().getDrawable(R.drawable.pause_small);
                    playingNow.setCompoundDrawablesWithIntrinsicBounds(null, null, playNPause, null);
                    paused = true;

                } else {
                    playNPause = getResources().getDrawable(R.drawable.play_small);
                    playingNow.setCompoundDrawablesWithIntrinsicBounds(null, null, playNPause, null);
                    paused = false;
                }
            }
        });

        // Find the view that shows the NextSong Button: We want the the
        // user clicks this Button to change the song
        final Button nextSong = (Button) findViewById(R.id.nextSongButton);

        // Set a Click Listener fot that view
        nextSong.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                position = -1;
                for (int i = 0; i < songs.size(); i++) {
                    if (playingNowSong.equals(songs.get(i))) {
                        position = i;
                    }
                }

                if (position == songs.size() - 1) {
                    playingNowSong = songs.get(0);
                } else {
                    playingNowSong = songs.get(++position);
                }

                songsName.setText(playingNowSong.getName());
                songsAlbum.setText(playingNowSong.getAlbum());
                songsSinger.setText(playingNowSong.getSinger());
                songsDuration.setText(String.valueOf(playingNowSong.getDuration()));
                mySharedPreferences.savePlayingSong(playingNowSong, "playingNow", PlayingNowActivity.this);
            }
        });

        // Find the view that shows the PreviousSong Button: We want the the
        // user clicks this Button to change the song
        final Button previousSong = (Button) findViewById(R.id.previousSongButton);

        // Set a Click Listener fot that view
        previousSong.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Playing Now View is clicked
            @Override
            public void onClick(View view) {
                position = -1;
                for (int i = 0; i < songs.size(); i++) {
                    if (playingNowSong.equals(songs.get(i))) {
                        position = i;
                    }
                }

                if (position == 0) {
                    playingNowSong = songs.get(songs.size() - 1);
                } else {
                    playingNowSong = songs.get(--position);
                }

                songsName.setText(playingNowSong.getName());
                songsAlbum.setText(playingNowSong.getAlbum());
                songsSinger.setText(playingNowSong.getSinger());
                songsDuration.setText(String.valueOf(playingNowSong.getDuration()));
                mySharedPreferences.savePlayingSong(playingNowSong, "playingNow", PlayingNowActivity.this);
            }
        });
    }

    // Functions Override to keep the Playing Song after Screen Rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("position", position);
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        position = savedInstanceState.getInt("position");
        playingNowSong = songs.get(position);
        songsName.setText(playingNowSong.getName());
        songsAlbum.setText(playingNowSong.getAlbum());
        songsSinger.setText(playingNowSong.getSinger());
        songsDuration.setText(String.valueOf(playingNowSong.getDuration()));
    }
}


