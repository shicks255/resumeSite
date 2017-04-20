package com.steven.hicks.Utilities;

import com.steven.hicks.entities.FileRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FileUploadUtil extends HttpServlet
{
    public static File uploadFile(HttpServletRequest request)
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
                        fileItem.write (file);

                        return file;
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return null;
    }

    public static FileRequest getFileRequest(HttpServletRequest request)
    {
        FileRequest fileRequest = new FileRequest();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        File tempFile = null;
        try
        {
            List items = upload.parseRequest(request);
            for(Object item1 : items)
            {
                FileItem item = (FileItem) item1;
                if (!item.isFormField() && item.getSize()>0 && item.getName()!=null && item.getName().length()>0)
                {
                    String extension = "";
                    String filename = item.getName().toLowerCase();
                    if (filename.contains("."))
                        extension = filename.substring(filename.lastIndexOf('.') + 1);

                    // Store in the temporary directory
                    tempFile = File.createTempFile("temp", "." + extension, new File(System.getProperty("java.io.tmpdir")));
                    item.write(tempFile);
                    item.delete();

                    fileRequest.setUploadedFile(tempFile);
                    fileRequest.setShortFilename(item.getName());
                }

                if (item.isFormField() && item.getFieldName()!=null && item.getFieldName().length()>0)
                    fileRequest.getParameters().put(item.getFieldName(), item.getString());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return fileRequest;

    }

}
