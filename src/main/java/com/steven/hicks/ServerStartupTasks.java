package com.steven.hicks;


import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.store.StoreItemType;

import javax.servlet.ServletContext;
import java.io.File;
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
            if (itemTypeObject == null)
            {
                itemTypeObject = new StoreItemType();
                itemTypeObject.setItemType(itemType);
                HibernateUtil.createItem(itemTypeObject);
            }
        }
    }

    public static void initializePictures(ServletContext sc)
    {
        String path = sc.getRealPath("/");
        sc.setAttribute("imagesPath", path.replace(path, "") + File.separator + "images" + File.separator);

        PicturesLogic.loadPicturesForGallery(sc);
    }

}
