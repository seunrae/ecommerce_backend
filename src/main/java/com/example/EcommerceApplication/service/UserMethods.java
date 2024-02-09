package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.ProductUserResponse;
import com.example.EcommerceApplication.dto.UserResponse;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.User;

import java.util.List;

public interface UserMethods {
    UserResponse userToUserResponse(User user);
    List<ProductUserResponse> productListToProductUserResponse(List<Product> products);
    ProductUserResponse productToProductUserResponse(Product product);
    List<UserResponse> userListToUserResponse(List<User> users);
}
