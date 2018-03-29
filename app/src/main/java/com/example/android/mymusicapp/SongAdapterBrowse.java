package com.example.android.mymusicapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class SongAdapterBrowse extends ArrayAdapter<Song> {

    private static final String LOG_TAG = SongAdapterBrowse.class.getSimpleName();

    // Variables for the Songs list, the Playing Song and the Activity
    Activity context;
    ArrayList<Song> songs;
    Song playingNow;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param Songs   A List of AndroidFlavor objects to display in a list
     */
    public SongAdapterBrowse(Activity context, ArrayList<Song> Songs, Song playingSong) {
        super(context, 0, Songs);
        this.context = context;
        songs = Songs;
        playingNow = playingSong;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout_browse, parent, false);

            // Find the view that shows the Download Button
            final Button download = (Button) listItemView.findViewById(R.id.downloadButton);

            // Find the Linear Layout of the layout of a Song
            LinearLayout layout = (LinearLayout) listItemView.findViewById(R.id.layoutBrowse);

            // Set a Click Listener for the Linear Layout that represents a Song
            layout.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the Download Button is clicked
                @Override
                public void onClick(View view) {
                    // The Clickable Song will become the Playing Song
                    Song currentSong = getItem(position);
                    playingNow = currentSong;
                    mySharedPreferences.savePlayingSong(playingNow, "playingNow", context);
                    Toast.makeText(getContext(), getContext().getResources().getText(R.string.changeSong),
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Set a Click Listener fot that view
            download.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the Download Button is clicked
                @Override
                public void onClick(View view) {
                    // The Button color will change and the song will be added to the Downloaded List
                    Song currentSong = getItem(position);
                    if (currentSong.getDownloaded() == false) {
                        Drawable downloadIcon = getContext().getResources().getDrawable(R.drawable.download_icon_true);
                        download.setCompoundDrawablesWithIntrinsicBounds(null, null, downloadIcon, null);
                        currentSong.setDownloaded(true);
                        Toast.makeText(getContext(), getContext().getResources().getText(R.string.addSong),
                                Toast.LENGTH_SHORT).show();
                        mySharedPreferences.saveSongList(songs, "songList", context);
                    } else {
                        Drawable downloadIcon = getContext().getResources().getDrawable(R.drawable.download_icon_false);
                        download.setCompoundDrawablesWithIntrinsicBounds(null, null, downloadIcon, null);
                        currentSong.setDownloaded(false);
                        Toast.makeText(getContext(), getContext().getResources().getText(R.string.deleteSong),
                                Toast.LENGTH_SHORT).show();
                        mySharedPreferences.saveSongList(songs, "songList", context);
                    }
                }
            });

        }

        // Find the Current Song of the list, locate each field in the XML and then set it
        // the same as the Current's Song
        Song currentSong = getItem(position);

        TextView nameText = (TextView) listItemView.findViewById(R.id.name);
        nameText.setText(currentSong.getName());

        TextView albumText = (TextView) listItemView.findViewById(R.id.album);
        albumText.setText(currentSong.getAlbum());

        TextView durationText = (TextView) listItemView.findViewById(R.id.duration);
        durationText.setText(String.valueOf(currentSong.getDuration()));

        TextView singerText = (TextView) listItemView.findViewById(R.id.singer);
        singerText.setText(currentSong.getSinger());

        Button download = (Button) listItemView.findViewById(R.id.downloadButton);
        if (currentSong.getDownloaded() == true) {
            download.setCompoundDrawablesWithIntrinsicBounds(null, null, getContext().getResources().getDrawable(R.drawable.download_icon_true), null);
        } else {
            download.setCompoundDrawablesWithIntrinsicBounds(null, null, getContext().getResources().getDrawable(R.drawable.download_icon_false), null);
        }

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}



