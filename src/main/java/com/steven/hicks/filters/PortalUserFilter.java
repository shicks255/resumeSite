package com.steven.hicks.filters;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.StoreItemGeneric;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.store.Cart;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebFilter(urlPatterns = "/portal")
public class PortalUserFilter implements Filter
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

        String requestUri = httpRequest.getRequestURI();

        if (!requestUri.contains("CSS") && !requestUri.contains("fonts") && !requestUri.contains("JS") && !requestUri.contains("icons") && !requestUri.contains("images"))
        {
            Principal principal = ((HttpServletRequest) request).getUserPrincipal();
            HttpSession session = ((HttpServletRequest) request).getSession();

            User user = (User)session.getAttribute("user");

            if (user == null)
            {
                user = User.getUser(principal.getName());
                session.setAttribute("user", user);
            }


//            List<StoreItemGeneric> allItems = StoreItemGeneric.getAllItems();
//            session.setAttribute("allItems", allItems);

//            Cart cart =
//            if (session.getAttribute("cart") == null)
//            {
//                Cart cart = Cart.getCartByUser(user.getUserName());
//                if (cart == null)
//                {
//                    cart = new Cart();
//                    cart.setUserNameOfCart(user.getUserName());
//                    HibernateUtil.createItem(cart);
//                }
//                session.setAttribute("cart", cart);
//            }
        }

        chain.doFilter(httpRequest, httpResponse);

        if (!requestUri.contains("CSS") && !requestUri.contains("fonts") && !requestUri.contains("JS") && !requestUri.contains("icons") && !requestUri.contains("images"))
        {

        }

    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }

}
