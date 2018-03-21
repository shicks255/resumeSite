package com.steven.hicks.Utilities;

import com.steven.hicks.ResultsPage;
import com.steven.hicks.ServerStart;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.Visitor;
import com.steven.hicks.entities.store.items.LegoSet;
import com.steven.hicks.entities.store.items.MusicAlbum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static Integer getInteger(String integer)
    {
        if (integer==null || integer.trim().length()==0) return 0;
        integer = integer.trim();

        int value=0;
        try
        {
            value = Integer.parseInt(integer);
        }
        catch (NumberFormatException e)
        {
        }

        return value;
    }

    public static String getString(Object objectToStringify)
    {
        String answer = "";

        if (objectToStringify instanceof Number)
            return (answer += objectToStringify);

        String object = (String)objectToStringify;
        if (object.length() ==0)
            return "";

        object = object.trim();

        return object;
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

        if (resultPages.size() == 0)
            resultPages.add(resultsPage);

        return resultPages;
    }

    public static String getJSONStringForItem(StoreItemGeneric item) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, IntrospectionException
    {
        String json = "[{";

        if (item.getItemType() == 113)
        {
            MusicAlbum album = (MusicAlbum)item;

            List<PropertyDescriptor> pds = Arrays.asList(Introspector.getBeanInfo(Class.forName("com.steven.hicks.entities.store.items.MusicAlbum")).getPropertyDescriptors());
            for (PropertyDescriptor pd : pds)
            {
                Method method = pd.getReadMethod();
                Object answer1 = method.invoke(album);
                if (answer1 != null && answer1 instanceof Object
                        && !method.getName().equalsIgnoreCase("getClass")
                        && !method.getName().equalsIgnoreCase("getItemPictures")
                        && !method.getName().equalsIgnoreCase("getItemTypeObject"))
                {
                    String answer = getString(answer1);
                    json += "\"" + method.getName() + "\":" + "\"" + answer + "\"";
                    if (pds.indexOf(pd) != pds.size() - 1)
                        json += ",";
                }
            }
        }

        json += "}]";

        return json;
    }
}