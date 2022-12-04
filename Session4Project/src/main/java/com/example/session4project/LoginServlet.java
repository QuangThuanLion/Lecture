package com.example.session4project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = request.getContextPath();
        response.sendRedirect(requestURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            String requestURL = request.getContextPath() + "/product-servlet";
            response.sendRedirect(requestURL);
        } else {
            String requestURL = "index.jsp";
            final String message = "username or password not invalid";
            request.setAttribute("message",message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(requestURL);
            requestDispatcher.forward(request, response);
        }

    }
}
