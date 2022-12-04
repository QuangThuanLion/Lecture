package com.example.firstproject;

import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InitiParamServlet2", value = "/initial-Param-Servlet2",
    initParams = {
        @WebInitParam(name = "emailSupport3", value = "12345@gmail.com"),
        @WebInitParam(name = "emailSupport4", value = "7890@gmail.com")
    }
)
public class InitiParamServlet2 extends HttpServlet {
    private String emailSupport4;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();

        out.println("<html><body>");
        out.println("<br/>");
        out.println("<p>SERVLET CONTEXT</p>");
        out.println(servletContext.getInitParameter("name"));
        out.println(servletContext.getInitParameter("website"));
        out.println("<br/>");
        out.println("<p>SERVLET CONFIG</p>");
        out.println("<p>emailSupport3: " + servletConfig.getInitParameter("emailSupport3") + "</p>");
        out.println("<p>emailSupport4: " + servletConfig.getInitParameter("emailSupport4") + "</p>");

        out.println("<p>emailSupport2: " + servletConfig.getInitParameter("emailSupport2") + "</p>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.emailSupport4 = config.getInitParameter("emailSupport4");
        super.init(config);
    }
}
