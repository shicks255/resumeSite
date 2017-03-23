package com.steven.hicks.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class CurrentDateTag extends TagSupport
{
    @Override
    public int doStartTag() throws JspException
    {
        Date currentDate = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        String currentDateFormatted = df.format(currentDate);

        try
        {
            JspWriter out = pageContext.getOut();
            out.print(currentDateFormatted);
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }

        return SKIP_BODY;
    }
}
