package com.steven.hicks.entities;

/**
 * Created by Steven on 0001, March 1, 2017.
 */
public class Album
{

    String artist = "";
    String album = "";
    String year = "";

    public Album()
    {}

    @Override
    public String toString()
    {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }
}