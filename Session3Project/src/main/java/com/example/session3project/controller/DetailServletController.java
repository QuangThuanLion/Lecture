package com.example.session3project.controller;

import com.example.session3project.contants.FunctionUtils;
import com.example.session3project.model.Category;
import com.example.session3project.model.Product;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DetailServletController", value = "/detail-servlet-controller")
public class DetailServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Detail Product</h1>");

        String productId = request.getParameter("productId");
        out.println("<h2>Product Id: " + productId + "</h2>");

        Optional<Product> product = FunctionUtils.products.stream()
                .filter(x -> productId != null && productId.equals(x.getId()))
                .findFirst();

        out.println("<li>ProductName: " + product.get().getName() + "</li>");
        out.println("<li>ProductPrice: " + product.get().getPrice() + "</li>");
        out.println("<li>ProductDescription: " + product.get().getDescription() + "</li>");

        out.println("<li>CreatedTime: " + simpleDateFormat.format(product.get().getCreatedTime()) + "</li>");
        out.println("<li>UpdatedTime: " + simpleDateFormat.format(product.get().getUpdatedTime()) + "</li>");
        out.println("<li>Status: " + (product.get().getStatus() ? "available" : "not available") + " </li>");

        Optional<Category> category = FunctionUtils.categories.stream()
                .filter(x -> product.get().getCategory().getId() != null && product.get().getCategory().getId().equals(x.getId()))
                .findFirst();

        out.println("<li>Product Category: " + category.get().getName() + "</li>");

        String contentPath = request.getContextPath() + "/home-servlet-controller";
        out.println("<a href='" + contentPath + "'>Back To Home Page<a/>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
