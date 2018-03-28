package com.example.android.mymusicapp;

/**
 * Created by Loukas on 23/3/2018.
 */

public class Song {

    /* Songs Fields: Song's Name, Album, Singer and Duration(as a Double Variable).
     * Also a variable bool to see if the user has chosen to download this song */
    private String name;
    private String album;
    private double duration;
    private String singer;
    private boolean downloaded;

    /* Class Ctor */
    public Song(String sname, String salbum, double sduration, String ssinger) {
        name = sname;
        album = salbum;
        duration = sduration;
        singer = ssinger;
        downloaded = false;
    }

    /* Getters for the Class Fields */
    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public double getDuration() {
        return duration;
    }

    public String getSinger() {
        return singer;
    }

    public boolean getDownloaded() { return downloaded; }

    // Function to check if a Song is the same as another one given
    public boolean equals(Song s) {
        if (this.name.equals(s.name) && this.album.equals(s.album) && this.duration == s.duration && this.singer.equals(s.singer) && this.downloaded == s.downloaded) {
            return true;
        } else {
            return false;
        }
    }

    // Setter for the private Downloaded Function
    public void setDownloaded(boolean d) {
        this.downloaded = d;
    }
}



