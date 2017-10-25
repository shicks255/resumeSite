package com.steven.hicks.Utilities;

import com.steven.hicks.ResultsPage;
import com.steven.hicks.ServerStart;
import com.steven.hicks.entities.Visitor;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public final class CommonUtils
{
    private static final Logger log = Logger.getLogger(ServerStart.class.getName());

    public static boolean isAdminVisitor(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if (session != null)
        {
            String visitorIpAddress = request.getRemoteHost();
            if (visitorIpAddress != null && visitorIpAddress.length() > 0)
            {
                if (visitorIpAddress.equals("67.87.211.190") ||
                        visitorIpAddress.equals("127.0.0.1") ||
                        visitorIpAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
                    return true;
            }
        }

        return false;
    }

    public static void increaseVisitorCountIfNewSession(HttpServletRequest request)
    {
        String visitorIPAddress = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null)
            visitorIPAddress = request.getHeader("x-forwarded-for");

        if (request.getSession(false) == null)
        {
            Visitor visitor;
            if (Visitor.getVisitor(visitorIPAddress) != null)
            {
                visitor = Visitor.getVisitor(visitorIPAddress);
                visitor.setCountOfVisits(visitor.getCountOfVisits() + 1);

                HibernateUtil.updateItem(visitor);
                log.info("Visitor returning " + visitor.toString());
            } else
            {
                visitor = new Visitor();
                visitor.setIpAddress(visitorIPAddress);
                visitor.setCountOfVisits(1);

                HibernateUtil.createItem(visitor);
                log.info("New visited logged " + visitor.toString());
            }
        }
    }

    public static void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }

    public static String getMimeType(String filename)
    {
        if (filename == null || filename.length() < 4) return "";
        filename = filename.toLowerCase();
        String mimetype = "";

        if (filename.indexOf('.') >= 0)
        {
            String extension = filename.substring(filename.toLowerCase().lastIndexOf('.') + 1);

            if (extension.equals("ics")) mimetype = "text/calendar";
            if (extension.equals("ppt")) mimetype = "application/ms-powerpoint";
            if (extension.equals("pptx"))
                mimetype = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            if (extension.equals("doc")) mimetype = "application/msword";
            if (extension.equals("rtf")) mimetype = "application/msword";
            if (extension.equals("docx"))
                mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            if (extension.equals("dotx"))
                mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
            if (extension.equals("xls")) mimetype = "application/vnd.ms-excel";
            if (extension.equals("xlsx"))
                mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            if (extension.equals("xltx"))
                mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
            if (extension.equals("pdf")) mimetype = "application/pdf";
            if (extension.equals("jpg")) mimetype = "image/jpeg";
            if (extension.equals("gif")) mimetype = "image/gif";
            if (extension.equals("txt")) mimetype = "text/plain";
            if (extension.equals("log")) mimetype = "text/plain";
            if (extension.equals("bz2")) mimetype = "application/bzip2";
            if (extension.equals("gz")) mimetype = "application/gzip";
            if (extension.equals("csv")) mimetype = "text/csv";
            if (extension.equals("flipchart")) mimetype = "application/Inspire flipchart";
            if (extension.equals("png")) mimetype = "image/png";
            if (extension.equals("crx")) mimetype = "application/unknown";
            if (extension.equals("ttf")) mimetype = "application/octet-stream";
            if (extension.equals("otf")) mimetype = "application/octet-stream";
            if (extension.equals("xml")) mimetype = "application/xml";
            if (extension.equals("mp4")) mimetype = "video/mp4";
            if (extension.equals("mov")) mimetype = "video/quicktime";
        }
        return mimetype;
    }

    //:todo rename this function
    public static List<ResultsPage> putResultsInPage(List <?> results, Integer resultsPerPage)
    {
        if (resultsPerPage == null || resultsPerPage < 3)
            resultsPerPage = 10;

        List<ResultsPage> resultPages = new ArrayList<>();

        int counter = 1;

        ResultsPage resultsPage = new ResultsPage();
        resultsPage.setPageNumber(counter);

        for (Object result : results)
        {
            if (resultsPage.getResults().size() == resultsPerPage)
            {
                resultPages.add(resultsPage);
                resultsPage = new ResultsPage();
                resultsPage.setResultsPerPage(resultsPerPage);
                resultsPage.setPageNumber(resultPages.size()+1);
            }

            if (counter == results.size())
                resultsPage.setLastPage(true);

            resultsPage.getResults().add(result);
            counter++;
        }

        return resultPages;
    }
}