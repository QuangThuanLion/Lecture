package com.example.storeapplication.servlet;

import com.example.storeapplication.common.FunctionUtils;
import com.example.storeapplication.model.Product;
import com.example.storeapplication.model.ResponseDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", value = "/products-servlet")
public class ProductServlet extends HttpServlet {

    private List<Product> products;

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String requestURL = "/views/products.jsp";
        final List<ResponseDTO> productsResponse = new ArrayList<>();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        mappingToModel(request, response, requestURL, productsResponse, simpleDateFormat, products);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String requestURL = "/views/products.jsp";
        final List<ResponseDTO> productsResponse = new ArrayList<>();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        final String keywords = Optional.ofNullable(request.getParameter("keyword")).orElse(null);
        final List<Product> products = FunctionUtils.products.stream()
                .filter(x -> x.getName() != null || x.getDescription() != null)
                .filter(x -> x.getName().toLowerCase().contains(keywords.toLowerCase())
                        || x.getDescription().toLowerCase().contains(keywords.toLowerCase()))
                .collect(Collectors.toList());

        mappingToModel(request, response, requestURL, productsResponse, simpleDateFormat, products);
    }

    /**
     * @param request
     * @param response
     * @param requestURL
     * @param productsResponse
     * @param simpleDateFormat
     * @param products
     * @throws ServletException
     * @throws IOException
     */
    private void mappingToModel(HttpServletRequest request, HttpServletResponse response,
                                String requestURL, List<ResponseDTO> productsResponse,
                                SimpleDateFormat simpleDateFormat, List<Product> products)
            throws ServletException, IOException
    {
        products.forEach(x -> {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setId(x.getId());
            responseDTO.setName(x.getName());
            responseDTO.setDescription(x.getDescription());
            responseDTO.setPrice(x.getPrice());
            responseDTO.setCreatedTime(simpleDateFormat.format(x.getCreatedTime()));
            responseDTO.setUpdatedTime(simpleDateFormat.format(x.getUpdatedTime()));
            responseDTO.setStatus(x.getStatus() ? "available" : "not available");
            responseDTO.setCategoryName(x.getCategory().getName());
            responseDTO.setActive(x.getActive());

            productsResponse.add(responseDTO);
        });

        request.setAttribute("products", productsResponse);
        RequestDispatcher dispatcher = request.getRequestDispatcher(requestURL);
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        FunctionUtils.listCategories();
        products = FunctionUtils.listProducts();
        super.init();
    }
}
