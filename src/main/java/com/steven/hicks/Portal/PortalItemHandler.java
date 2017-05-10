package com.steven.hicks.Portal;


import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.store.Cart;
import com.steven.hicks.entities.store.CartItem;
import com.steven.hicks.entities.store.items.LegoSet;
import com.steven.hicks.entities.store.items.MusicAlbum;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.StoreItemType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
           PortalItemLogic.addMusicAlbum(request);
           response.sendRedirect("portalItemHandler?action=form");
        }

        if (action.equalsIgnoreCase("addLegoSet"))
        {
            PortalItemLogic.addLegoSet(request);
            response.sendRedirect("portalItemHandler?action=form");
        }

        if (action.equalsIgnoreCase("editMusicAlbums"))
        {
            List<StoreItemGeneric> musicAlbums = StoreItemGeneric.getItemsOfType("MusicAlbum");
            StoreItemType itemType = StoreItemType.getItemTypeByName("MusicAlbum");

            List<MusicAlbum> albums = musicAlbums.stream()
                    .filter(item -> item.getItemType() == itemType.getItemTypeCode())
                    .map(item -> (MusicAlbum)item)
                    .collect(Collectors.toList());

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

        if (action.equalsIgnoreCase("editLegoSet"))
        {
            List<StoreItemGeneric> genericItems = StoreItemGeneric.getItemsOfType("LegoSet");
            StoreItemType itemType = StoreItemType.getItemTypeByName("LegoSet");

            List<LegoSet> legoSets = genericItems.stream()
                    .filter(item -> item.getItemType() == itemType.getItemTypeCode())
                    .map(item -> (LegoSet)item)
                    .collect(Collectors.toList());

            for (LegoSet legoSet : legoSets)
            {
                String newName = request.getParameter("name_" + legoSet.getItemNumber());
                String newDescription = request.getParameter("description_" + legoSet.getItemNumber());
                String newPrice = request.getParameter("price_" + legoSet.getItemNumber());
                String newLegoCode = request.getParameter("legoCode_" + legoSet.getItemNumber());
                String newTheme = request.getParameter("theme_" + legoSet.getItemNumber());
                String newPieces = request.getParameter("pieces_" + legoSet.getItemNumber());
                String newReleaseYear = request.getParameter("releaseYear_" + legoSet.getItemNumber());

                if (newName.length() > 0)
                    legoSet.setItemName(newName);
                if (newDescription.length() > 0)
                    legoSet.setItemDescription(newDescription);
                if (newPrice.length() > 0)
                    legoSet.setItemPrice(newPrice);
                if (newLegoCode.length() > 0)
                    legoSet.setLegoCode(newLegoCode);
                if (newTheme.length() > 0)
                    legoSet.setLegoTheme(newTheme);
                if (newPieces.length() > 0)
                    legoSet.setNumberOfPieces(Integer.valueOf(newPieces));
                if (newReleaseYear.length() > 0)
                    legoSet.setReleaseYear(newReleaseYear);

                HibernateUtil.updateItem(legoSet);
            }

            response.sendRedirect("portalItemHandler?action=editItems");
        }

        if (action.equalsIgnoreCase("getItemPicture"))
        {
            Integer itemPictureObjectId = Integer.valueOf(request.getParameter("itemPictureObjectId"));
            StoreItemPicture picture = StoreItemPicture.getItemPicture(itemPictureObjectId);

            response.setContentType("image/jpg");
            response.setContentLengthLong(picture.getImage().length);

            response.getOutputStream().write(picture.getImage());
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
            if (itemType.equalsIgnoreCase("LegoSet"))
            {
                dispatcher = request.getRequestDispatcher("portal/items/legoSetTable.jsp");
                dispatcher.forward(request, response);
            }

        }

        if (action.equalsIgnoreCase("ajaxGetSearchResults"))
        {
            String searchInput = request.getParameter("searchInput").toLowerCase();
            String[] searchTerms = searchInput.split("\\s+");

            List<StoreItemGeneric> items = StoreItemGeneric.getAllItems();
            items.removeIf(item ->
            {
                for (String searchTerm : searchTerms)
                {
                    if (!item.getItemName().toLowerCase().contains(searchTerm.toLowerCase()))
                        return true;
                }
                return false;
            });

            int size = items.size();
            if (size > 10)
                items.subList(10, size).clear();

            request.setAttribute("items", items);
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/searchResultsInjected.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("showItemPage"))
        {
//            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));
            String itemName = request.getParameter("itemName");
            StoreItemGeneric item = StoreItemGeneric.getItemByName(itemName);

            if (item != null)
            {
                StoreItemType type = StoreItemType.getItemType(item.getItemType());

                if (type != null)
                {
                    if (type.getItemTypeCode() == 113)
                    {
                        MusicAlbum album = (MusicAlbum)item;

                        request.setAttribute("album", album);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/itemPages/musicAlbum.jsp");
                        dispatcher.forward(request, response);

                    }
                    if (type.getItemTypeCode() == 114)
                    {
                        LegoSet legoset = (LegoSet)item;
                        request.setAttribute("legoSet", legoset);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/itemPages/legoSet.jsp");
                        dispatcher.forward(request, response);
                    }
                }

            }
        }

        if (action.equalsIgnoreCase("searchForItems"))
        {
            String searchTerms = request.getParameter("searchTerms");
            List<StoreItemGeneric> itemSearchResults = StoreItemGeneric.searchForItems(searchTerms);

            request.setAttribute("items", itemSearchResults);


            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/searchResults.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("addItemToCart"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));
            StoreItemGeneric item = StoreItemGeneric.getItem(itemNumber);

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");

            Cart userCart = user.getUserCart();

            List<CartItem> cartItems = userCart.getItemsInCart();


            CartItem cartItem;

            if (userCart.getItemFromCart(itemNumber) != null)
            {
                cartItem = userCart.getItemFromCart(itemNumber);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                HibernateUtil.updateItem(cartItem);
            }
            else
            {
                cartItem = new CartItem();
                cartItem.setCartObjectId(userCart.getObjectId());
                cartItem.setItemObjectIt(item.getItemNumber());
                cartItem.setQuantity(1);
                HibernateUtil.createItem(cartItem);
            }
        }

        if (action.equalsIgnoreCase("updateCartQty"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));
            int quantity = Integer.valueOf(request.getParameter("newQuantity"));

            CartItem cartItem = CartItem.getCartItem(itemNumber);
            cartItem.setQuantity(quantity);
            HibernateUtil.updateItem(cartItem);
            response.sendRedirect("portal?action=portalCart");
        }

        if (action.equalsIgnoreCase("removeItemFromCart"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));

            CartItem cartItem = CartItem.getCartItem(itemNumber);
            HibernateUtil.deleteItem(cartItem);

            response.sendRedirect("portal?action=portalCart");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }

}
