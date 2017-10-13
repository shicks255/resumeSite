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
//            <a href="#!" class="breadcrumb">First</a>
//            <a href="#!" class="breadcrumb">Second</a>
//            <a href="#!" class="breadcrumb">Third</a>

            List<String> things = new ArrayList<>();
            things = Arrays.asList(servletContext.split("/"));
        try
        {
            JspWriter out = pageContext.getOut();

            int numberOfTabs = things.size();

            out.println("<div class=\'nav-wrapper\'>");
            out.println("<div class=\'col s12\'>");

            for (String thing : things)
            {
                if (thing.length() == 0 || thing.contains(".jsp"))
                    continue;
                System.out.println(thing);

                String link = "";

                if (things.get(0).equalsIgnoreCase(thing))
                {
                    link = getServletContext() + thing + "?action=form";
                }

                if (!things.get(0).equalsIgnoreCase(thing))
                {
                    link = getServletContext() + things.get(0) + "action=" + thing;
                }

                out.println("<a href=\'" + link + "\' class=breadcrumb>" + thing + "</a>");
            }


            out.println("</div>");
            out.println("</div>");

//            out.print(servletContext);
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }


        return EVAL_BODY_INCLUDE;
    }

}
