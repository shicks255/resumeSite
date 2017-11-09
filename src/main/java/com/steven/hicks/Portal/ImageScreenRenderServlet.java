package com.steven.hicks.Portal;

import com.steven.hicks.entities.User;
import com.steven.hicks.entities.UserAvatar;

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
