package com.steven.hicks.Portal;

import com.steven.hicks.entities.User;
import com.steven.hicks.entities.UserAvatar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
            int avatarId = Integer.valueOf(request.getParameter("avatarId"));

            UserAvatar avatar = UserAvatar.getAvatar(avatarId);

            response.setContentType("image/jpg");
            response.setContentLengthLong(avatar.getPicture().length);

            response.getOutputStream().write(avatar.getPicture());
            //do i need this??
            response.getOutputStream().flush();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
