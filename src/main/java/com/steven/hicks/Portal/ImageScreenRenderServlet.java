package com.steven.hicks.Portal;

import com.steven.hicks.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@WebServlet (urlPatterns = "/imageScreenRenderServlet")
public class ImageScreenRenderServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("form"))
        {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            response.setContentType("image/jpg");
            response.setContentLengthLong(user.getAvatar().getPicture().length);

            response.getOutputStream().write(user.getAvatar().getPicture());

//            PrintWriter out = response.getWriter();
//            String imageSource = "data:image/png;base64, ";
//            imageSource += Base64.getEncoder().encodeToString(user.getAvatar().getPicture());
//            imageSource += org.apache.commons.codec.binary.Base64.encodeBase64String(user.getAvatar().getPicture());
//            out.write(imageSource);

//            out.flush();
//            out.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
