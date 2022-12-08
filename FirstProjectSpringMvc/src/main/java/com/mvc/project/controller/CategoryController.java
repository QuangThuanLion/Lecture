package com.mvc.project.controller;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @param model
     * @return
     */
    @GetMapping(path = "/categories")
    public String categories(Model model) {
        List<CategoryDTO> categories = categoryRepository.getAllCategory();
        model.addAttribute("categories", categories);
        return "categories/category-page";
    }

    @RequestMapping(path = "/create-category", method = RequestMethod.GET)
    public String createCategory(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categories/category-create";
    }

    @RequestMapping(path = "/create-category", method = RequestMethod.POST)
    public String createCategory(
            @Valid @ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "categories/category-create";
        }

        categoryRepository.createCategory(categoryDTO);
        AtomicReference<String> message = new AtomicReference<>("Create Category failed");
        AtomicReference<String> alert = new AtomicReference<>("danger");
        Optional.ofNullable(categoryDTO).ifPresent(x -> {
            message.set("Create category successfully");
            alert.set("success");
        });

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("alert", alert);

        return "redirect:/categories";
    }

    @RequestMapping(path = "/category/{categoryId}/update", method = RequestMethod.GET)
    public String updateCategory(@PathVariable(name = "categoryId") Integer categoryId,
                                 Model model) {
        CategoryDTO categoryDTO = categoryRepository.findCategoryById(categoryId);
        model.addAttribute("categoryDTO", categoryDTO);
        return "categories/category-update";
    }

    @RequestMapping(path = "/update-category", method = RequestMethod.POST)
    public String updateCategory(
            @Valid @ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes)
    {
        if (bindingResult.hasErrors())
        {
            return "categories/category-update";
        }

        try {
            Optional.ofNullable(categoryDTO.getId()).ifPresent(x -> {
                categoryRepository.updateCategory(categoryDTO);
            });

            AtomicReference<String> message = new AtomicReference<>("Update category failed");
            AtomicReference<String> alert = new AtomicReference<>("failed");

            Optional.ofNullable(categoryDTO).ifPresent(x -> {
                message.set("Update category successfully");
                alert.set("success");
            });

            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("alert", alert);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/categories";
    }
}