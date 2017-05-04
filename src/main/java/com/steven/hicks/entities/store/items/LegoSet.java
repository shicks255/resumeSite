package com.steven.hicks.entities.store.items;

import com.steven.hicks.entities.store.StoreItemType;
import com.steven.hicks.entities.StoreItemGeneric;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LegoSet")
public class LegoSet extends StoreItemGeneric
{

    @Column
    private String legoCode = "";

    @Column
    private String legoTheme = "";

    @Column
    private int numberOfPieces;

    @Column
    private String releaseYear = "";


    public String getItemCode()
    {
        StoreItemType itemType = StoreItemType.getItemTypeByName("MusicAlbum");
        return "" + itemType.getItemTypeCode() + super.getItemNumber();
    }

    public String getLegoCode()
    {
        return legoCode;
    }

    public void setLegoCode(String legoCode)
    {
        this.legoCode = legoCode;
    }

    public String getLegoTheme()
    {
        return legoTheme;
    }

    public void setLegoTheme(String legoTheme)
    {
        this.legoTheme = legoTheme;
    }

    public int getNumberOfPieces()
    {
        return numberOfPieces;
    }

    public void setNumberOfPieces(int numberOfPieces)
    {
        this.numberOfPieces = numberOfPieces;
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
