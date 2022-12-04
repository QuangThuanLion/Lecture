package com.example.session4project.contants;

import java.util.Arrays;
import java.util.List;

public class application {

    public static void main(String[] args) {
        List<String> productString = Arrays.asList("apple", "banana");
        List<Product> products = Arrays.asList(
                new Product("car", "car description"),
                new Product("honda", "honda description"));

        FunctionUtils.updateProductString(productString);
        FunctionUtils.updateProducts(products);

        List<Product> products1 = FunctionUtils.updateProductsNew(products);

        for (String item : productString) {
            System.out.println(item);
        }

        for (Product pro : products) {
            System.out.println(pro.toString());
        }
    }
}
