package com.example.android.mymusicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by loukaswhatdup on 23/3/2018.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    private static final String LOG_TAG = SongAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param songs   A List of AndroidFlavor objects to display in a list
     */
    public SongAdapter(Activity context, ArrayList<Song> songs) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, songs);
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
                    R.layout.list_item_layout, parent, false);

            // Find the view that shows the Download Button
            final Button download = (Button) listItemView.findViewById(R.id.downloadButton);

            // Set a Click Listener fot that view
            download.setOnClickListener(new View.OnClickListener() {
                // The code in this method will be executed when the Download Button is clicked
                @Override
                public void onClick(View view) {
                    // The Button color will change and the song will be added to the Downloaded List
                    Song currentSong = getItem(position);
                    if (currentSong.getDownloaded() == false) {
                        //setSongsDownloaded(true, currentSong, download);
                        Drawable downloadIcon = getContext().getResources().getDrawable(R.drawable.download_icon_true);
                        download.setCompoundDrawablesWithIntrinsicBounds(null, null, downloadIcon, null);
                        currentSong.setDownloaded(true);
                        Toast.makeText(getContext(), getContext().getResources().getText(R.string.addSong),
                                Toast.LENGTH_SHORT).show();
                    } else {
//                        setSongsDownloaded(false, currentSong, download);
                        Drawable downloadIcon = getContext().getResources().getDrawable(R.drawable.download_icon_false);
                        download.setCompoundDrawablesWithIntrinsicBounds(null, null, downloadIcon, null);
                        currentSong.setDownloaded(false);
                        Toast.makeText(getContext(), getContext().getResources().getText(R.string.deleteSong),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
