package com.example.storeapplication.servlet;

import com.example.storeapplication.constant.MappingUtils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter( "/*")
public class FilterServlet implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession();

        final String requestURI = request.getRequestURI();
        final String defaultHomePageURL = request.getContextPath() + "/";
        final String loginURI = request.getContextPath() + "/login-servlet";
        boolean loggedIn = session != null && session.getAttribute(MappingUtils.SESSION_NAME) != null;

//        final Cookie[] cookies = request.getCookies();
//        if(cookies != null && requestURI.equals(defaultHomePageURL)) {
//            for(Cookie cookie : cookies) {
//                String cookieName = cookie.getName();
//                if(cookieName.equalsIgnoreCase(MappingUtils.COOKIE_NAME)) {
//                    final String requestURL = "/StoreApplication/products-servlet";
//                    response.sendRedirect(requestURL);
//                    return;
//                }
//            }
//        }

        if (!loggedIn && requestURI.equals(loginURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!loggedIn && !(requestURI.equals(defaultHomePageURL))) {
            final String requestURL = request.getContextPath() + "/";
            response.sendRedirect(requestURL);
        } else if (requestURI.equals(defaultHomePageURL) && loggedIn) {
            final String requestURL = request.getContextPath() + "/products-servlet";
            response.sendRedirect(requestURL);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
