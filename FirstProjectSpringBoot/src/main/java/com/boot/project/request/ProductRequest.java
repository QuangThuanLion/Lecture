package com.boot.project.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import org.hibernate.validator.constraints.NotEmpty;

public class ProductRequest {

    @NotNull
    @NotEmpty(message = "Name cannot empty")
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    @NotEmpty(message = "price cannot empty")
    private Double price;

    @NotNull
    @NotEmpty(message = "status cannot empty")
    private boolean status;

    @NotNull
    @NotEmpty(message = "categoryId cannot empty")
    private UUID categoryId;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
