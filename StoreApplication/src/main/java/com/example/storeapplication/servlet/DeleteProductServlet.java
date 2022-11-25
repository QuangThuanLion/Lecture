package com.example.storeapplication.servlet;

import com.example.storeapplication.common.FunctionUtils;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProductServlet", value = "/delete-product-servlet")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String productId = Optional.ofNullable(request.getParameter("product_id")).orElse(null);

        FunctionUtils.products.removeIf(x -> x.getId() != null && productId.equals(x.getId()));
        final String requestURL = request.getContextPath() + "/products-servlet";
        response.sendRedirect(requestURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
