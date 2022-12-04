package com.mvc.project.controller;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.dto.ProductDTO;
import com.mvc.project.repositories.CategoryRepository;
import com.mvc.project.repositories.ProductRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model) {
        final List<ProductDTO> products = productRepository.getProductList();
        model.addAttribute("products", products);

        return "product-page";
    }

    @RequestMapping(value = "/home-page", method = RequestMethod.GET)
    public String homePage(Model model) {
        final List<String> products = Arrays.asList("banana", "apple", "durian", "guava");
        model.addAttribute("products", products);
        model.addAttribute("message", "WELCOME TO PRODUCTS LIST");
        return "home-page";
    }

    @RequestMapping(value = "/product-detail/{productId}", method = RequestMethod.GET)
    public String productDetail(@PathVariable(name = "productId") Integer productId,
                                Model model) {
        ProductDTO productDTO = productRepository.findByProductId(productId);
        model.addAttribute("productDTO", productDTO);
        return "product-detail-page";
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.GET)
    public String createProduct(Model model) {
        List<CategoryDTO> categories = categoryRepository.getAllCategory();
        model.addAttribute("categories", categories);
        return "create-product";
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    public String createProduct(HttpServletRequest request,
                                @ModelAttribute ProductDTO productDTO)
            throws InvocationTargetException, IllegalAccessException {
//        final String name = request.getParameter("name");
//        final Double price = Double.valueOf(request.getParameter("price"));
//        final String description = request.getParameter("description");
//        final boolean status = Boolean.parseBoolean(request.getParameter("status"));
//        final Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));

        productDTO.setCreatedTime(new Timestamp(new Date().getTime()));
        BeanUtils.populate(productDTO, request.getParameterMap());

//        ProductDTO productDTO = new ProductDTO(name, price, description, status, new Timestamp(new Date().getTime()), categoryId);
        productRepository.createProduct(productDTO);

        return "redirect:/products";
    }
}
