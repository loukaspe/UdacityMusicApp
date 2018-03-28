package com.example.android.mymusicapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by loukaswhatdup on 27/3/2018.
 */

    // Class that inherits the SongAdapter that will be used in the search activity. The layout that
    // this class has does not have a download button as the base SongAdapter
public class SongAdapterSearch extends SongAdapter {
    private static final String LOG_TAG = SongAdapter.class.getSimpleName();

    SongAdapterSearch(Activity context, ArrayList<Song> songs) {
        super(context, songs);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout_search, parent, false);

        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameText = (TextView) listItemView.findViewById(R.id.name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameText.setText(currentSong.getName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView albumText = (TextView) listItemView.findViewById(R.id.album);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        albumText.setText(currentSong.getAlbum());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView durationText = (TextView) listItemView.findViewById(R.id.duration);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        durationText.setText(String.valueOf(currentSong.getDuration()));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView singerText = (TextView) listItemView.findViewById(R.id.singer);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        singerText.setText(currentSong.getSinger());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
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
}