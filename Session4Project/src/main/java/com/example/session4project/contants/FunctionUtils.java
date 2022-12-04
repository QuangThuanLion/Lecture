package com.example.session4project.contants;

import java.util.ArrayList;
import java.util.List;

public class FunctionUtils {
    public static List<String> products;

    public static List<String> productList() {
        products = new ArrayList<>();

        products.add("Banana");
        products.add("Apple");
        products.add("Cucumber");
        products.add("Orange");
        products.add("Water melon");

        return products;
    }

    public static void updateProductString(List<String> productString) {
//        productString.set(productString.indexOf("apple"), "apple update");
//        productString.set(productString.indexOf("banana"), "banana update");
        List<String> updates = new ArrayList<>();
        for (String item : productString) {

            item = item + " update";
            updates.add(item);
        }
    }

    public static void updateProducts(List<Product> products) {
        for (Product product : products) {
            product.setName(product.getName() + " update");
            product.setDescriptions(product.getDescriptions() + " update");
        }
    }

    public static List<Product> updateProductsNew(List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            product.setName(product.getName() + " update");
            product.setDescriptions(product.getDescriptions() + " update");

            result.add(product);
        }

        return result;
    }
}
