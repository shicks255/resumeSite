package com.steven.hicks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven on 10/5/2016.
 */
@WebServlet(urlPatterns = "/control")
public final class Controller extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = "/controltab1";
        boolean startParameters = true;

        Map<String, String[]> parameterMap = request.getParameterMap();

        Iterator it = parameterMap.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            String[] values = (String[])pair.getValue();
            System.out.println(pair.getKey() + " , " + values[0]);

            if (startParameters)
            {
                url += "?";
                startParameters = false;
            }

            url += (String)pair.getKey() + "=" + values[0];
            if (it.hasNext())
                url += "&";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
