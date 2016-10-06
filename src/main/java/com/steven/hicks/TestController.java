package com.steven.hicks;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Steven on 10/5/2016.
 */

@WebServlet(urlPatterns = "/controltab1")
public final class TestController extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {

        String test = "did we get here";

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request, response);
    }


}
