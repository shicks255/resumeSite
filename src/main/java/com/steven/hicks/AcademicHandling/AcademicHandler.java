package com.steven.hicks.AcademicHandling;

import com.steven.hicks.FileUploadUtil;
import com.steven.hicks.HibernateUtil;
import com.steven.hicks.entities.AcademicCourse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
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

        if (action.equalsIgnoreCase("uploadCoursework"))
        {
//            String academicCourseId = request.getParameter("uploadCourseId");

//            AcademicCourse course = AcademicLogic.getCourse(Integer.valueOf(academicCourseId));

            FileUploadUtil.uploadFile(request, null, null);

        }

        if (action.equalsIgnoreCase("uploadCoursework2"))
        {
            System.out.println("we got here");
            int courseObjectId = Integer.valueOf(request.getParameter("uploadCourseId"));

            AcademicCourse course = AcademicLogic.getCourse(courseObjectId);

            String fileLocation = request.getParameter("filePicker");
            System.out.println(fileLocation);

            // Create path components to save the file
            final String path = System.getProperty("java.io.tmpdir");
            final Part filePart = request.getPart("filePicker");
//            final String fileName = getFileName(filePart);

            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                out = new FileOutputStream(new File(path + File.separator
                        + filePart.getName()));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                writer.println("New file " + filePart.getName() + " created at " + path);
            } catch (FileNotFoundException fne) {
                writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());

            } finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}
