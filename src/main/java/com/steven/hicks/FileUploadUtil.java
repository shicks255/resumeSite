package com.steven.hicks;

import com.steven.hicks.AcademicHandling.AcademicLogic;
import com.steven.hicks.entities.AcademicCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Steven on 0023, March 23, 2017.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class FileUploadUtil extends HttpServlet
{

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("we got here");
        int courseObjectId = Integer.valueOf(request.getParameter("uploadCourseId"));

        AcademicCourse course = AcademicLogic.getCourse(courseObjectId);

        String fileLocation = request.getParameter("file");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        processRequest(request, response);
    }

}
