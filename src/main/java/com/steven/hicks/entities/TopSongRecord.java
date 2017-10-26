package com.steven.hicks.entities;

public class TopSongRecord
{
    private Song song;
    private int rank;
    private int playCount;

    public Song getSong()
    {
        return song;
    }

    public void setSong(Song song)
    {
        this.song = song;
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
