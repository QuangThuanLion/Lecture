package com.mvc.project.anotation.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

//    @GetMapping("/home-page")
    @RequestMapping(value = "/home-page", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        final List<String> products = Arrays.asList("banana", "apple", "orange", "durian", "guava");
        model.addAttribute("products", products);
        model.addAttribute("message", "message hello");
        return "hello";
    }
}
