package com.example.firstproject;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomePageJSPController", value = "/home-page-servlet")
public class HomePageJSPController extends HttpServlet {
    private List<String> products = new ArrayList<>();

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directoryFileName = "/views/admin/home-page.jsp";
        request.setAttribute("products", products);
        request.setAttribute("confirm", "Hello Confirm");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(directoryFileName);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
