package com.example.demo.servlet.v1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

//@WebServlet(name = "/homeServlet", urlPatterns = { "/home-Servlet/*" })
public class HomeServlet extends HttpServlet {
    private String message;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("text/html");

        final List<String> products = Arrays.asList("banana", "apple", "orange", "cucumber", "durian");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        out.println("<br/>");
        out.println("<h1>Products List</h1>");
        out.println("<a href='/DemoServletV1/home-servletV2'>Click me</a>");
        out.println("<br/>");

        out.println("<ul><ul/>");
        for (String item : products) {
            out.println("<li>" + item + "</li>");
            out.println("<br/>");
        }

//		final String url = "/HomeServletV2";
//		request.setAttribute("data", "From HomeServlet");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);

//      dispatcher.include(request, response);
//		response.sendRedirect(request.getContextPath() + "/HomeServletV2");
//		response.sendRedirect("http://localhost:8080/DemoServlet/HomeServletV2");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("service ServletResponse");
        super.service(req, res);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("destroy home servlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("init");
        message = "Hello World";
        super.init();
    }
}
