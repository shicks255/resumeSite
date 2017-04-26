package com.steven.hicks.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn()
@DiscriminatorValue("StoreItemGeneric")
public abstract class StoreItemGeneric
{
    @Id
    String itemCode;
    @Column
    String itemName;
    @Column
    String itemDescription;
    @Column
    String getItemType;
    @Column
    String getPrice;

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription)
    {
        this.itemDescription = itemDescription;
    }

    public String getItemCode()
    {
        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getGetItemType()
    {
        return getItemType;
    }

    public void setGetItemType(String getItemType)
    {
        this.getItemType = getItemType;
    }

    public String getGetPrice()
    {
        return getPrice;
    }

    public void setGetPrice(String getPrice)
    {
        this.getPrice = getPrice;
    }
}
