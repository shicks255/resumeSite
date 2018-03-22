package com.steven.hicks.Portal;

import com.steven.hicks.PicturesLogic;
import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.Utilities.FileUploadUtil;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.FileRequest;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.StoreItemType;
import com.steven.hicks.entities.store.items.LegoSet;
import com.steven.hicks.entities.store.items.MusicAlbum;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Map;

public class PortalItemLogic
{
    public static void addMusicAlbum(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        String productDescription = params.get("productDescriptionMusicAlbum");
        BigDecimal productPrice = new BigDecimal(params.get("productPriceMusicAlbum"));
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

        Session session = HibernateUtil.sessionFactory.openSession();
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
            picture.setStoreItemGeneric(musicAlbum);
            picture.setPictureCaption(musicAlbum.getAlbumTitle());

            session.beginTransaction();
            session.save(picture);
            musicAlbum.getItemPictures().add(picture);

            StoreItemPicture smallPicture = new StoreItemPicture();
            byte[] resizedBytes = PicturesLogic.resizePicturesForThumbnailGallery(bytes);
            if (resizedBytes != null)
            {
                smallPicture.setImage(resizedBytes);
                smallPicture.setStoreItemGeneric(musicAlbum);
                smallPicture.setPictureCaption(musicAlbum.getAlbumTitle() + "_small");
                session.save(smallPicture);
                musicAlbum.getItemPictures().add(smallPicture);
            }

        }
        session.save(musicAlbum);
        session.getTransaction().commit();
        session.close();
    }

    public static void editMusicAlbum(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        int itemNumber = CommonUtils.getInteger(params.get("itemNumber"));

        MusicAlbum albumToEdit = (MusicAlbum)MusicAlbum.getItem(itemNumber);

        if (albumToEdit != null)
        {
            String newName = CommonUtils.getString(params.get("artistName"));
            String newAlbumName = CommonUtils.getString(params.get("albumName"));
            String newPrice = CommonUtils.getString(params.get("price"));
            String newReleaseYear = CommonUtils.getString(params.get("releaseYear"));
            String newDescription = CommonUtils.getString(params.get("itemDescription"));

            albumToEdit.setArtist(newName);
            albumToEdit.setAlbumTitle(newAlbumName);
            albumToEdit.setItemDescription(newDescription);

            BigDecimal price = new BigDecimal(newPrice);
            if (price != null)
                albumToEdit.setItemPrice(new BigDecimal(newPrice));
            albumToEdit.setReleaseYear(newReleaseYear);

            HibernateUtil.updateItem(albumToEdit);
        }
    }

    public static void addLegoSet(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        String productDescription = params.get("productDescriptionLego");
        BigDecimal productPrice = new BigDecimal(params.get("productPriceLego"));
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

        Session session = HibernateUtil.sessionFactory.openSession();
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
            picture.setStoreItemGeneric(legoSet);
            picture.setPictureCaption(legoSet.getItemName());

            session.beginTransaction();
            session.save(picture);
            legoSet.getItemPictures().add(picture);

            StoreItemPicture smallPicture = new StoreItemPicture();
            byte[] resizedBytes = PicturesLogic.resizePicturesForThumbnailGallery(bytes);
            if (resizedBytes != null)
            {
                smallPicture.setImage(resizedBytes);
                smallPicture.setStoreItemGeneric(legoSet);
                smallPicture.setPictureCaption(legoSet.getItemName() + "_small");
                session.save(smallPicture);
                legoSet.getItemPictures().add(smallPicture);
            }

            session.save(legoSet);
            session.getTransaction().commit();
            session.close();
        }
        HibernateUtil.createItem(legoSet);
    }

    public static void editLegoSet(HttpServletRequest request)
    {
        FileRequest fr = FileUploadUtil.getFileRequest(request);
        Map<String, String> params = fr.getParameters();

        int itemNumber = CommonUtils.getInteger(params.get("itemNumber"));

        LegoSet legoSet = (LegoSet) StoreItemGeneric.getItem(itemNumber);

        if (legoSet != null)
        {
//            String newName = request.getParameter("name_" + legoSet.getItemNumber());
//            String newDescription = request.getParameter("description_" + legoSet.getItemNumber());
//            BigDecimal newPrice = new BigDecimal(request.getParameter("price_" + legoSet.getItemNumber()));
//            String newLegoCode = request.getParameter("legoCode_" + legoSet.getItemNumber());
//            String newTheme = request.getParameter("theme_" + legoSet.getItemNumber());
//            String newPieces = request.getParameter("pieces_" + legoSet.getItemNumber());
//            String newReleaseYear = request.getParameter("releaseYear_" + legoSet.getItemNumber());

            HibernateUtil.updateItem(legoSet);
        }
    }
}
