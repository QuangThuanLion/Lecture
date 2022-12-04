package com.example.kafkaexample;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NAME")
    private String name;

    @JsonProperty("PRICE")
    private double price;
    @JsonProperty("DESCRIPTION")
    private String description;

    @JsonProperty("STATUS")
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
