package com.steven.hicks.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeekdayGreetingTag extends TagSupport
{
    @Override
    public int doStartTag() throws JspException
    {
        String greetingTest = "";
        Calendar currentDate = new GregorianCalendar();
        int day = currentDate.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY)
            greetingTest = "I hope you're enjoying the weekend :)";
        else
            greetingTest = "At least it's almost Friday :)";

        try
        {
            JspWriter out = pageContext.getOut();
            out.print(greetingTest);
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }
        return EVAL_BODY_INCLUDE;
    }
}
