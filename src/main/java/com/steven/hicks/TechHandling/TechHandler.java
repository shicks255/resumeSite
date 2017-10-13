package com.steven.hicks.TechHandling;

import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.entities.Album;
import com.steven.hicks.entities.MusicArtist;
import com.steven.hicks.entities.SteamGame;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/techPractice")
public class TechHandler extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/java.jsp");
            dispatcher.forward(request, response);
        }

//        -----SESSION PRACTICE REDIRECT
        if (action.equalsIgnoreCase("sessionPracticePage"))
        {
            HttpSession session = request.getSession();

            int numberOfTimesPageAccessedSoFar = TechLogic.getSessionAccessAcount(session, request);
            request.setAttribute("accessCount", numberOfTimesPageAccessedSoFar + "");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/sessionPractice.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("sessionInvalidate"))
        {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/techPractice?action=sessionPracticePage");
        }

        if (action.equalsIgnoreCase("multithreadingPage"))
        {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/multithreading.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("designPatternsPage"))
        {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/designPatterns.jsp");
            dispatcher.forward(request, response);
        }

//        -----RESTFUL SERVICES PAGE
        if (action.equalsIgnoreCase("restfulServicesPage"))
        {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/restfulServicesPractice.jsp");
            dispatcher.forward(request, response);
        }

//        -----DO STEAM API
        if (action.equalsIgnoreCase("steamApi"))
        {
            List<SteamGame> allGameList = TechLogic.doSteamApiCall(request);

            request.setAttribute("allGameList", allGameList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/restfulCall.jsp");
            dispatcher.forward(request, response);
        }

//        -----Do last.fm call
        if (action.equalsIgnoreCase("getMusicArtistsFromLast_FM"))
        {
            List<MusicArtist> musicArtists = TechLogic.getMusicArtistsFromLast_FM(request);

            request.setAttribute("musicArtist", musicArtists);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFMCallArtistSearchPopup.jsp");
            dispatcher.forward(request, response);
        }

//        -----Last FM artist search
        if (action.equalsIgnoreCase("artistSearch"))
        {
            String artistSearchName = request.getParameter("artistSearchField");

            if (artistSearchName.length() == 0)
            {
                CommonUtils.setInfoMessage(request, "No search terms were entered", "");
            }

            if (artistSearchName.length() > 0)
            {
                List<MusicArtist> musicArtists = Collections.emptyList();
                try
                {
                    musicArtists = TechLogic.searchForArtists(request, artistSearchName);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                request.setAttribute("musicArtist", musicArtists);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFMCallArtistSearchPopup.jsp");
                dispatcher.forward(request, response);
            }
        }

//        -----Last FM Album search
        if (action.equalsIgnoreCase("albumSearch"))
        {
            String albumSearchName = request.getParameter("albumSearchName");

            List<Album> albums = null;
            try
            {
                albums = TechLogic.searchForAlbums(request, albumSearchName);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            request.setAttribute("albums", albums);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFMCallAlbumSearchPopup.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
