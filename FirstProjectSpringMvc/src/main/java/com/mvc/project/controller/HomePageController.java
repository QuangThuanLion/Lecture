package com.mvc.project.controller;

import com.mvc.project.repositories.CategoryRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @param model
     * @return
     */
    @RequestMapping(path = "/home-page", method = RequestMethod.GET)
    public String homePage(Model model) {
        final List<String> products = Arrays.asList("banana", "apple", "durian", "guava");
        model.addAttribute("products", products);
        model.addAttribute("message", "WELCOME TO PRODUCTS LIST");
        return "home-page";
    }

    /**
     * @Desc return default page
     * @return
     */
    @GetMapping(path = "*")
    public String fallBackMethod() {
        return "fallback-page";
    }
}
