package com.steven.hicks.AcademicHandling;

import com.steven.hicks.Utilities.FileUploadUtil;
import com.steven.hicks.entities.AcademicCourse;
import com.steven.hicks.entities.Coursework;
import com.steven.hicks.entities.FileRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/academic")
public class AcademicHandler extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

//        ------PAGE LOAD
        if (action.equalsIgnoreCase("form"))
        {
            List<AcademicCourse> academicCourseList = AcademicLogic.getCourseList();
            request.setAttribute("courseList", academicCourseList);

            String visitingIPAddress = request.getRemoteHost();
            if (visitingIPAddress.equals("67.87.211.190"))
                request.setAttribute("adminComputer", "adminComputer");

            System.out.println(visitingIPAddress);

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
            AcademicLogic.addCourse(request);
            response.sendRedirect(getServletContext().getContextPath() + "/academic?action=form");
        }

//        ------EDIT COURSE ACTION
        if (action.equalsIgnoreCase("editACourse"))
        {
            String courseId = request.getParameter("courseId");
            AcademicCourse course = AcademicCourse.getCourse(Integer.valueOf(courseId));

            AcademicLogic.editCourse(request, course);

            response.sendRedirect(getServletContext().getContextPath() + "/academic?action=form");
        }

//        ------DELETE A COURSE ACTION
        if (action.equalsIgnoreCase("deleteACourse"))
        {
            String courseObjectId = request.getParameter("objectId");
            AcademicCourse course = AcademicCourse.getCourse(Integer.valueOf(courseObjectId));

            if (course != null)
                AcademicLogic.deleteCourse(course);

            response.sendRedirect(getServletContext().getContextPath() + "/academic?action=form");
        }

//        ------AJAX GET A COURSE TO EDIT
        if (action.equalsIgnoreCase("getAjaxForEditingCourse"))
        {
            int courseObjectId = Integer.valueOf(request.getParameter("courseObjectId"));
            AcademicCourse course = AcademicCourse.getCourse(courseObjectId);

            request.setAttribute("course", course);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/education/editCoursePopup.jsp");
            dispatcher.forward(request, response);
        }

//        -----AJAX TO GET COURSEWORK
        if (action.equalsIgnoreCase("getCoursework"))
        {
            int courseObjectId = Integer.valueOf(request.getParameter("courseObjectId"));

            AcademicCourse course = AcademicCourse.getCourse(courseObjectId);

            if (course != null)
            {
                List<Coursework> courseworks = course.getCourseworks();
                request.setAttribute("courseWorkList", courseworks);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/education/showCourseworkPopup.jsp");
            dispatcher.forward(request, response);
        }

//        -----PRINT COURSEWORK TO BROWSER
        if (action.equalsIgnoreCase("printCoursework"))
        {
            AcademicLogic.printCoursework(request, response);
        }

//        --------Upload coursework
        if (action.equalsIgnoreCase("uploadCoursework"))
        {
            FileRequest fr = FileUploadUtil.getFileRequest(request);
            File file = fr.getUploadedFile();

            AcademicLogic.saveCoursework(file, fr);

            response.sendRedirect(getServletContext().getContextPath() + "/academic?action=form");
        }

//        -----DELETE A COURSEWORK ITEM
        if (action.equalsIgnoreCase("deleteCoursework"))
        {
            String fileName = request.getParameter("fileName");
            AcademicLogic.deleteCoursework(fileName);

            response.sendRedirect(getServletContext().getContextPath() + "/academic?action=form");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
