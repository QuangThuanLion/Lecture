package com.example.session3project.controller;

import com.example.session3project.contants.FunctionUtils;
import com.example.session3project.model.Category;
import com.example.session3project.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServletController", value = "/home-servlet-controller")
public class HomeServletController extends HttpServlet {

    private List<Category> categories;

    private List<Product> products;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body>");
        out.println("<h1>Product List</h1>");
        String requestBtnAdd = request.getContextPath() + "/create-product-controller";
        out.println("<a href='" + requestBtnAdd + "'>Add Product<a/>");

        out.println("<ul>");
        for (Product item : products) {
            out.println("<li>ProductId: " + item.getId() + "</li>");
            out.println("<li>ProductName: " + item.getName() + "</li>");
            out.println("<li>ProductPrice: " + item.getPrice() + "</li>");
            out.println("<li>ProductDescription: " + item.getDescription() + "</li>");

            out.println("<li>CreatedTime: " + simpleDateFormat.format(item.getCreatedTime()) + "</li>");
            out.println("<li>UpdatedTime: " + simpleDateFormat.format(item.getUpdatedTime()) + "</li>");
            out.println("<li>Status: " + (item.getStatus() ? "available" : "not available") + " </li>");

            Optional<Category> category = categories.stream()
                    .filter(x -> item.getCategory().getId() != null && item.getCategory().getId().equals(x.getId()))
                    .findFirst();

            category.ifPresentOrElse(
                    x -> out.println("<li>Product Category: " + x.getName() + "</li>"),
                    () -> out.println("<li>There is no such category:</li>"));

            String contentPath = request.getContextPath() + "/detail-servlet-controller?productId=" + item.getId();
            out.println("<a href='" + contentPath + "'>CLick Go To Detail Product<a/>");

            out.println("<br/>");
            out.println("<br/>");
        }
        out.println("<ul/>");
        out.println("<br/>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        categories = FunctionUtils.listCategories();
        products = FunctionUtils.listProducts();

        super.init();
    }
}
