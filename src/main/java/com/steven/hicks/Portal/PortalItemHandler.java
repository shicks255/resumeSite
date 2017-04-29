package com.steven.hicks.Portal;


import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.Utilities.StoreItemHelpers;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.StoreItems.MusicAlbum;
import com.steven.hicks.entities.StoreItems.StoreItemType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/portalItemHandler")
public class PortalItemHandler extends HttpServlet
{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {
            List<StoreItemType> itemTypes = StoreItemType.getItemTypes();
            request.setAttribute("itemTypes", itemTypes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/portal/portalAddAnItem.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("editItems"))
        {
            List<StoreItemType> itemTypes = StoreItemType.getItemTypes();
            request.setAttribute("itemTypes", itemTypes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/portal/portalEditItems.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("addMusicAlbum"))
        {
            String productDescription = request.getParameter("productDescription");
            String productPrice = request.getParameter("productPrice");
            String artistName = request.getParameter("artistName");
            String albumTitle = request.getParameter("albumTitle");
            String releaseYear = request.getParameter("releaseYear");

            MusicAlbum musicAlbum = new MusicAlbum();
            musicAlbum.setItemDescription(productDescription);
            musicAlbum.setItemPrice(productPrice);
            musicAlbum.setArtist(artistName);
            musicAlbum.setAlbumTitle(albumTitle);
            musicAlbum.setReleaseYear(releaseYear);
            musicAlbum.setItemName(artistName + " - " + albumTitle);
            musicAlbum.setItemType(StoreItemType.getItemTypeByName("MusicAlbum").getItemTypeCode());

            HibernateUtil.createItem(musicAlbum);

            response.sendRedirect("portalItemHandler?action=form");
        }

        if (action.equalsIgnoreCase("editMusicAlbums"))
        {
            List<StoreItemGeneric> musicAlbums = StoreItemGeneric.getItemsOfType("MusicAlbum");

            List<MusicAlbum> albums = StoreItemGeneric.getItemsOfType("MusicAlbum");

            for (MusicAlbum album : albums)
            {
                String newName = request.getParameter("name_" + album.getItemNumber());
                String newDescription = request.getParameter("description_" + album.getItemNumber());
                String newPrice = request.getParameter("price_" + album.getItemNumber());
                String newArtistName = request.getParameter("artist_" + album.getItemNumber());
                String newAlbumTitle = request.getParameter("albumTitle_" + album.getItemNumber());
                String newReleaseYear = request.getParameter("releaseYear_" + album.getItemNumber());

                if (newName.length() > 0)
                    album.setItemName(newName);
                if (newDescription.length() > 0)
                    album.setItemDescription(newDescription);
                if (newPrice.length() > 0)
                    album.setItemPrice(newPrice);
                if (newArtistName.length() > 0)
                    album.setArtist(newArtistName);
                if (newAlbumTitle.length() > 0)
                    album.setAlbumTitle(newAlbumTitle);
                if (newReleaseYear.length() > 0)
                    album.setReleaseYear(newReleaseYear);

                HibernateUtil.updateItem(album);
            }

            response.sendRedirect("portalItemHandler?action=editItems");
        }

        if (action.equalsIgnoreCase("ajaxGetItems"))
        {
            String itemType = request.getParameter("itemType");
            StoreItemType storeItemType = StoreItemType.getItemTypeByName(itemType);

            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();

            Query query = session.createQuery("from StoreItemGeneric ");
            List<StoreItemGeneric> list = query.list();

            session.close();
            factory.close();

            list.removeIf(item -> item.getItemType() != storeItemType.getItemTypeCode());
            request.setAttribute("items", list);

            RequestDispatcher dispatcher;

            if (itemType.equalsIgnoreCase("MusicAlbum"))
            {
                dispatcher = request.getRequestDispatcher("portal/items/musicAlbumTable.jsp");
                dispatcher.forward(request, response);
            }

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }

}
