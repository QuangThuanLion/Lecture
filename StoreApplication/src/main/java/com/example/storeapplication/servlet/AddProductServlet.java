package com.example.storeapplication.servlet;

import com.example.storeapplication.common.FunctionUtils;
import com.example.storeapplication.model.Category;
import com.example.storeapplication.model.Product;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddProductServlet", value = "/add-product-servlet")
public class AddProductServlet extends HttpServlet {

    private List<Category> categories;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = "/views/add_product.jsp";
        final String productId = request.getParameter("productId");

        if (Objects.isNull(productId)) {
            request.setAttribute("categories", categories);
            RequestDispatcher dispatcher = request.getRequestDispatcher(requestURL);

            dispatcher.forward(request, response);
            return;
        }

        final Optional<Product> product = FunctionUtils.products
                .stream()
                .filter(x -> x.getId() != null && productId.equals(x.getId()))
                .findFirst();

        if (product.isPresent()) {
            requestURL = "/views/update_product.jsp";
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String createdTime = simpleDateFormat.format(product.get().getCreatedTime());

            request.setAttribute("product", product.get());
            request.setAttribute("categories", categories);
            request.setAttribute("createdTime", createdTime);

            RequestDispatcher dispatcher = request.getRequestDispatcher(requestURL);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String productName = request.getParameter("product_name");
        final String price = request.getParameter("price");
        final String description = request.getParameter("description");
        final Boolean status = Boolean.valueOf(request.getParameter("status"));
        final String categoryId = request.getParameter("category");
        final String color = request.getParameter("color");

        final String productId = request.getParameter("product_id");
        final String createdTime = Optional.ofNullable(request.getParameter("createdTime")).orElse(null);

        //CREATE
        if (Objects.isNull(productId)) {
            final Optional<Category> category = categories.stream()
                    .filter(x -> x.getId() != null && categoryId.equals(x.getId()))
                    .findFirst();

            final Product product = new Product(String.valueOf(Math.random()), productName, Integer.valueOf(price),
                    description, new Date(), new Date(), status,
                    category.get(), color);

            FunctionUtils.products.add(product);

            final String requestURL = request.getContextPath() + "/products-servlet";
            response.sendRedirect(requestURL);
            return;
        }

        //UPDATE
        final Optional<Product> product = FunctionUtils.products
                .stream()
                .filter(x -> x.getId() != null && productId.equals(x.getId()))
                .findFirst();
        final Optional<Category> category = FunctionUtils.categories
                .stream()
                .filter(x -> x.getId()!=null && categoryId.equals(x.getId()))
                .findFirst();

        product.ifPresent(x -> {
            x.setName(productName);
            x.setPrice(Integer.valueOf(price));
            x.setDescription(description);
            x.setStatus(status);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date updatedTime = (Date) simpleDateFormat.parseObject(createdTime);
                x.setCreatedTime(updatedTime);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            x.setCategory(category.get());
            x.setUpdatedTime(new Date());
            x.setActive(color);
        });
        final String requestURL = request.getContextPath() + "/products-servlet";
        response.sendRedirect(requestURL);
    }

    @Override
    public void init() throws ServletException {
        categories = FunctionUtils.categories;
        super.init();
    }
}
