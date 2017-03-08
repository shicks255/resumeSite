package com.steven.hicks.TechHandling;

import com.steven.hicks.Utils;
import com.steven.hicks.entities.Album;
import com.steven.hicks.entities.MusicArtist;
import com.steven.hicks.entities.SteamGame;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

//        -----SESSION PRACTICE REDIRECT
        if (action.equalsIgnoreCase("sessionPractice"))
        {
            String invalidate = request.getParameter("invalidate");
            HttpSession session = request.getSession();
            if (invalidate.equalsIgnoreCase("true"))
            {
                session.invalidate();
                invalidate = "false";
            }

            int numberOfTimesPageAccessedSoFar = TechLogic.getSessionAccessAcount(session, request);
            request.setAttribute("accessCount", numberOfTimesPageAccessedSoFar + "");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/sessionPractice.jsp");
            dispatcher.forward(request, response);
//            response.sendRedirect("/techPractice?action=sessionPractice&invalidate=" + invalidate);
        }

//        -----DO STEAM API
        if (action.equalsIgnoreCase("steamApi"))
        {
            List<SteamGame> allGameList = TechLogic.doSteamApiCall(request);

            request.setAttribute("allGameList", allGameList);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/restfulCall.jsp");
            dispatcher.forward(request, response);
        }

//        -----Do last.fm call
        if (action.equalsIgnoreCase("getMusicArtistsFromLast_FM"))
        {
            List<MusicArtist> musicArtists = TechLogic.getMusicArtistsFromLast_FM(request);

            request.setAttribute("musicArtist", musicArtists);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/lastFMCall.jsp");
            dispatcher.forward(request, response);
        }

//        -----Last FM artist search
        if (action.equalsIgnoreCase("artistSearch"))
        {
            String artistSearchName = request.getParameter("artistSearchField");

            request.setAttribute("musicArtist", TechLogic.searchForArtists(request, artistSearchName));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/lastFMCall.jsp");
            dispatcher.forward(request, response);
        }

//        -----Album search
        if (action.equalsIgnoreCase("albumSearch"))
        {
            String albumSearchName = request.getParameter("albumSearchName");

            List<Album> albums = TechLogic.searchForAlbums(request, albumSearchName);
            request.setAttribute("albums", albums);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/lastFMCallAlbum.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
