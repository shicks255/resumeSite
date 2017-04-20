package com.steven.hicks;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/pictures")
public class PicturesHandler extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {
            ServletContext context = getServletContext();
            String path = context.getRealPath("/");

            String imageRoot = path.replace(path, "") + File.separator + "images" + File.separator;
            System.out.println(path + " " + imageRoot);

            List<Object> files = Files.list(Paths.get(path + imageRoot)).collect(Collectors.toList());

            List<String> fileList = new ArrayList<>();
            files.forEach(file ->
            {
                String newFile = file.toString();
                newFile = newFile.substring(newFile.lastIndexOf("\\") + 1);
                fileList.add(newFile);
            });

            fileList.removeIf(fileName -> !fileName.contains("_small"));

            request.setAttribute("fileList", fileList);
            request.setAttribute("imagePath", getServletContext().getAttribute("imagesPath"));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pictures.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
