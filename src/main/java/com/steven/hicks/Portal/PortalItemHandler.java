package com.steven.hicks.Portal;


import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.store.Cart;
import com.steven.hicks.entities.store.CartItem;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.StoreItemType;
import com.steven.hicks.entities.store.items.LegoSet;
import com.steven.hicks.entities.store.items.MusicAlbum;
import com.steven.hicks.filters.LogFilter;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/portalItemHandler")
public class PortalItemHandler extends HttpServlet
{
    private static final Logger log = Logger.getLogger(LogFilter.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String action = request.getParameter("action");

//        NAVIGATE TO ADD AN ITEM
        if (action.equalsIgnoreCase("form"))
        {
            List<StoreItemType> itemTypes = StoreItemType.getItemTypes();
            request.setAttribute("itemTypes", itemTypes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/portal/portalAddAnItem.jsp");
            dispatcher.forward(request, response);
        }

//        NAVIGATE TO EDIT AN ITEM
        if (action.equalsIgnoreCase("editItems"))
        {
            List<StoreItemType> itemTypes = StoreItemType.getItemTypes();
            request.setAttribute("itemTypes", itemTypes);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/portal/portalEditItems.jsp");
            dispatcher.forward(request, response);
        }

//        ADD MUSIC ALBUM
        if (action.equalsIgnoreCase("addMusicAlbum"))
        {
           PortalItemLogic.addMusicAlbum(request);
           response.sendRedirect("portalItemHandler?action=form");
        }

//        ADD LEGO SET
        if (action.equalsIgnoreCase("addLegoSet"))
        {
            PortalItemLogic.addLegoSet(request);
            response.sendRedirect("portalItemHandler?action=form");
        }

//        EDIT MUSIC ALBUM
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
                BigDecimal newPrice = new BigDecimal(request.getParameter("price_" + album.getItemNumber()));
                String newArtistName = request.getParameter("artist_" + album.getItemNumber());
                String newAlbumTitle = request.getParameter("albumTitle_" + album.getItemNumber());
                String newReleaseYear = request.getParameter("releaseYear_" + album.getItemNumber());

                if (newName.length() > 0)
                    album.setItemName(newName);
                if (newDescription.length() > 0)
                    album.setItemDescription(newDescription);
                if (newPrice != null)
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

//        EDIT LEGO SET
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
                BigDecimal newPrice = new BigDecimal(request.getParameter("price_" + legoSet.getItemNumber()));
                String newLegoCode = request.getParameter("legoCode_" + legoSet.getItemNumber());
                String newTheme = request.getParameter("theme_" + legoSet.getItemNumber());
                String newPieces = request.getParameter("pieces_" + legoSet.getItemNumber());
                String newReleaseYear = request.getParameter("releaseYear_" + legoSet.getItemNumber());

                if (newName.length() > 0)
                    legoSet.setItemName(newName);
                if (newDescription.length() > 0)
                    legoSet.setItemDescription(newDescription);
                if (newPrice != null)
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

//        GET AN ITEMS PICTURE
        if (action.equalsIgnoreCase("getItemPicture"))
        {
            Integer pictureObjectId = Integer.valueOf(request.getParameter("itemPictureObjectId"));

            Session session = HibernateUtil.sessionFactory.openSession();

            StoreItemPicture picture = session.get(StoreItemPicture.class, pictureObjectId);

            if (picture != null)
            {
                response.setContentType("image/png");
                response.setContentLengthLong(picture.getImage().length);
                response.addHeader("Content-Disposition", "inline");
                response.getOutputStream().write(picture.getImage());
                response.getOutputStream().close();
            }

            session.close();
        }

//        GET ITEMS OF A TYPE
        if (action.equalsIgnoreCase("ajaxGetItemsByType"))
        {
            String itemType = request.getParameter("itemType");
            StoreItemType storeItemType = StoreItemType.getItemTypeByName(itemType);

            Session session = HibernateUtil.sessionFactory.openSession();

            Query query = session.createQuery("from StoreItemGeneric ");
            List<StoreItemGeneric> list = query.list();

            //to prevent lazy initialization exceptions
            list.forEach(e -> e.getFirstPictureId());

            session.close();

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

//        NAVIGATE TO SHOW ITEM PAGE
        if (action.equalsIgnoreCase("showItemPage"))
        {
//            String itemName = request.getParameter("itemName");

            Integer itemNumber = Integer.valueOf(request.getParameter("itemNumber"));

            Session session = HibernateUtil.sessionFactory.openSession();

            String queryString = "from StoreItemGeneric where itemNumber=:itemNumber";
            Query query = session.createQuery(queryString).setParameter("itemNumber", itemNumber);
            List<StoreItemGeneric> items = query.list();
            StoreItemGeneric item = items.get(0);

            List<StoreItemPicture> pictures = item.getItemPictures();

            if (item != null)
            {
                StoreItemType type = StoreItemType.getItemType(item.getItemType());

                if (type != null)
                {
                    if (type.getItemTypeCode() == 113)
                    {
                        MusicAlbum album = (MusicAlbum)item;

                        request.setAttribute("album", album);
                        request.setAttribute("picture", pictures.get(0));

                        RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/itemPages/musicAlbum.jsp");
                        dispatcher.forward(request, response);

                    }
                    if (type.getItemTypeCode() == 114)
                    {
                        LegoSet legoset = (LegoSet)item;
                        request.setAttribute("legoSet", legoset);
                        request.setAttribute("picture", pictures.get(0));

                        RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/itemPages/legoSet.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            }

            session.close();
        }

//        NAV BAR ITEM SEARCH
        if (action.equalsIgnoreCase("searchForItems"))
        {
            String searchTerms = request.getParameter("searchTerms");
            List<StoreItemGeneric> itemSearchResults = StoreItemGeneric.searchForItems(searchTerms);
            request.setAttribute("items", itemSearchResults);

            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/items/searchResults.jsp");
            dispatcher.forward(request, response);
        }

//        GET ALL ITEMS
        if (action.equalsIgnoreCase("getAllItemsJSON"))
        {
            List<StoreItemGeneric> allItems = StoreItemGeneric.getAllItems();
            allItems.sort(Comparator.comparing(StoreItemGeneric::getItemName));

            String jsonString = "[";

//            for (StoreItemGeneric item : allItems)
//            {
//                jsonString += "{\"" + item.getItemName() + "\": " + "\"" + item.getFirstPictureId() + "\"}";
//                if (!allItems.get(allItems.size()-1).equals(item))
//                    jsonString += ",";
//            }
            for (StoreItemGeneric item : allItems)
            {
                jsonString += "{\"" + item.getItemName() + "\": " + "\"\"}";
                if (!allItems.get(allItems.size()-1).equals(item))
                    jsonString += ",";

            }
            jsonString += "]";


            PrintWriter out = response.getWriter();
            response.setContentType("text");

            out.println(jsonString);
            out.close();

        }

        if (action.equalsIgnoreCase("ajaxGetJSONItem"))
        {
            Integer itemNumber = CommonUtils.getInteger(request.getParameter("itemNumber"));
            if (itemNumber != null)
            {
                StoreItemGeneric storeItem = StoreItemGeneric.getItemWithPictures(itemNumber);
                if (storeItem != null)
                {
                    try
                    {
                        String jsonString = CommonUtils.getJSONStringForItem(storeItem);

                        PrintWriter writer = response.getWriter();
                        response.setContentType("text");
                        writer.println(jsonString);
                        writer.close();
                    }
                    catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | IntrospectionException e)
                    {
                        System.out.println(e);
                        log.error(e.getMessage());
                    }
                }
            }
        }

//        ADD ITEM TO CART
        if (action.equalsIgnoreCase("addItemToCart"))
        {
            Integer itemNumber = CommonUtils.getInteger(request.getParameter("itemObjectId"));

            Session hibernateSession = HibernateUtil.sessionFactory.openSession();
            hibernateSession.beginTransaction();

            StoreItemGeneric item = hibernateSession.get(StoreItemGeneric.class, itemNumber);

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            Cart cart = null;

            org.hibernate.query.Query query = hibernateSession.createQuery("from Cart where userNameOfCart = \'" + user.getUserName() + "\'");
            List<Cart> carts = query.list();
            if (carts.size() > 0)
                cart = carts.get(0);

            CartItem cartItem;

            if (cart.getItemFromCartByItemNumber(itemNumber) != null)
            {
                cartItem = cart.getItemFromCartByItemNumber(itemNumber);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                hibernateSession.update(cartItem);
//                request.getSession().setAttribute("cart", cartItem.getCart());
            }
            else
            {
                cartItem = new CartItem();
                cartItem.setItemObjectIt(item.getItemNumber());
                cartItem.setQuantity(1);
                cartItem.setCart(cart);
                hibernateSession.save(cartItem);
            }
            hibernateSession.merge(cart);
            hibernateSession.getTransaction().commit();

            hibernateSession.close();
        }

//        UPDATE CART QUANTITY
        if (action.equalsIgnoreCase("updateCartQty"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));
            int quantity = Integer.valueOf(request.getParameter("newQuantity"));

            Session session = HibernateUtil.sessionFactory.openSession();
            session.beginTransaction();

            CartItem cartItem = session.get(CartItem.class, itemNumber);
            cartItem.setQuantity(quantity);

            session.getTransaction().commit();

            session.close();

            response.sendRedirect("portal?action=portalCart");
        }

//        REMOVE ITEM FROM CART
        if (action.equalsIgnoreCase("removeItemFromCart"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemObjectId"));

            Session session = HibernateUtil.sessionFactory.openSession();
            session.beginTransaction();

            CartItem cartItem = session.get(CartItem.class, itemNumber);
            session.delete(cartItem);

            session.getTransaction().commit();

            session.close();

            response.sendRedirect("portal?action=portalCart");
        }

//        DELETE ITEM FROM DATABASE
        if (action.equalsIgnoreCase("deleteItem"))
        {
            int itemNumber = Integer.valueOf(request.getParameter("itemNumber"));

            StoreItemGeneric item = StoreItemGeneric.getItem(itemNumber);
            if (item != null)
            {
                List<StoreItemPicture> itemPictures = item.getItemPictures();
                List<CartItem> cartItems = CartItem.getAllCartItems();
                cartItems.forEach(cartItem ->
                {
                    if (cartItem.getStoreItem().getItemType() == itemNumber)
                        HibernateUtil.deleteItem(cartItem);
                });

                Session session = HibernateUtil.sessionFactory.openSession();
                session.beginTransaction();
//                itemPictures.forEach(itemPicture -> HibernateUtil.deleteItem(itemPicture));
                session.delete(item);
                session.getTransaction().commit();
                session.close();
            }
        }

//        UPDATE TOTAL
        if (action.equalsIgnoreCase("updateCartTotal"))
        {
            User user = (User)request.getSession().getAttribute("user");

            if (user != null)
            {
                Cart userCart = user.getUserCart();

                PrintWriter out = response.getWriter();
                response.setContentType("text");

                out.println(userCart.getTotal());
                out.close();
            }

        }

//        UPDATE SUBTOTAL
        if (action.equalsIgnoreCase("updateCartSubTotal"))
        {
            User user = (User)request.getSession().getAttribute("user");
            if (user != null)
            {
                Cart userCart = user.getUserCart();

                PrintWriter out = response.getWriter();
                response.setContentType("text");

                out.println(userCart.getSubTotal());
                out.close();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }

}
