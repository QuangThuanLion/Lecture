package com.example.firstproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", value = "/home-servlet/*")
public class HomeServlet extends HttpServlet {

    private List<String> products = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ServletContext servletContext = getServletContext();
        ServletConfig servletConfig = getServletConfig();
//        response.setContentType("application/json");
        response.setContentType("text/html");

        out.println("<html><body>");
        out.println("<h1>Product List</h1>");

        out.println("<ul>");
        for (String item : products) {
            out.println("<li>" + item + "</li>");
            out.println("<br/>");
        }
        out.println("<ul/>");
        out.println("<br/>");
        out.println("<a href='/FirstProject/hello-servlet'>CLick To Hello Servlet<a/>");
        out.println(servletContext.getInitParameter("name"));
        out.println(servletContext.getInitParameter("website"));
        out.println("<p>emailSupport2: " + servletConfig.getInitParameter("emailSupport2") + "</p>");
        out.println("</body></html>");

//        final String requestURL = "/hello-servlet";

//        request.setAttribute("message", "Message Value");
//        RequestDispatcher dispatcher = request.getRequestDispatcher(requestURL);
//        dispatcher.forward(request, response);

//        final String requestURL = request.getContextPath() + "/hello-servlet";
//        response.sendRedirect(requestURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("service method: " + request.getMethod());
        super.service(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");

        System.out.println("size products: " + products.size());

        products.clear();

        System.out.println("size products: " + products.size());

        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");

        products.add("banana");
        products.add("apple");
        products.add("orange");
        products.add("cucumber");
        products.add("watermelon");

        super.init();
    }
}
