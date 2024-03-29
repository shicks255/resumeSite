package com.steven.hicks.filters;


import com.steven.hicks.ServerStart;
import org.apache.log4j.Logger;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(urlPatterns = "/*")
public class LogFilter implements Filter
{
    private FilterConfig filterConfig = null;
    private static final Logger log = Logger.getLogger(LogFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
        String dateTimeFormatted = dateTime.format(dateTimeFormatter);

        String requestUri = httpRequest.getRequestURI();

        if (!requestUri.contains("CSS") && !requestUri.contains("fonts") && !requestUri.contains("JS") && !requestUri.contains("icons") && !requestUri.contains("images"))
            log.info(httpRequest.getRequestURI() + " - Starting at " + dateTimeFormatted + " from " + httpRequest.getRemoteAddr());

        chain.doFilter(httpRequest, httpResponse);

        if (!requestUri.contains("CSS") && !requestUri.contains("fonts") && !requestUri.contains("JS") && !requestUri.contains("icons") && !requestUri.contains("images"))
        {
            LocalDateTime endTime = LocalDateTime.now();
            String endTimeFormatted = endTime.format(dateTimeFormatter);

            log.info(httpRequest.getRequestURI() + " - Ending at " + endTimeFormatted + " from " + httpRequest.getRemoteAddr());
        }
    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }

}
