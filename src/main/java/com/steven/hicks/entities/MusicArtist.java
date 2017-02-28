package com.steven.hicks.entities;

public class MusicArtist
{
    private String artistName = "";

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
