package com.mvc.project.restController;

import com.mvc.project.dto.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/springmvcproject")
@Controller(value = "ProductRestController")
public class ProductRestController {

    @ResponseBody
    @PostMapping(value = "/create-product/rest-api",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createProductRestApi(@RequestBody ProductDTO productDTO){
        System.out.println(productDTO);
        return "Successfully";
    }

}
