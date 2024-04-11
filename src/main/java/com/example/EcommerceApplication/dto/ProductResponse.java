package com.example.EcommerceApplication.dto;

import com.example.EcommerceApplication.model.CATEGORY;
import com.example.EcommerceApplication.model.QuantitySold;
import com.example.EcommerceApplication.model.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductResponse {
    private Long productId;
    private String productName;
    private String description;
    private CATEGORY category;
    private Integer price;
    private Integer quantity;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<UserProductResponse> users;
    private String imagePath;
}
