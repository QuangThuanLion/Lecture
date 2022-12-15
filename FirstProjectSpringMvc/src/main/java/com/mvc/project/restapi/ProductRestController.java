package com.mvc.project.restapi;

import com.mvc.project.dto.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api")
public class ProductRestController {

    @ResponseBody
    @RequestMapping(value = "/create-product/rest-api",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public String createProductRestApi(@RequestBody ProductDTO productDTO){
        System.out.println(productDTO);
        return "Successfully";
    }
}
