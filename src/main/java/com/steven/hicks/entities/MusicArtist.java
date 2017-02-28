package com.steven.hicks.entities;

/**
 * Created by Steven on 0027, February 27, 2017.
 */
public class MusicArtist
{
    String artistName = "";

    public MusicArtist(String name)
    {
        this.artistName = name;
    }

    @Override
    public String toString()
    {
        return "MusicArtist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }

    public String getArtistName()
    {
        return artistName;
    }

    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }
}
