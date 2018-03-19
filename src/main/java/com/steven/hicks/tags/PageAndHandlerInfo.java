package com.steven.hicks.tags;

import com.steven.hicks.Utilities.CommonUtils;
import com.steven.hicks.filters.LogFilter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

public class PageAndHandlerInfo extends TagSupport
{

    private static final Logger log = Logger.getLogger(LogFilter.class.getName());

    @Override
    public int doStartTag()
    {
        JspWriter out = pageContext.getOut();

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        if (CommonUtils.isAdminVisitor(request))
        {
            String pageName = request.getServletPath();

            Map<String, String[]> paramMap = pageContext.getRequest().getParameterMap();
            String action = paramMap.get("action")[0];

            String info = "Action=<b>" + action + "</b> | " + "JspPage=<b>" + pageName + "</b>";

            try
            {
                out.println(info);
            } catch (IOException e)
            {
                log.error(e.getMessage());
                System.out.println(e);
            }
        }

        return SKIP_BODY;
    }




}
