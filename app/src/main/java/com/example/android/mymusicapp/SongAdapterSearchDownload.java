package com.example.android.mymusicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by loukaswhatdup on 27/3/2018.
 */

// Class that inherits the SongAdapterBrowse that will be used in the Search and in the Download activity. The layout that
// this class has does not have a download button as the base SongAdapterBrowse
public class SongAdapterSearchDownload extends SongAdapterBrowse {
    private static final String LOG_TAG = SongAdapterBrowse.class.getSimpleName();

    SongAdapterSearchDownload(Activity context, ArrayList<Song> songs, Song playingSong) {
        super(context, songs, playingSong);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout_search_download, parent, false);

        }

        // Find the Linear Layout of the XML
        LinearLayout layout = (LinearLayout) listItemView.findViewById(R.id.layoutSearchDownload);

        // Set a Click Listener fot the Linear Layout that represents a Song
        layout.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Download Button is clicked
            @Override
            public void onClick(View view) {
                // The clickable Song will become the Playing Song
                Song currentSong = getItem(position);
                playingNow = currentSong;
                mySharedPreferences.savePlayingSong(playingNow, "playingNow", context);
                Toast.makeText(getContext(), getContext().getResources().getText(R.string.changeSong),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Find the Current song of the list and set each field of the Layout to the Current's Fields
        Song currentSong = getItem(position);

        TextView nameText = (TextView) listItemView.findViewById(R.id.name);
        nameText.setText(currentSong.getName());

        TextView albumText = (TextView) listItemView.findViewById(R.id.album);
        albumText.setText(currentSong.getAlbum());

        TextView durationText = (TextView) listItemView.findViewById(R.id.duration);
        durationText.setText(String.valueOf(currentSong.getDuration()));

        TextView singerText = (TextView) listItemView.findViewById(R.id.singer);
        singerText.setText(currentSong.getSinger());

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }

    // Function to filter the Searches, copied from http://abhiandroid.com/ui/searchview and slightly changed
    public void filter(String text, ArrayList<Song> songs, ArrayList<Song> searchedSongs) {
        text = text.toLowerCase(Locale.getDefault());
        searchedSongs.clear();

        if (text.length() == 0) {
            searchedSongs.addAll(songs);
        } else {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getName().toLowerCase(Locale.getDefault()).contains(text)) {
                    searchedSongs.add(songs.get(i));
                }
            }
        }

        notifyDataSetChanged();
    }

    // Function to add the Downloaded Songs to the Downloads
    public void addToDownloads(ArrayList<Song> songs, ArrayList<Song> downloads) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getDownloaded() == true) {
                downloads.add(songs.get(i));
            }
        }
    }
}