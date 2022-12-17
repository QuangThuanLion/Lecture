package com.boot.project.example;

import com.boot.project.entities.Product;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class InPutStudent {
    public static void main(String[] args) {
        Student student;
        String directoryWriteFile = "/Users/admin/Documents/DATA/files/student.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(directoryWriteFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            student = (Student) objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.getAge());
        System.out.println(student.getName());

        List<String> products = new ArrayList<>();
        products.add("abc");
        products.add("bcd");

        InPutStudent inPutStudent = new InPutStudent();
        inPutStudent.convertFunction(products);
        products.forEach(x -> {
            System.out.println(x);
        });

        List<Product> products1 = new ArrayList<>();
        Product product = new Product();
        product.setProductName("abc");
        product.setStatus(false);
        products1.add(product);

        inPutStudent.convertFunction1(products1);
        products1.forEach(x -> {
            System.out.println(x.getProductName());
            System.out.println(x.getStatus());
        });

        String a = "abc";
        String b = "abc";
        String c = new String("abc");

        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a == c);

        String sql = "UPDATE * FROM ";
        StringBuilder stringBuilder = new StringBuilder("UPDATE * FROM ");
        inPutStudent.convertFunction2(sql);
        inPutStudent.convertFunction3(stringBuilder);
        System.out.println(sql);
        System.out.println(stringBuilder);

        System.out.println("--------------");

        Product product1 = new Product();
        product1.setProductName("thuan");
        product1.setStatus(false);
        inPutStudent.convertProduct(product1);

        System.out.println(product1.getProductName());
        System.out.println(product1.getStatus());

//        System.out.println(product2.getProductName());
//        System.out.println(product2.getStatus());
    }

    private Product convertProduct(Product product1) {
//        product1.setProductName(product1.getProductName() + " update");
//        product1.setStatus(true);

        Product product = new Product();
        product.setProductName("thuannguyen");
        product.setStatus(true);

        product1 = product;

        System.out.println("----TAM----");
        System.out.println(product1.getProductName());
        System.out.println(product1.getStatus());
        return product1;
    }

    private void convertFunction3(StringBuilder stringBuilder) {
        stringBuilder.append("TABLE WHERE a = 1");
    }
    private void convertFunction2(String sql) {
        sql.concat("TABLE WHERE a = 1");
    }
    private void convertFunction1(List<Product> products1) {
        for (Product product : products1) {
            product.setProductName(product.getProductName() + " update");
            product.setStatus(true);
        }
    }
    private void convertFunction(List<String> products) {
        for (String product: products) {
            product = product + " update";
        }
    }
}