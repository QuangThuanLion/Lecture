package com.example.firstproject;

import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class InitParamServlet extends HttpServlet {

    private String emailSupport1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();
        response.setContentType("text/html");

        out.println("<html><body>");
        out.println("<br/>");
        out.println("<p>SERVLET CONTEXT</p>");
        out.println(servletContext.getInitParameter("name"));
        out.println(servletContext.getInitParameter("website"));
        out.println("<br/>");
        out.println("<p>SERVLET CONFIG</p>");
        out.println("<p>emailSupport1: " + this.emailSupport1 + "</p>");
        out.println("<p>emailSupport2: " + servletConfig.getInitParameter("emailSupport2") + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.emailSupport1 = config.getInitParameter("emailSupport1");
        super.init(config);
    }
}
