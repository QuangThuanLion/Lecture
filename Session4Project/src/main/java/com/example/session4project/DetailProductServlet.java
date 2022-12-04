package com.example.session4project;

import com.example.session4project.contants.FunctionUtils;
import com.example.session4project.contants.Product;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DetailProductServlet", value = "/product-detail")
public class DetailProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("name");
        Optional<String> product = FunctionUtils.products
                .stream()
                .filter(x -> productName.equals(x))
                .findFirst();
        request.setAttribute("product", product.get());

        final String requestURL = "/views/product_detail.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(requestURL);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productRoot = request.getParameter("product_root");
        String productUpdate = request.getParameter("product_update");

        List<String> products = FunctionUtils.products;
        products.set(products.indexOf(productRoot), productUpdate);

        for (String item : products) {
            if (item.equals(productRoot)) {
                item = productUpdate;
            }
        }

        String requestURL = request.getContextPath() + "/product-servlet";
        response.sendRedirect(requestURL);
    }
}
