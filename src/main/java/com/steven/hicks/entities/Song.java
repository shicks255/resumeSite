package com.steven.hicks.entities;

public class Song
{
    private String title;
    private String album;
    private String artist;
    String smallImageUrl;
    String medImageUrl;


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
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
