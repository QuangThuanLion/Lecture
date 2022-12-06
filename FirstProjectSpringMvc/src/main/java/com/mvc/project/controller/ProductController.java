package com.mvc.project.controller;

import com.mvc.project.dto.CategoryDTO;
import com.mvc.project.dto.ProductDTO;
import com.mvc.project.repositories.CategoryRepository;
import com.mvc.project.repositories.ProductRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @param model
     * @return
     */
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String products(Model model) {
        final List<ProductDTO> products = productRepository.getProductList();
        productMappingModel(products);

        model.addAttribute("products", products);
        return "product-page";
    }

    /**
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(path = "/product-detail/{productId}", method = RequestMethod.GET)
    public String productDetail(@PathVariable(name = "productId") Integer productId,
                                Model model) {
        ProductDTO productDTO = productRepository.findByProductId(productId);
        model.addAttribute("productDTO", productDTO);
        return "product-detail-page";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(path = "/create-product", method = RequestMethod.GET)
    public String createProduct(Model model) {
        List<CategoryDTO> categories = categoryRepository.getAllCategory();
        model.addAttribute("categories", categories);
        return "create-product";
    }

    /**
     * @param request
     * @param redirectAttributes
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/create-product", method = RequestMethod.POST)
    public String createProduct(HttpServletRequest request,
                                RedirectAttributes redirectAttributes)
            throws InvocationTargetException, IllegalAccessException
    {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCreatedTime(new Timestamp(new Date().getTime()));
        BeanUtils.populate(productDTO, request.getParameterMap());

        AtomicReference<String> message = new AtomicReference<>("Create Product failed");
        AtomicReference<String> alert = new AtomicReference<>("danger");
        Optional.ofNullable(productDTO).ifPresent(x -> {
            message.set("Create product successfully");
            alert.set("success");;
        });

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("alert", alert);
        productRepository.createProduct(productDTO);

        //List<ProductDTO> productDTOS = getProductDTOS();
        //productRepository.createProductWay3(productDTO);
        //productRepository.createProductWay4(productDTO);
        //productRepository.batchInsertProducts(productDTOS);
        //productRepository.batchInsertSplitFile(productDTOS, 1);
        //productRepository.batchInsertProductWithNamedParameter(productDTOS);
        //productRepository.batchInsertProductWithNamedParameterWay1(productDTOS);
        return "redirect:/products";
    }

    /**
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(value = "/product/{productId}/update", method = RequestMethod.GET)
    public String updateProduct(@PathVariable(name = "productId") Integer productId,
                                Model model) {
        ProductDTO productDTO = productRepository.findByProductId(productId);
        List<CategoryDTO> categories = categoryRepository.getAllCategory();

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createdTime = simpleDateFormat.format(productDTO.getCreatedTime());

        model.addAttribute("categories", categories);
        model.addAttribute("product", productDTO);
        model.addAttribute("createdTime", createdTime);
        return "update-product";
    }

    /**
     * @param request
     * @param redirectAttributes
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/update-product", method = RequestMethod.POST)
    public String updateProduct(HttpServletRequest request,
                                RedirectAttributes redirectAttributes)
            throws InvocationTargetException, IllegalAccessException {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.populate(productDTO, request.getParameterMap());

        String createdTime = Optional.ofNullable(request.getParameter("createdTimeFormat")).orElse(null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
           Date date =  (Date) simpleDateFormat.parseObject(createdTime);
           productDTO.setCreatedTime(new Timestamp(date.getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        AtomicReference<String> message = new AtomicReference<>("Update product failed");
        AtomicReference<String> alert = new AtomicReference<>("failed");

        Optional.ofNullable(productDTO).ifPresent(x -> {
            message.set("Update product successfully");
            alert.set("success");
        });

        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("alert", alert);
        productRepository.updateProductById(productDTO);
        return "redirect:/products";
    }

    /**
     * @param productId
     * @param redirectAttributes
     * @return
     */
    @GetMapping(path = "/delete-product")
    public String deleteProduct(@RequestParam(name = "product_id") Integer productId,
                                RedirectAttributes redirectAttributes){
        AtomicReference<String> message = new AtomicReference<>("ProductId invalid, system was wrong");
        AtomicReference<String> alert = new AtomicReference<>("danger");
        Optional.ofNullable(productId).ifPresent(x -> {
            message.set("Delete product successfully");
            alert.set("success");
        });

        redirectAttributes.addFlashAttribute("message", message.get().toString());
        redirectAttributes.addFlashAttribute("alert", alert.get().toString());
        productRepository.deleteProductById(productId);
        return "redirect:/products";
    }

    /**
     * @param keyword
     * @param model
     * @return
     */
    @PostMapping(path = "/products-search")
    public String searchByKeyword(@RequestParam(name = "keyword") String keyword,
                                  Model model) {
        AtomicReference<String> message = new AtomicReference<>("Keyword not invalid");
        AtomicReference<String> alert = new AtomicReference<>("danger");

        Optional.ofNullable(keyword).ifPresent(x -> {
            message.set("This is list by keyword");
            alert.set("success");
        });

        final List<ProductDTO> products = productRepository.searchByKeyword(keyword);
        productMappingModel(products);

        model.addAttribute("products", products);
        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return "product-page";
    }

    /**
     * @Desc search by keyword
     * @return
     */
    @GetMapping(path = "/products-search")
    public String searchByKeyword() {
        return "redirect:/products";
    }

    /**
     * @param products
     * @return
     */
    private List<ProductDTO> productMappingModel(List<ProductDTO> products) {
        final List<CategoryDTO> categories = categoryRepository.getAllCategory();
        for (ProductDTO productDTO : products) {
            Integer categoryId = productDTO.getCategoryId();
            String categoryName = categories.stream()
                    .filter(x -> x.getId().equals(categoryId))
                    .map(CategoryDTO::getName)
                    .findFirst().get();
            productDTO.setCategoryName(categoryName);
        }
        return products;
    }

    /**
     * @Desc data fake products
     * @return
     */
    private static List<ProductDTO> getProductDTOS() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(ProductDTO.builder()
                .name("product_1")
                .price((double)1234)
                .description("product_1_description")
                .status(false)
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .categoryId(1)
                .build());
        productDTOS.add(ProductDTO.builder()
                .name("product_2")
                .price((double)56678)
                .description("product_2_description")
                .status(true)
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .categoryId(2)
                .build());
        productDTOS.add(ProductDTO.builder()
                .name("product_3")
                .price((double)56678)
                .description("product_4_description")
                .status(false)
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .categoryId(3)
                .build());
        return productDTOS;
    }
}
