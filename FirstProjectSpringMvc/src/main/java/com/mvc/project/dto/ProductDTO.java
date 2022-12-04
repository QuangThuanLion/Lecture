package com.mvc.project.dto;

import java.sql.Timestamp;

public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Boolean status;
    private Timestamp createdTime;
    private Integer categoryId;

    public ProductDTO() {
    }

    public ProductDTO(String name, Double price, String description, Boolean status, Timestamp createdTime, Integer categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
