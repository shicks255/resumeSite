package com.steven.hicks.AcademicHandling;

import com.steven.hicks.FileUploadUtil;
import com.steven.hicks.HibernateUtil;
import com.steven.hicks.Utils;
import com.steven.hicks.entities.AcademicCourse;
import com.steven.hicks.entities.Coursework;
import com.steven.hicks.entities.FileRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

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
            response.sendRedirect("academic?action=form");
        }

//        ------EDIT COURSE ACTION
        if (action.equalsIgnoreCase("editACourse"))
        {
            String courseId = request.getParameter("courseId");
            AcademicCourse course = AcademicLogic.getCourse(Integer.valueOf(courseId));

            AcademicLogic.editCourse(request, course);

            response.sendRedirect("academic?action=form");
        }

//        ------DELETE A COURSE ACTION
        if (action.equalsIgnoreCase("deleteACourse"))
        {
            String courseObjectId = request.getParameter("objectId");
            AcademicCourse course = AcademicLogic.getCourse(Integer.valueOf(courseObjectId));

            if (course != null)
                AcademicLogic.deleteCourse(course);

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

//        -----AJAX TO GET COURSEWORK
        if (action.equalsIgnoreCase("getCoursework"))
        {
            int courseObjectId = Integer.valueOf(request.getParameter("courseObjectId"));

            AcademicCourse course = AcademicLogic.getCourse(courseObjectId);

            if (course != null)
            {
                List<Coursework> courseworks = course.getCoursework();
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

            String errorMessage = AcademicLogic.saveCoursework(file, fr);

            if (errorMessage.length() == 0)
                response.sendRedirect("/academic?action=form");

        }

//        -----DELETE A COURSEWORK ITEM
        if (action.equalsIgnoreCase("deleteCoursework"))
        {
            String fileName = request.getParameter("fileName");
            String errorMessage = AcademicLogic.deleteCoursework(fileName);

            response.sendRedirect("/academic?action=form");

        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
