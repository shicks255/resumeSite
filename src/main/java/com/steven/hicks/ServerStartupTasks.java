package com.steven.hicks;


import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItems.StoreItemType;

import java.util.ArrayList;
import java.util.List;

public class ServerStartupTasks
{

    public static void loadDefaultItemTypes()
    {
        List<String> itemTypesToAdd = new ArrayList<>();
        itemTypesToAdd.add("Music_Album");
        itemTypesToAdd.add("Lego_Set");

        for (String itemType : itemTypesToAdd)
        {
            StoreItemType itemTypeObject = StoreItemType.getItemTypeByName(itemType);

            if (itemTypeObject == null)
            {
                itemTypeObject = new StoreItemType();
                itemTypeObject.setItemType(itemType);
                HibernateUtil.createItem(itemTypeObject);
            }
        }

    }

}
