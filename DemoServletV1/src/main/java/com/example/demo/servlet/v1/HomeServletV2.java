package com.example.demo.servlet.v1;

import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/home-servletV2")
public class HomeServletV2 extends HttpServlet {
    private String message;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServletV2() {
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

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
        out.println("<br/>");

        Object attribute = request.getAttribute("data");
        out.println("<h1>" + String.valueOf(attribute) + "</h1>");
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
        System.out.println("destroy home servlet 2");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("init");
        message = "Hello World HomeServlet Version2";
        super.init();
    }
}
