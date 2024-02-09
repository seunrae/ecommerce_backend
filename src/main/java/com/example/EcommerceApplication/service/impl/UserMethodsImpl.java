package com.example.EcommerceApplication.service.impl;

import com.example.EcommerceApplication.dto.ProductUserResponse;
import com.example.EcommerceApplication.dto.UserResponse;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.User;
import com.example.EcommerceApplication.service.UserMethods;

import java.util.ArrayList;
import java.util.List;

public class UserMethodsImpl implements UserMethods {
    @Override
    public UserResponse userToUserResponse(User user) {
        UserResponse userResponse =  new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setName(user.getName());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setRole(user.getRole());
        userResponse.setProductList(productListToProductUserResponse(user.getProducts()));
        return userResponse;
    }

    @Override
    public List<ProductUserResponse> productListToProductUserResponse(List<Product> products) {
        List<ProductUserResponse> productUserResponses = new ArrayList<>();
        for (Product product: products) {
            productUserResponses.add(productToProductUserResponse(product));
        }
        return productUserResponses;
    }

    @Override
    public ProductUserResponse productToProductUserResponse(Product product) {
        ProductUserResponse productUserResponse =  new ProductUserResponse();
        productUserResponse.setProductId(product.getProductId());
        productUserResponse.setProductName(product.getProductName());
        productUserResponse.setDescription(product.getDescription());
        productUserResponse.setCategory(product.getCategory());
        productUserResponse.setPrice(product.getPrice());
        productUserResponse.setQuantity(product.getQuantity());
        productUserResponse.setCreatedAt(product.getCreatedAt());
        productUserResponse.setUpdatedAt(product.getUpdatedAt());
        return productUserResponse;
    }

    @Override
    public List<UserResponse> userListToUserResponse(List<User> users) {
        List<UserResponse> userResponses =  new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse =  new UserResponse();
            userResponse.setUserId(user.getUserId());
            userResponse.setName(user.getName());
            userResponse.setAddress(user.getAddress());
            userResponse.setPhone(user.getPhone());
            userResponse.setEmail(user.getEmail());
            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());
            userResponse.setRole(user.getRole());
            userResponse.setProductList(productListToProductUserResponse(user.getProducts()));
            userResponses.add(userResponse);
        }
        return  userResponses;
    }
}
