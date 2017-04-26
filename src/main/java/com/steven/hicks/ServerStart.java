package com.steven.hicks;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.Visitor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "")
public class ServerStart extends HttpServlet
{
    private static final Logger log = Logger.getLogger(ServerStart.class.getName());

    @Override
    public void init() throws ServletException
    {
        log.info("ResumeSite starting up");
        ServerStartupTasks.loadDefaultItemTypes();
    }

    @Override
    public void destroy()
    {
        log.info("ResumeSite shutting down");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String visitorIPAddress = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for")!=null)
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
            }
            else
            {
                visitor = new Visitor();
                visitor.setIpAddress(visitorIPAddress);
                visitor.setCountOfVisits(1);

                HibernateUtil.createItem(visitor);
                log.info("New visited logged " + visitor.toString());
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
