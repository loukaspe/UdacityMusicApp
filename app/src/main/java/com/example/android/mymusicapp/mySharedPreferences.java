package com.example.android.mymusicapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.android.mymusicapp.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by loukaswhatdup on 26/3/2018.
 */

/*  These are function to be used to save and retrieve the Playing Song and the Songs List in order to be the same in
 *  all the acitivities. They are copied from https://stackoverflow.com/questions/7145606/how-android-sharedpreferences-save-store-object  and
 *  https://freakycoder.com/android-notes-40-how-to-save-and-get-arraylist-into-sharedpreference-7d1f044bc79a
 */
public class mySharedPreferences {

    static public void savePlayingSong(Song playingSong, String key, Activity activity)
    {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playingSong);
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    static public void saveSongList(ArrayList<Song> myList, String key, Activity activity)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myList);
        editor.putString(key, json);
        editor.apply();
    }

    static public ArrayList<Song> getSongList(String key, Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Song>>() {}.getType();
        return gson.fromJson(json, type);
    }

    static public Song getPlayingSong(String key, Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Song obj = gson.fromJson(json, Song.class);
        return obj;
    }

}
