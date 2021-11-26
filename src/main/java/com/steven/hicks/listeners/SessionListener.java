package com.steven.hicks.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener
{
    //called when sessions are created/destroyed
    public void sessionCreated(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
    }

}
