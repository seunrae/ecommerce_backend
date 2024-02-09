package com.example.EcommerceApplication.dto;

import com.example.EcommerceApplication.model.CATEGORY;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductRequest {
    @NotBlank(message = "name should not be blank")
    private String productName;
    @NotBlank(message = "description should not be blank")
    private String description;
    @NotNull(message = "category should not be blank")
    private String category;
    @NotNull(message = "enter a valid price")
    @Min(0)
    private Integer price;
    @NotNull(message = "Enter a quantity")
    @Min(0)
    @Max(255)
    private Integer quantity;
}
