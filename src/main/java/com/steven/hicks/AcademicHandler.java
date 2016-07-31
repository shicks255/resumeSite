package com.steven.hicks;

import com.steven.hicks.entities.AcademicCourse;

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

        if (action.equalsIgnoreCase("form"))
        {
            List<AcademicCourse> academicCourseList = AcademicLogic.getCourseList();
            request.setAttribute("courseList", academicCourseList);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("thesis"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/thesis.jsp");
            dispatcher.forward(request, response);
        }

        if (action.equalsIgnoreCase("bibliography"))
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/education/bibliography.jsp");
            dispatcher.forward(request, response);

        }

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
