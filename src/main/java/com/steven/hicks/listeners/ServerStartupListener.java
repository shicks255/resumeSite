package com.steven.hicks.listeners;

import com.steven.hicks.PicturesLogic;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

@WebListener
public class ServerStartupListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext sc = event.getServletContext();

        String path = sc.getRealPath("/");
        sc.setAttribute("imagesPath", path.replace(path, "") + File.separator + "images" + File.separator);

        PicturesLogic.loadPictures(sc);

    }

    public void contextDestroyed(ServletContextEvent event)
    {
        String test = "test";

    }


}
