package com.steven.hicks.entities;

public class TopArtistRecord
{

    private MusicArtist musicArtist;
    private int playCount;
    private int rank;

    public TopArtistRecord()
    {

    }

    public TopArtistRecord(MusicArtist artist, int playCount, int rank)
    {
        musicArtist = artist;
        this.playCount = playCount;
        this.rank = rank;
    }


    public MusicArtist getMusicArtist()
    {
        return musicArtist;
    }

    public void setMusicArtist(MusicArtist musicArtist)
    {
        this.musicArtist = musicArtist;
    }

    public int getPlayCount()
    {
        return playCount;
    }

    public void setPlayCount(int playCount)
    {
        this.playCount = playCount;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }
}
