package com.steven.hicks.entities;

public class MusicArtist
{
    private String artistName = "";

    String smallImageUrl;
    String medImageUrl;

    public MusicArtist() {}

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

    public String getSmallImageUrl()
    {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl)
    {
        this.smallImageUrl = smallImageUrl;
    }

    public String getMedImageUrl()
    {
        return medImageUrl;
    }

    public void setMedImageUrl(String medImageUrl)
    {
        this.medImageUrl = medImageUrl;
    }
}
