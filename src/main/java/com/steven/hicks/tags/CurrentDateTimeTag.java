package com.steven.hicks.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTimeTag extends TagSupport
{
    @Override
    public int doStartTag() throws JspException
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyy  -   HH:mm:ss:SSS");
        String dateTimeFormatted = dateTime.format(dateTimeFormatter);

        try
        {
            JspWriter out = pageContext.getOut();
            out.print(dateTimeFormatted);
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }

        return SKIP_BODY;
    }
}
