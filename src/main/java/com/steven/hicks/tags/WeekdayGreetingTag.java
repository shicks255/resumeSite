package com.steven.hicks.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.TagSupport;
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
        else if (day == Calendar.FRIDAY)
            greetingTest = "At least it's friday :)";
        else
        {
            int daysLeft = 6 - day;

            if (daysLeft == 1)
                greetingTest = "Only " + daysLeft + " day left until friday.";
            else
                greetingTest = "Only " + daysLeft + " days left until friday";
        }

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
