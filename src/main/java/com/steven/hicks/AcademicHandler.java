package com.steven.hicks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Steven on 6/18/2016.
 */


@WebServlet(urlPatterns = "/academic")
public class AcademicHandler extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("addACourse"))
        {
            String courseName = request.getParameter("courseName");
        }

        if (action.equalsIgnoreCase("thesis"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/thesis.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("bibliography"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/bibliography.jsp");
            dispatcher.forward(request, response);

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
