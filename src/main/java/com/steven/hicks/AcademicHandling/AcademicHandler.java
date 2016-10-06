package com.steven.hicks.AcademicHandling;

import com.steven.hicks.HibernateUtil;
import com.steven.hicks.entities.AcademicCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Steven on 6/18/2016.
 */


@WebServlet(urlPatterns = "/academic")
public class AcademicHandler extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

//        ------PAGE LOAD
        if (action.equalsIgnoreCase("form"))
        {
            List<AcademicCourse> academicCourseList = AcademicLogic.getCourseList();
            request.setAttribute("courseList", academicCourseList);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education.jsp");
            dispatcher.forward(request, response);
        }

//        ------THESIS REDIRECT
        if (action.equalsIgnoreCase("thesis"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/thesis.jsp");
            dispatcher.forward(request, response);
        }

//        ------BIBLIOGRAPHY REDIRECT
        if (action.equalsIgnoreCase("bibliography"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/bibliography.jsp");
            dispatcher.forward(request, response);

        }

//        ------ADD COURSE ACTION
        if (action.equalsIgnoreCase("addACourse"))
        {
            String courseName = request.getParameter("courseName");
            String courseCode = request.getParameter("courseCode");
            String collegeName = request.getParameter("collegeName");
            String courseGrade = request.getParameter("courseGrade");
            String semester = request.getParameter("semester");

            AcademicCourse course = new AcademicCourse();
            course.setCourseName(courseName);
            course.setCourseCode(courseCode);
            course.setCollege(collegeName);
            course.setGradeReceived(courseGrade);
            course.setSemester(semester);

            course.setSemesterTrackingNumber(AcademicLogic.getSemesterNumberForSemester(semester));
            AcademicLogic.addCourse(course);

            response.sendRedirect("academic?action=form");
        }

//        ------EDIT COURSE ACTION
        if (action.equalsIgnoreCase("editACourse"))
        {
            String courseId = request.getParameter("courseId");
            AcademicCourse course = AcademicLogic.getCourse(Integer.valueOf(courseId));

            course.setCourseName(request.getParameter("courseNameEdit"));
            course.setCourseCode(request.getParameter("courseCodeEdit"));
            course.setCollege(request.getParameter("collegeNameEdit"));
            course.setSemester(request.getParameter("semesterEdit"));
            course.setGradeReceived(request.getParameter("courseGradeEdit"));

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();

            response.sendRedirect("academic?action=form");
        }

//        ------DELETE A COURSE ACTION
        if (action.equalsIgnoreCase("deleteACourse"))
        {
            String courseObjectId = request.getParameter("objectId");
            AcademicCourse course = AcademicLogic.getCourse(Integer.valueOf(courseObjectId));

            if (course != null)
            {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.delete(course);
                session.getTransaction().commit();
                session.close();
                sessionFactory.close();
            }

            response.sendRedirect("academic?action=form");
        }

//        ------AJAX GET A COURSE TO EDIT
        if (action.equalsIgnoreCase("getAjaxForEditingCourse"))
        {
            int courseObjectId = Integer.valueOf(request.getParameter("courseObjectId"));
            AcademicCourse course = AcademicLogic.getCourse(courseObjectId);

            request.setAttribute("course", course);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/education/editCoursePopup.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}