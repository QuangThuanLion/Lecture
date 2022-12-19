package com.boot.project.dto;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private UUID id;
    private Double price;
    private String productName;
    private boolean status;
    private String categoryName;
}
