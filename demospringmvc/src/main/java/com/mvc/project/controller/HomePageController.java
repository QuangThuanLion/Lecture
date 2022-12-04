package com.mvc.project.controller;

import com.mvc.project.dto.StudentDTO;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

//    @GetMapping(value = {"/", "/home-page"})
//    public String hello(Model model) {
//        final List<String> products = Arrays.asList("banana", "apple", "orange", "durian", "guava");
//        model.addAttribute("products", products);
//        return "hello";
//    }
    @GetMapping("/home-page")
    public String hello(Model model) {
        final List<String> products = Arrays.asList("banana", "apple", "orange", "durian", "guava");
        model.addAttribute("products", products);
        return "hello";
    }

    @RequestMapping("/form-submit")
    public ModelAndView formSubmit() {
        ModelAndView modelAndView = new ModelAndView("form-submit");
        modelAndView.addObject("message", "message model and view");
        return modelAndView;
    }

    @RequestMapping(value = "/form-submit", method = RequestMethod.POST)
    public ModelAndView formSubmitPost(@ModelAttribute(value = "studentDTO") StudentDTO studentDTO,
                                       HttpServletRequest request)
            throws InvocationTargetException, IllegalAccessException {
        ModelAndView modelAndView = new ModelAndView("form-submit");

        StudentDTO studentDTOForm = new StudentDTO();
        BeanUtils.populate(studentDTOForm, request.getParameterMap());

        modelAndView.addObject("message", "message model and view post");
        modelAndView.addObject("studentDTO", studentDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/form-submit", method = RequestMethod.DELETE)
    public ModelAndView formSubmitDelete() {
        ModelAndView modelAndView = new ModelAndView("form-submit");
        modelAndView.addObject("message", "message model and view delete");
        return modelAndView;
    }

    @RequestMapping(value = "/form-submit", method = RequestMethod.PUT)
    public ModelAndView formSubmitPut() {
        ModelAndView modelAndView = new ModelAndView("form-submit");
        modelAndView.addObject("message", "message model and view put");
        return modelAndView;
    }

    @GetMapping("/contact-model")
    public String contactModel(Model model) {
        //do some thing
        return "redirect:/contact";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", "hello contact");
        return "contact";
    }
}
