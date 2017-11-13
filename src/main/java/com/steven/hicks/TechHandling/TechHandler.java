package com.steven.hicks.TechHandling;

import com.steven.hicks.AcademicHandling.AcademicLogic;
import com.steven.hicks.ResultsPage;
import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.entities.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/javascript/javaScript.jsp");
            dispatcher.forward(request, response);
        }

//        ------Charts JS
        if (action.equalsIgnoreCase("charts"))
        {
            List<AcademicCourse> allCourses = AcademicLogic.getCourseList();
            Map<String, BigDecimal> mapOfSemesterAverages = new HashMap<>();

            Map<String, List<AcademicCourse>> mapOfAcademicCoursesBySemester = allCourses.stream()
                    .collect(Collectors.groupingBy(AcademicCourse::getSemester));

            //For line chart
            Iterator it = mapOfAcademicCoursesBySemester.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry pair = (Map.Entry)it.next();
                List<AcademicCourse> courses1 = (List)pair.getValue();

                BigDecimal average = courses1.stream()
                        .map(AcademicCourse::getGradeReceived)
                        .map(grade -> AcademicLogic.getIntFromLetterGrade(grade))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal average2 = average.divide(new BigDecimal(courses1.size()), 2, RoundingMode.HALF_DOWN);
                mapOfSemesterAverages.put((String)pair.getKey(), average2);
            }
            request.setAttribute("averages", mapOfSemesterAverages);

            List<String> listOfSemesters = allCourses.stream()
                    .sorted(Comparator.comparing(AcademicCourse::getSemesterTrackingNumber))
                    .map(AcademicCourse::getSemester)
                    .distinct()
                    .collect(Collectors.toList());

            request.setAttribute("semesters", listOfSemesters);

            //for Pie chart
            //For averages of letters
            Map<String, Long> averageGrades = new HashMap<>();
            averageGrades = allCourses.stream()
                    .map(AcademicCourse::getGradeReceived)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            request.setAttribute("averageGrades", averageGrades);

            List<String> allGradeList = allCourses.stream()
                    .sorted(Comparator.comparing(AcademicCourse::getGradeReceived))
                    .map(AcademicCourse::getGradeReceived)
                    .distinct()
                    .collect(Collectors.toList());
            request.setAttribute("allGrades", allGradeList);


            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/javascript/charts.jsp");
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
            String function = request.getParameter("function");
            if (function != null && function.length() > 0)
            {
                if (function.equals("largeNumMultiThread"))
                {
                    TechLogic.doLargeNumberFunctionMultiThread();

                }
                if (function.equals("largeNum"))
                {
                    int largestPrime = TechLogic.getLargestPrimeNumberUnderAMillion();
                }
//                if (function.equals("largeNumMultiThread"))
//                {
//
//                }

            }


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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/steamRestCall.jsp");
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

//        -----Top Artists
        //:todo make it so that only a certain amount of paginations are visable at a time, and the rest will be...s
        //:todo make the pagination look prettier, and centered in whatever div it's in
        if (action.equalsIgnoreCase("topArtists"))
        {
            String userName = "shicks255";
            String pageNumberString = request.getParameter("pageNumber");
            if (pageNumberString == null || pageNumberString.length() == 0)
                pageNumberString = "1";
            int pageNumber = Integer.parseInt(pageNumberString);

            String selectedOption = request.getParameter("selectedTimeOption");
            if (selectedOption == null || selectedOption.length() == 0)
                selectedOption = "overall";
            request.setAttribute("selectedTimePeriod", selectedOption);

            List<String> timeOptions = Arrays.asList("overall", "7day", "1month", "3month", "6month", "12month");
            request.setAttribute("timeOptions", timeOptions);

            List<TopArtistRecord> artistRecords = TechLogic.searchForTopArtists(request, userName, selectedOption);
            List<ResultsPage> resultPages = CommonUtils.putResultsInPage(artistRecords, 10);
            request.setAttribute("resultPages", resultPages);
            request.setAttribute("selectedPage", resultPages.get(pageNumber-1));
            request.setAttribute("lookupCriteria", "artist");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFmStuff/lastFMCallTopArtistsPopup.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("topAlbums"))
        {
            String userName = "shicks255";
            String pageNumberString = request.getParameter("pageNumber");
            if (pageNumberString == null || pageNumberString.length() == 0)
                pageNumberString = "1";
            int pageNumber = Integer.parseInt(pageNumberString);

            String selectedOption = request.getParameter("selectedTimeOption");
            if (selectedOption == null || selectedOption.length() == 0)
                selectedOption = "overall";
            request.setAttribute("selectedTimePeriod", selectedOption);

            List<String> timeOptions = Arrays.asList("overall", "7day", "1month", "3month", "6month", "12month");
            request.setAttribute("timeOptions", timeOptions);

            List<TopAlbumRecord> albumRecords = TechLogic.searchForTopAlbums(request, userName, selectedOption);
            List<ResultsPage> resultPages = CommonUtils.putResultsInPage(albumRecords, 10);
            request.setAttribute("resultPages", resultPages);
            request.setAttribute("selectedPage", resultPages.get(pageNumber-1));
            request.setAttribute("lookupCriteria", "album");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFmStuff/lastFMCallTopAlbumsPopup.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("topSongs"))
        {
            String userName = "shicks255";
            String pageNumberString = request.getParameter("pageNumber");
            if (pageNumberString == null || pageNumberString.length() == 0)
                pageNumberString = "1";
            int pageNumber = Integer.parseInt(pageNumberString);

            String selectedOption = request.getParameter("selectedTimeOption");
            if (selectedOption == null || selectedOption.length() == 0)
                selectedOption = "overall";
            request.setAttribute("selectedTimePeriod", selectedOption);

            List<String> timeOptions = Arrays.asList("overall", "7day", "1month", "3month", "6month", "12month");
            request.setAttribute("timeOptions", timeOptions);

            List<TopSongRecord> songRecords = TechLogic.searchForTopSongs(request, userName, selectedOption);
            List<ResultsPage> resultPages = CommonUtils.putResultsInPage(songRecords, 10);
            request.setAttribute("resultPages", resultPages);
            request.setAttribute("selectedPage", resultPages.get(pageNumber-1));
            request.setAttribute("lookupCriteria", "song");

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/techPractice/java/lastFmStuff/lastFMCallTopSongsPopup.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
