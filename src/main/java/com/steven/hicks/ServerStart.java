package com.steven.hicks;

import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.Utilities.HibernateUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "")
public class ServerStart extends HttpServlet
{
    private static final Logger log = Logger.getLogger(ServerStart.class.getName());

    @Override
    public void init()
    {
        log.info("ResumeSite starting up");
        HibernateUtil.initializeSessionFactory();

        new Thread(() -> {
            ServerStartupTasks.loadDefaultItemTypes();
            ServerStartupTasks.initializePictures(getServletContext());
            log.info("Startup tasks complete, server fully started");
        }).start();

        log.info("Starting Tasks");
    }

    @Override
    public void destroy()
    {
        log.info("ResumeSite shutting down");
        HibernateUtil.destroySessionFactory();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CommonUtils.increaseVisitorCountIfNewSession(request);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
