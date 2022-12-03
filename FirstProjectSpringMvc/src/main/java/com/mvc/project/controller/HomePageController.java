package com.mvc.project.controller;

import com.mvc.project.dto.ProductDTO;
import com.mvc.project.repositories.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
    @Autowired
    private ProductRepository productRepository;

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
}
