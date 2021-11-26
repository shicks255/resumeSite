package com.steven.hicks.Portal;

import com.steven.hicks.entities.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet (urlPatterns = "/userRegistration")
public class PortalUserRegistrationHandler extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {

            String userName = request.getParameter("newUserName");
            String password = request.getParameter("newPassword");
            String password2 = request.getParameter("newPassword2");
            String email    = request.getParameter("newEmail");
            String firstName = request.getParameter("newFirstName");
            String lastName  = request.getParameter("newLastName");

            User.createUser(userName, password, email, firstName, lastName, "user");

            response.sendRedirect("portal?action=form");
        }

        if (action.equalsIgnoreCase("userNameAlreadyUsed"))
        {
            String userNameEntered = request.getParameter("userNameEntered");

            User aUser = User.getUser(userNameEntered);

            String userExists = "false";

            if (aUser != null)
                userExists = "true";

            response.setContentType("text/plain");
            response.getWriter().write(userExists);
        }

        if (action.equalsIgnoreCase("addAdminUser"))
        {

            String userName = request.getParameter("adminUserName");
            String password = request.getParameter("adminPassword");
            String email    = request.getParameter("adminEmail");
            String firstName = request.getParameter("adminFirstName");
            String lastName = request.getParameter("adminLastName");

            User.createUser(userName, password, email, firstName, lastName, "admin");

            response.sendRedirect("portal?action=form");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }
}
