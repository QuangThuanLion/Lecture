package com.mvc.project.controller;

import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller(value = "HomePageController")
public class HomePageController {

    private Logger LOGGER = Logger.getLogger(HomePageController.class);
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
    @RequestMapping(path = "/**", method = RequestMethod.GET)
    public String fallBackMethod() {
        LOGGER.info("FALLBACK-METHOD");
        return "errors/fallback-page";
    }

    @ResponseBody
    @RequestMapping(value = "/home/language")
    public void language() {
        System.out.println("Home language lang");
    }
}
