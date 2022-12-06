package com.mvc.project.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Boolean status;
    private Timestamp createdTime;
    private Integer categoryId;
    private String categoryName;

    public ProductDTO(Integer id, String name, Double price,
                      String description, Boolean status,
                      Timestamp createdTime, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.createdTime = createdTime;
        this.categoryId = categoryId;
    }
}
