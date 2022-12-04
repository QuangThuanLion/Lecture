package com.example.firstproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Object message = request.getAttribute("message");
        ServletContext servletContext = getServletContext();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<br/>");
        out.println("<h1>Hello Class !!!!!</h1>");
        out.println(servletContext.getInitParameter("name"));
        out.println(servletContext.getInitParameter("website"));

        out.println("<h1>attribute : " + String.valueOf(message) + "</h1>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}