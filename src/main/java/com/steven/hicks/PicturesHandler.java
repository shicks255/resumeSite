package com.steven.hicks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Steven on 7/17/2016.
 */

@WebServlet(urlPatterns = "/pictures")
public class PicturesHandler extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {

            ServletContext context = getServletContext();
//            URL imagesPath = context.getResource("/src/main/webapp/images/");

            InputStream resourceContent = context.getResourceAsStream("/src/main/webapp/images/");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pictures.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

}
