package com.steven.hicks.Portal;

import com.steven.hicks.Utilities.FileUploadUtil;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.FileRequest;
import com.steven.hicks.entities.store.items.LegoSet;
import com.steven.hicks.entities.store.items.MusicAlbum;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.StoreItemType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class PortalItemLogic
{
    public static void addMusicAlbum(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        String productDescription = params.get("productDescriptionMusicAlbum");
        String productPrice = params.get("productPriceMusicAlbum");
        String artistName = params.get("artistName");
        String albumTitle = params.get("albumTitle");
        String releaseYear = params.get("releaseYear");

        MusicAlbum musicAlbum = new MusicAlbum();
        musicAlbum.setItemDescription(productDescription);
        musicAlbum.setItemPrice(productPrice);
        musicAlbum.setArtist(artistName);
        musicAlbum.setAlbumTitle(albumTitle);
        musicAlbum.setReleaseYear(releaseYear);
        musicAlbum.setItemName(artistName + " - " + albumTitle);
        musicAlbum.setItemType(StoreItemType.getItemTypeByName("MusicAlbum").getItemTypeCode());

        File file = fr.getUploadedFile();

        if (file != null && file.isFile())
        {
            StoreItemPicture picture = new StoreItemPicture();
            byte[] bytes = new byte[(int) file.length()];

            try(FileInputStream inputStream = new FileInputStream(file))
            {
                inputStream.read(bytes);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            picture.setImage(bytes);
            HibernateUtil.createItem(picture);
            musicAlbum.setPictureObjectId(picture.getObjectId());
        }
        HibernateUtil.createItem(musicAlbum);
    }

    public static void addLegoSet(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        String productDescription = params.get("productDescriptionLego");
        String productPrice = params.get("productPriceLego");
        String productName = params.get("productNameLego");
        String legoCode = params.get("legoCode");
        String legoTheme = params.get("legoTheme");
        String releaseYear = params.get("releaseYearLego");
        int numberOfPieces = Integer.valueOf(params.get("numberOfPieces"));

        LegoSet legoSet = new LegoSet();
        legoSet.setItemDescription(productDescription);
        legoSet.setItemPrice(productPrice);
        legoSet.setItemName(productName);
        legoSet.setLegoCode(legoCode);
        legoSet.setLegoTheme(legoTheme);
        legoSet.setNumberOfPieces(numberOfPieces);
        legoSet.setReleaseYear(releaseYear);
        legoSet.setItemType(StoreItemType.getItemTypeByName("LegoSet").getItemTypeCode());

        File file = fr.getUploadedFile();

        if (file != null && file.isFile())
        {
            StoreItemPicture picture = new StoreItemPicture();
            byte[] bytes = new byte[(int) file.length()];

            try(FileInputStream inputStream = new FileInputStream(file))
            {
                inputStream.read(bytes);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

            picture.setImage(bytes);
            HibernateUtil.createItem(picture);
            legoSet.setPictureObjectId(picture.getObjectId());
        }
        HibernateUtil.createItem(legoSet);
    }
}
