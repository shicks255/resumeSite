package com.steven.hicks;


import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.store.StoreItemType;

import java.util.ArrayList;
import java.util.List;

public class ServerStartupTasks
{

    public static void loadDefaultItemTypes()
    {
        List<String> itemTypesToAdd = new ArrayList<>();
        itemTypesToAdd.add("MusicAlbum");
        itemTypesToAdd.add("LegoSet");

        for (String itemType : itemTypesToAdd)
        {
            StoreItemType itemTypeObject = StoreItemType.getItemTypeByName(itemType);

            int firstItemCode = 113;
            if (itemTypeObject == null)
            {
                itemTypeObject = new StoreItemType();
                itemTypeObject.setItemType(itemType);
                HibernateUtil.createItem(itemTypeObject);
            }
        }

    }

}
