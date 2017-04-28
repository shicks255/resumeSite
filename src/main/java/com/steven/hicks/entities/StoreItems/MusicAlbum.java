package com.steven.hicks.entities.StoreItems;

import com.steven.hicks.entities.StoreItemGeneric;

import javax.persistence.*;

@Entity
@DiscriminatorValue("MusicAlbum")
public class MusicAlbum extends StoreItemGeneric
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    String musicAlbumItemId = "";

    @Column
    String artist = "";
    @Column
    String albumTitle = "";
    @Column
    String releaseYear = "";


    @Override
    public String toString()
    {
        return artist + " - " + albumTitle;
    }

    public String getItemCode()
    {
        StoreItemType itemType = StoreItemType.getItemTypeByName("MusicAlbum");
        return "" + itemType.getItemTypeCode() + super.getItemNumber();
    }

//    public String getMusicAlbumItemId()
//    {
//        return musicAlbumItemId;
//    }
//
//    public void setMusicAlbumItemId(String musicAlbumItemId)
//    {
//        this.musicAlbumItemId = musicAlbumItemId;
//    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getAlbumTitle()
    {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle)
    {
        this.albumTitle = albumTitle;
    }

    public String getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear)
    {
        this.releaseYear = releaseYear;
    }
}
