package com.example.EcommerceApplication.dto;

import com.example.EcommerceApplication.model.CATEGORY;
import com.example.EcommerceApplication.model.QuantitySold;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductUserResponse {
    private Long productId;
    private String productName;
    private String description;
    private CATEGORY category;
    private Integer price;
    private Integer quantity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String imagePath;
}
