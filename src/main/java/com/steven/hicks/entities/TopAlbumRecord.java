package com.steven.hicks.entities;

public class TopAlbumRecord
{
    private Album album;
    private String year;

    private int rank;
    private int playCount;

    public Album getAlbum()
    {
        return album;
    }

    public void setAlbum(Album album)
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

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getPlayCount()
    {
        return playCount;
    }

    public void setPlayCount(int playCount)
    {
        this.playCount = playCount;
    }
}
