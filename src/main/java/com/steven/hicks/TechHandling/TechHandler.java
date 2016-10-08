package com.steven.hicks.TechHandling;

import com.steven.hicks.entities.SteamGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

//        -----PAGE LOAD
        if (action.equalsIgnoreCase("form"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice.jsp");
            dispatcher.forward(request, response);
        }

//        -----CSS REDIRECT
        if (action.equalsIgnoreCase("css"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/css.jsp");
            dispatcher.forward(request, response);
        }

//        -----JAVASCRIPT REDIRECT
        if (action.equalsIgnoreCase("javaScript"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/javaScript.jsp");
            dispatcher.forward(request, response);
        }

//        -----JAVA REDIRECT
        if (action.equalsIgnoreCase("java"))
        {
            HttpSession session = request.getSession();
            request.setAttribute("session", session);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("sessionPractice"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/sessionPractice.jsp");
            dispatcher.forward(request, response);
        }

//        -----DO STEAM API
        if (action.equalsIgnoreCase("steamApi"))
        {
            List<SteamGame> allGameList = (ArrayList<SteamGame>) TechLogic.doSteamApiCall(request);

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
