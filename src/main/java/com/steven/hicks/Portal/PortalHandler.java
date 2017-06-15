package com.steven.hicks.Portal;


import com.steven.hicks.Utilities.FileUploadUtil;
import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.FileRequest;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.UserAvatar;
import com.steven.hicks.entities.store.Cart;

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
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//            Principal principal = request.getUserPrincipal();
//            User user = User.getUser(principal.getName());

//            HttpSession session = request.getSession();
//            session.setAttribute("user", user);

//            List<StoreItemGeneric> allItems = StoreItemGeneric.getAllItems();
//            session.setAttribute("allItems", allItems);

//            Cart cart = Cart.getCartByUser(user.getUserName());
//            if (cart == null)
//            {
//                cart = new Cart();
//                cart.setUserNameOfCart(user.getUserName());
//                HibernateUtil.createItem(cart);
//            }
//            session.setAttribute("cart", cart);

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
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/portalCart.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }

}
