package com.steven.hicks;

import com.steven.hicks.entities.SteamGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if (action.equalsIgnoreCase("steamApi"))
        {
            List<SteamGame> allGameList = (ArrayList<SteamGame>)TechLogic.doSteamApiCall();

            Collections.sort(allGameList, new Comparator<SteamGame>()
                    {
                        @Override
                        public int compare(SteamGame o1, SteamGame o2)
                        {
                            int returnValue = 0;
                            if (o1.getAppId() > o2.getAppId())
                                returnValue = 1;
                            if (o1.getAppId() < o2.getAppId())
                                returnValue = -1;

                            return returnValue;
                        }
                    });

            String sortType = request.getParameter("sort");

            if (sortType.equalsIgnoreCase("reverseId"))
            {
                Collections.sort(allGameList, new Comparator<SteamGame>()
                {
                    @Override
                    public int compare(SteamGame o1, SteamGame o2)
                    {
                        int returnValue = 0;
                        if (o1.getAppId() > o2.getAppId())
                            returnValue = -1;
                        if (o1.getAppId() < o2.getAppId())
                            returnValue = 1;

                        return returnValue;
                    }

                });
            }

            if (sortType.equalsIgnoreCase("alpha"))
            {
                allGameList = allGameList.stream()
                        .sorted( (o1, o2) -> o1.getName().compareTo(o2.getName()))
                        .collect(Collectors.toList());
            }

            if (sortType.equalsIgnoreCase("reverseAlpha"))
            {
                allGameList = allGameList.stream()
                        .sorted( (o1, o2) -> -o1.getName().compareTo(o2.getName()))
                        .collect(Collectors.toList());
            }


            request.setAttribute("allGameList", allGameList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/restfulCall.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
