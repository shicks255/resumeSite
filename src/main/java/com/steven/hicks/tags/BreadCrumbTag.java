package com.steven.hicks.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreadCrumbTag extends TagSupport
{

    private String servletContext;

    public String getServletContext()
    {
        return servletContext;
    }

    public void setServletContext(String servletContext)
    {
        this.servletContext = servletContext;
    }

    @Override
    public int doStartTag() throws JspException
    {
//            <div class="nav-wrapper">
//        <div class="col s12">
//            <a href="#!" class="breadcrumb">First</a>
//            <a href="#!" class="breadcrumb">Second</a>
//            <a href="#!" class="breadcrumb">Third</a>
//        </div>
//    </div>


            List<String> things = new ArrayList<>();

            things = Arrays.asList(servletContext.split("/"));

            things.forEach(thing ->
            {

            });



        try
        {
            JspWriter out = pageContext.getOut();

            out.println("<div class=\'nav-wrapper\'>");
                out.println("<div class=\'col s12\'>");

                for (String thing : things)
                {
//                    String link = servletContext.con
                }


                out.println("</div>");
            out.println("</div>");



            out.print(servletContext);
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }


        return EVAL_BODY_INCLUDE;
    }

}
