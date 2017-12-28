package com.steven.hicks.TechHandling.logics;

//import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import com.steven.hicks.entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TechLogic
{
    public static int getSessionAccessAcount(HttpSession session, HttpServletRequest request)
    {
        Integer accessCount;
        synchronized(session)
        {
            if (!request.isRequestedSessionIdValid())
                session = request.getSession();

            accessCount = (Integer)session.getAttribute("accessCountSession");
            if (accessCount == null)
                accessCount = 1;   // autobox int to Integer
            else
                accessCount = new Integer(accessCount + 1 + "");
        }

        session.setAttribute("accessCountSession", accessCount);

        return accessCount;
    }

}
