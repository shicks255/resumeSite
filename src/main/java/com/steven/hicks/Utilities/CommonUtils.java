package com.steven.hicks.Utilities;

import javax.servlet.ServletContext;


public final class CommonUtils
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

    public static String getMimeType(String filename)
    {
        if (filename == null || filename.length()<4 ) return "";
        filename = filename.toLowerCase();
        String mimetype = "";

        if (filename.indexOf('.')>=0)
        {
            String extension = filename.substring(filename.toLowerCase().lastIndexOf('.') + 1);

            if (extension.equals("ics"))        mimetype = "text/calendar";
            if (extension.equals("ppt"))        mimetype = "application/ms-powerpoint";
            if (extension.equals("pptx"))       mimetype = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            if (extension.equals("doc"))        mimetype = "application/msword";
            if (extension.equals("rtf"))        mimetype = "application/msword";
            if (extension.equals("docx"))       mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            if (extension.equals("dotx"))       mimetype = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
            if (extension.equals("xls"))        mimetype = "application/vnd.ms-excel";
            if (extension.equals("xlsx"))       mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            if (extension.equals("xltx"))       mimetype = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
            if (extension.equals("pdf"))        mimetype = "application/pdf";
            if (extension.equals("jpg"))        mimetype = "image/jpeg";
            if (extension.equals("gif"))        mimetype = "image/gif";
            if (extension.equals("txt"))        mimetype = "text/plain";
            if (extension.equals("log"))        mimetype = "text/plain";
            if (extension.equals("bz2"))        mimetype = "application/bzip2";
            if (extension.equals("gz"))         mimetype = "application/gzip";
            if (extension.equals("csv"))        mimetype = "text/csv";
            if (extension.equals("flipchart"))  mimetype = "application/Inspire flipchart";
            if (extension.equals("png"))        mimetype = "image/png";
            if (extension.equals("crx"))        mimetype = "application/unknown";
            if (extension.equals("ttf"))        mimetype = "application/octet-stream";
            if (extension.equals("otf"))        mimetype = "application/octet-stream";
            if (extension.equals("xml"))        mimetype = "application/xml";
            if (extension.equals("mp4"))        mimetype = "video/mp4";
            if (extension.equals("mov"))        mimetype = "video/quicktime";
        }
        return mimetype;
    }


}
