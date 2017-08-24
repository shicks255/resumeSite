package com.steven.hicks.filters;

import com.steven.hicks.Utilities.HibernateUtil;
import com.steven.hicks.entities.User;
import com.steven.hicks.entities.store.Cart;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@WebFilter(urlPatterns = {"/portal", "/portalItemHandler"})
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

            Session hSession = HibernateUtil.sessionFactory.openSession();
            hSession.beginTransaction();

            User user = (User)session.getAttribute("user");

            if (user == null)
            {
                user = hSession.get(User.class, principal.getName());
                session.setAttribute("user", user);
            }

            hSession.close();
//            Cart cart = (Cart)session.getAttribute("cart");
//
//            if (cart == null)
//            {
//
//                Query query = hSession.createQuery("from Cart where userNameOfCart = \'" + principal.getName() + "\'");
//                List<Cart> carts = query.list();
//
//                if (carts.size() > 0)
//                    cart = carts.get(0);
//
//                cart = Cart.getCartByUser(user.getUserName());
//                if (cart == null)
//                {
//                    cart = new Cart();
//                    cart.setUserNameOfCart(user.getUserName());
//                    hSession.save(cart);
////                    HibernateUtil.createItem(cart);
//                }
//            }

//
//            hSession.refresh(cart);
////            hSession.load(Cart.class, cart);
//            hSession.getTransaction().commit();
//            hSession.close();
//
//            session.setAttribute("cart", cart);       hse
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
