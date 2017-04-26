package com.steven.hicks.entities.StoreItems;

import com.steven.hicks.entities.StoreItem;

import javax.persistence.*;

@Entity
public class MusicAlbum implements StoreItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int itemNumber;

    @Column
    String itemDescription = "";
    @Column
    String itemPrice = "";
    @Column
    int itemType;

    @Column
    String artist = "";
    @Column
    String albumTitle = "";
    @Column
    String releaseYear = "";


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicAlbum that = (MusicAlbum) o;

        return itemNumber == that.itemNumber;
    }

    @Override
    public int hashCode()
    {
        return itemNumber;
    }

    @Override
    public String toString()
    {
        return artist + " - " + albumTitle;
    }

    public String getItemCode()
    {
        StoreItemType itemType = StoreItemType.getItemTypeByName("Music_Album");
        return "" + getItemNumber() + itemType.getItemTypeCode();
    }


    @Override
    public int getItemNumber()
    {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber)
    {
        this.itemNumber = itemNumber;
    }

    @Override
    public String getItemName()
    {
        return toString();
    }

    @Override
    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice()
    {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    @Override
    public int getItemType()
    {
        return itemType;
    }

    public void setItemType(int itemType)
    {
        this.itemType = itemType;
    }

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
