package com.steven.hicks.entities;

public class MusicArtist
{
    private String artistName = "";

    private String url = "";
    private String imageURL = "";

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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
}
