package com.mvc.project.controller;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.repositories.CategoryRepository;
import com.mvc.project.services.CategoryServices;
import com.mvc.project.validator.CategoryValidator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller(value = "CategoryController")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryValidator categoryValidator;

    @Autowired
    private CategoryServices categoryServices;

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

    /**
     * @param model
     * @return
     */
    @RequestMapping(path = "/create-category", method = RequestMethod.GET)
    public String createCategory(Model model) {
        model.addAttribute("categoryDTO", new CategoryDTO());
        return "categories/category-create";
    }

    /**
     * @param multipartFile
     * @param categoryDTO
     * @param bindingResult
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(path = "/create-category", method = RequestMethod.POST)
    public String createCategory(
            @RequestParam(name = "category_image") MultipartFile multipartFile,
            @ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request){
        categoryValidator.validate(categoryDTO, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "categories/category-create";
        }
        Optional.ofNullable(multipartFile).ifPresentOrElse(
                x -> saveUploadImages(categoryDTO, multipartFile, request),
                () -> categoryDTO.setThumbnail("default_category.png"));

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

    /**
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(path = "/category/{categoryId}/update", method = RequestMethod.GET)
    public String updateCategory(@PathVariable(name = "categoryId") Integer categoryId,
                                 Model model) {
        CategoryDTO categoryDTO = categoryRepository.findCategoryById(categoryId);
        model.addAttribute("categoryDTO", categoryDTO);
        return "categories/category-update";
    }

    /**
     * @param categoryDTO
     * @param bindingResult
     * @param redirectAttributes
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping(path = "/update-category", method = RequestMethod.POST)
    public String updateCategory(
            @ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "category_image") MultipartFile multipartFile,
            HttpServletRequest request)
    {
        categoryValidator.validate(categoryDTO, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "categories/category-update";
        }

        Optional.ofNullable(multipartFile).ifPresentOrElse(
                x -> saveUploadImages(categoryDTO, multipartFile, request),
                () -> categoryDTO.setThumbnail(categoryDTO.getThumbnail()));
        try {
            Optional.ofNullable(categoryDTO.getId()).ifPresent(x -> categoryRepository.updateCategory(categoryDTO));

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

    /**
     * @param categoryDTO
     * @param multipartFile
     * @param request
     */
    private void saveUploadImages(@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
                                  @RequestParam(name = "category_image") MultipartFile multipartFile,
                                  HttpServletRequest request) {
        String directory = "/Users/admin/Documents/DATA";
        File file = new File(directory);

        if (!file.exists()) {
            file.mkdirs();
        }

        Path path = Paths.get(directory + File.separator + multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path.toString());
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        String rootDirectory = categoryServices.baseUploadImages(request);
//        File directory = new File(rootDirectory);
//        try {
//            if (!directory.exists()) directory.mkdirs();
//            final String filename = StringUtils
//                    .cleanPath(multipartFile.getOriginalFilename())
//                    .replace(org.apache.commons.lang3.StringUtils.SPACE, MappingUtils.HYPHEN_CONSTANT);
//            categoryDTO.setThumbnail(filename);
//            Path path = Paths.get(rootDirectory + File.separator + filename);
//            FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
//            fileOutputStream.write(multipartFile.getBytes());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * @param thumbnail
     * @param request
     * @param response
     */
    @RequestMapping(path = "/category/{thumbnail}/download-file", method = RequestMethod.GET)
    public void downloadCategoryImage(@PathVariable(name = "thumbnail") String thumbnail,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
    {
        String dataDirectory = categoryServices.baseUploadImages(request);
        Path file = Paths.get(dataDirectory + File.separator, thumbnail);
        if (Files.exists(file)) {
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "attachment; filename=" + thumbnail);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}