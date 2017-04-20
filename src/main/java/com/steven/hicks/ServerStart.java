package com.steven.hicks;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.Visitor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Steven on 6/6/2016.
 */

@WebServlet(urlPatterns = "")
public class ServerStart extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String visitorIPAddress = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for")!=null)
            visitorIPAddress = request.getHeader("x-forwarded-for");
        System.out.println(visitorIPAddress);
        System.out.println(request.getContextPath());

        if (request.getSession(false) == null)
        {
            Visitor visitor;
            if (Visitor.getVisitor(visitorIPAddress) != null)
            {
                visitor = Visitor.getVisitor(visitorIPAddress);
                visitor.setCountOfVisits(visitor.getCountOfVisits() + 1);

                System.out.println(visitor);

                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
                session.beginTransaction();
                session.update(visitor);
                session.getTransaction().commit();
                session.close();
                factory.close();
            }
            else
            {
                visitor = new Visitor();
                visitor.setIpAddress(visitorIPAddress);
                visitor.setCountOfVisits(1);

                System.out.println(visitor);

                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession();
                session.beginTransaction();
                session.save(visitor);
                session.getTransaction().commit();
                session.close();
                factory.close();
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
