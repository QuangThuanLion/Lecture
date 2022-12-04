package com.example.session3project.controller;

import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateServletController", value = "/create-product-controller")
public class CreateServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>CREATE PRODUCT</h1>");
        out.println("<br/>");
        out.println("<form>");

        out.println("<label>Product Name</label>");
        out.println("<input name='product_name'>/input>");

        out.println("<label>Product Price</label>");
        out.println("<input name='product_price'>/input>");

        out.println("<label>Product Descriptions</label>");
        out.println("<input name='product_description'>/input>");

        out.println("<label>Product Name</label>");
        out.println("<input name='product_name'>/input>");

        

        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
