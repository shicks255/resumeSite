package com.steven.hicks.Utilities;

import com.steven.hicks.entities.StoreItems.MusicAlbum;

public class StoreItemHelpers
{
    public static Class getStoreItemQueryFromItemType(String itemType)
    {
        if (itemType.equalsIgnoreCase("Music_Album"))
            return MusicAlbum.class;

        return null;
    }


}
