package com.steven.hicks.entities;

import javax.persistence.Entity;


public interface StoreItem
{

    public String getItemName();
    public String getItemDescription();
    public int getItemNumber();
    public int getItemType();
    public String getItemPrice();

    public String getItemCode();


}
