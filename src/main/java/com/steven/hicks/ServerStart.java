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
    public void init() throws ServletException
    {
        log.info("ResumeSite starting up");
        HibernateUtil.initializeSessionFactory();
        ServerStartupTasks.loadDefaultItemTypes()
        ;
        ServletContext sc = getServletContext();

        String path = sc.getRealPath("/");
        sc.setAttribute("imagesPath", path.replace(path, "") + File.separator + "images" + File.separator);

        PicturesLogic.loadPicturesForGallery(sc);
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
