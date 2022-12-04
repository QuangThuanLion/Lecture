package com.example.session4project.contants;

public class Product {

    private String name;
    private String descriptions;

    public Product(String name, String descriptions) {
        this.name = name;
        this.descriptions = descriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
