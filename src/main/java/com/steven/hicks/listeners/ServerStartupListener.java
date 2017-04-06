package com.steven.hicks.listeners;

import com.steven.hicks.PicturesLogic;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

/**
 * Created by Steven on 0005, April 5, 2017.
 */


public class ServerStartupListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext sc = event.getServletContext();

        PicturesLogic.loadPictures(sc);

    }

    public void contextDestroyed(ServletContextEvent event)
    {

    }


}
