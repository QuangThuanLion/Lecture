package com.example.session3project.contants;

import com.example.session3project.model.Category;
import com.example.session3project.model.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FunctionUtils {

    public static List<Category> categories;

    public static List<Product> products;

    public static List<Category> listCategories() {
        //declare initial categories
        categories = new ArrayList<>();
        categories.add(new Category(String.valueOf(Math.random()), "Audi", "Audi Description"));
        categories.add(new Category(String.valueOf(Math.random()), "Hyundai", "hyundai Best Sale"));
        categories.add(new Category(String.valueOf(Math.random()), "Mercedes", "mercedes Best Sale"));
        categories.add(new Category(String.valueOf(Math.random()), "Ford", "Ford Best Sale"));

        return categories;
    }

    public static List<Product> listProducts() {
        //declare initial products
        products = new ArrayList<>();
        products.add(new Product(String.valueOf(Math.random()), "Audi 01", 3000000,
                "Audi 01 Description", new Date(), new Date(),true, categories.get(0)));
        products.add(new Product(String.valueOf(Math.random()), "Audi 02", 4000000,

                "Audi 02 Description", new Date(), new Date(), false, categories.get(0)));
        products.add(new Product(String.valueOf(Math.random()), "Hyundai 01", 5000000,

                "Hyundai 01 Description", new Date(), new Date(), true, categories.get(1)));
        products.add(new Product(String.valueOf(Math.random()), "Hyundai 02", 6000000,
                "Hyundai 02 Description", new Date(), new Date(), true, categories.get(1)));

        products.add(new Product(String.valueOf(Math.random()), "Mercedes 01", 4000000,
                "Mercedes 01 Description", new Date(), new Date(), false, categories.get(2)));

        products.add(new Product(String.valueOf(Math.random()), "Mercedes 02", 8000000,
                "Mercedes 02 Description", new Date(), new Date(), true, categories.get(2)));

        products.add(new Product(String.valueOf(Math.random()), "Ford 01", 10000000,
                "Ford 01 Description", new Date(), new Date(), true, categories.get(3)));

        products.add(new Product(String.valueOf(Math.random()), "Ford 02", 4000000,
                "Ford 02 Description", new Date(), new Date(), false, categories.get(3)));

        return products;
    }
}
