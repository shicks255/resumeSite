package com.steven.hicks.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener
{

    public void sessionCreated(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
    }

}
