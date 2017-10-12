package com.steven.hicks.Portal;


import com.steven.hicks.Utilities.FileUploadUtil;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.FileRequest;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.UserAvatar;
import com.steven.hicks.entities.store.Cart;
import com.steven.hicks.entities.store.CartItem;
import com.steven.hicks.entities.store.StoreItemPicture;
import com.steven.hicks.entities.store.ordering.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet (urlPatterns = "/portal")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"user", "admin"} ))
public class PortalHandler extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String action = request.getParameter("action");

//        -----Home Page
        if (action.equalsIgnoreCase("form"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/portalHome.jsp");
            dispatcher.forward(request, response);
        }

//        -----Edit Profile
        if (action.equalsIgnoreCase("editProfile"))
        {
            String editFirstName = request.getParameter("editFirstName");
            String editLastName = request.getParameter("editLastName");
            String editEmailAddress = request.getParameter("editEmail");

            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");

            user.setFirstName(editFirstName);
            user.setLastName(editLastName);
            user.setEmailAddress(editEmailAddress);

            HibernateUtil.updateItem(user);

            response.sendRedirect("portal?action=form");
        }

//        -----Edit Avatar
        if (action.equalsIgnoreCase("editAvatar"))
        {
            HttpSession userSession = request.getSession();
            User user = (User)userSession.getAttribute("user");

            UserAvatar avatar = user.getAvatar();
            if (avatar != null)
                HibernateUtil.deleteItem(avatar);

            user.setAvatar(null);
            HibernateUtil.updateItem(user);

            FileRequest fr = FileUploadUtil.getFileRequest(request);
            File file = fr.getUploadedFile();

            PortalLogic.saveAvater(file, fr, user);

            response.sendRedirect("portal?action=form");
        }

//        -----Sign Out
        if (action.equalsIgnoreCase("signOut"))
        {
            HttpSession userSession = request.getSession();

            userSession.invalidate();

            response.sendRedirect(getServletContext().getContextPath() + "/techPractice?action=form");
        }

//        -----Order Reviews
        if (action.equalsIgnoreCase("reviews"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/portalReviews.jsp");
            dispatcher.forward(request, response);
        }

//        -----Order History
        if (action.equalsIgnoreCase("orderHistory"))
        {

            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/portalOrderHistory.jsp");
            dispatcher.forward(request, response);
        }

//        -----My Cart
        if (action.equalsIgnoreCase("portalCart"))
        {
            HttpSession userSession = request.getSession();

            User user = (User)userSession.getAttribute("user");
            Cart userCart = user.getUserCart();
            List<CartItem> cartItems = userCart.getItemsInCart();

            List<StoreItemPicture> itemPictures = new ArrayList<>();
            List<StoreItemGeneric> storeItems = new ArrayList<>();

            Map<StoreItemGeneric, CartItem> itemsToCartItems = new HashMap<>();
            Map<StoreItemGeneric, StoreItemPicture> itemsToPicture = new HashMap<>();

            Session session = HibernateUtil.sessionFactory.openSession();

            for (CartItem cartItem : cartItems)
            {
                StoreItemGeneric item = session.get(StoreItemGeneric.class, cartItem.getItemObjectIt());
                Hibernate.initialize(item.getItemPictures());
                storeItems.add(item);

                StoreItemPicture picture = session.get(StoreItemPicture.class, item.getFirstPictureId());
                itemPictures.add(picture);

                itemsToCartItems.put(item, cartItem);

                itemsToPicture.put(item, picture);
            }

            session.close();

            request.setAttribute("storeItems", storeItems);
            request.setAttribute("itemPictures", itemPictures);
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cart", userCart);

            request.setAttribute("map", itemsToCartItems);
            request.setAttribute("itemsToPicture", itemsToPicture);

            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/portalCart.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("getNumberOfItemsInCart"))
        {
            String userName = request.getParameter("userName");

            Cart cart = Cart.getCartByUser(userName);
            response.getWriter().println(cart.getItemsInCart().size());
        }

        if (action.equalsIgnoreCase("checkout"))
        {
            HttpSession userSession = request.getSession();

            User user = (User)userSession.getAttribute("user");
            Cart userCart = user.getUserCart();

            request.setAttribute("cart", userCart);
            request.setAttribute("creditCards", Arrays.asList(CreditCardTypes.values()));

            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/checkout.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("orderCheckout"))
        {
            HttpSession userSession = request.getSession();

            User user = (User)userSession.getAttribute("user");
            Cart userCart = user.getUserCart();

            Session session = HibernateUtil.sessionFactory.openSession();

            StoreOrder order = new StoreOrder(user.getUserName());
            session.save(order);

            List<OrderedItem> orderedItems = new ArrayList<>();

            for (CartItem item : userCart.getItemsInCart())
            {
                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setItemNumber(item.getStoreItem().getItemNumber());
                orderedItem.setQuantity(item.getQuantity());
                orderedItem.setOrder(order);

                orderedItems.add(orderedItem);
            }

            order.setItemsFromOrder(orderedItems);

            OrderPaymentBehavior paymentBehavior = PaymentMethodFactory.buildAndReturnPaymentMethod(request);
            order.setOrderPaymentBehavior(paymentBehavior);

//            session.delete(order);
//            session.close();

//            response.sendRedirect(getServletContext().getContextPath() + "/portal/portalCart");
//            response.sendRedirect(getServletContext().getContextPath() + "/portal/orderCheckout");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }

}
