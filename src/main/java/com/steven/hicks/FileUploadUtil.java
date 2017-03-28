package com.steven.hicks;

import com.steven.hicks.AcademicHandling.AcademicLogic;
import com.steven.hicks.entities.AcademicCourse;
import com.steven.hicks.entities.Coursework;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Steven on 0023, March 23, 2017.
 */

public class FileUploadUtil extends HttpServlet
{
    public static String uploadFile(HttpServletRequest request, Object object, Class className)
            throws ServletException, IOException
    {
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 & 1024;

        String contentType = request.getContentType();
        String fileNameReturn = "";

        if (contentType.indexOf("multipart/form-data") >= 0)
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            String tempLocation = System.getProperty("java.io.tmpdir");
            factory.setRepository(new File(tempLocation));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            try
            {
                List fileItems = upload.parseRequest(request);

                Iterator i = fileItems.iterator();

                while (i.hasNext())
                {
                    FileItem fileItem = (FileItem)i.next();
                    String field1 = fileItem.getFieldName();
                    String field1Content = fileItem.getString();
                    if (!fileItem.isFormField())
                    {
                        String fieldName = fileItem.getFieldName();
                        String fileName = fileItem.getName();
                        fileNameReturn = fileName;
                        boolean isInMemory = fileItem.isInMemory();
                        long sizeInBytes = fileItem.getSize();

                        if (fileName.lastIndexOf("\\") >= 0)
                        {
                            file = new File( tempLocation + fileName.substring(fileName.lastIndexOf("\\")));
                        }
                        else
                        {
                            file = new File( tempLocation + fileName.substring(fileName.lastIndexOf("\\") +1));
                        }
                        storeFile(file, object, className);
                        fileItem.write (file);
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return fileNameReturn;
    }

    public static void storeFile(File file, Object object, Class className)
    {
        String fileName = "";
        String additionalNotes = "";
        String course = "";
        String grade = "";
        String semester = "";
        String year = "";


        Coursework coursework = new Coursework();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }


}
