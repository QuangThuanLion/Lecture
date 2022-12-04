package com.mvc.project.anotation.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home-page")
    public String hello(ModelMap model) {
        final List<String> products = Arrays.asList("banana", "apple", "orange", "durian", "guava");
        model.addAttribute("products", products);
        model.addAttribute("message", "message hello");
        return "hello";
    }
}
