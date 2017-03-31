package com.steven.hicks;

import javax.servlet.ServletContext;


public final class Utils
{
    public static void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }


}
