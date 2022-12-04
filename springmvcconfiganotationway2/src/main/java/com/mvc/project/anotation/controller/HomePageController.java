package com.mvc.project.anotation.controller;

import com.mvc.project.anotation.dto.Product;
import com.mvc.project.anotation.repository.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/home-page")
    public String hello(Model model) {
        final List<String> products = Arrays.asList("banana", "apple", "orange", "durian", "guava");
        model.addAttribute("products", products);
        model.addAttribute("message", "message hello");
        model.addAttribute("greeting", "Hello Spring MVC");
        return "hello";
    }

    @GetMapping("/product")
    public String productList(Model model) {
        List<Product> products = productRepository.getAllListProduct();
        model.addAttribute("products", products);
        return "product";
    }
}
