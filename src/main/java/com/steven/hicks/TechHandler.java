package com.steven.hicks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Steven on 7/17/2016.
 */

@WebServlet(urlPatterns = "/techPractice")
public class TechHandler extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("css"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/css.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("javaScript"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/javaScript.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("java"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
