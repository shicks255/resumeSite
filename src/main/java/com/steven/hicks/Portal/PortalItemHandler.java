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

            for (StoreItemGeneric musicAlbum : musicAlbums)
            {

                String newName = request.getParameter("name_" + musicAlbum.getItemNumber());
                String newDescription = request.getParameter("description_" + musicAlbum.getItemNumber());
                String newPrice = request.getParameter("price_" + musicAlbum.getItemNumber());
                String newArtistName = request.getParameter("artist_" + musicAlbum.getItemNumber());
                String newAlbumTitle = request.getParameter("albumTitle_" + musicAlbum.getItemNumber());
                String newReleaseYear = request.getParameter("releaseYear_" + musicAlbum.getItemNumber());

                if (newName.length() > 0)
                    musicAlbum.setItemName(newName);
                if (newDescription.length() > 0)
                    musicAlbum.setItemDescription(newDescription);
                if (newPrice.length() > 0)
                    musicAlbum.setItemPrice(newPrice);
                if (newArtistName.length() > 0)
//                    musicAlbum.set(newDescription);
                if (newAlbumTitle.length() > 0)
                    musicAlbum.setItemDescription(newDescription);
                if (newReleaseYear.length() > 0)
                    musicAlbum.setItemDescription(newDescription);


            }
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
