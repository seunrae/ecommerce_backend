package com.example.EcommerceApplication.dto;

import com.example.EcommerceApplication.model.OrderItem;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.ROLE;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserResponse {
    private Long userId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private ROLE role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<ProductUserResponse> productList;
    private List<OrderResponse> orderItems;
}
