package com.example.firstproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeJSPController", value = "/home-jsp-controller")
public class HomeJSPController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directoryFileName = "/views/admin/home.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(directoryFileName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
