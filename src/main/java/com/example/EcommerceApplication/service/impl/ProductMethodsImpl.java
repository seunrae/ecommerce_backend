package com.example.EcommerceApplication.service.impl;

import com.example.EcommerceApplication.dto.ProductResponse;
import com.example.EcommerceApplication.dto.UserProductResponse;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.User;
import com.example.EcommerceApplication.service.ProductMethods;

import java.util.ArrayList;
import java.util.List;

public class ProductMethodsImpl implements ProductMethods {
    @Override
    public ProductResponse productToProductResponse(Product product) {
        ProductResponse productResponse =  new ProductResponse();
        productResponse.setProductId(product.getProductId());
        productResponse.setProductName(product.getProductName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(product.getCategory());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        productResponse.setUsers(userListToUserProductResponseList(product.getUsers()));
        return productResponse;
    }

    @Override
    public List<ProductResponse> productListToproductResponseList(List<Product> products) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        products.forEach(product -> {
            productResponseList.add(productToProductResponse(product));
        });
        return productResponseList;
    }

    @Override
    public List<UserProductResponse> userListToUserProductResponseList(List<User> users) {
        List<UserProductResponse> userProductResponses = new ArrayList<>();

        users.forEach(user -> {
            UserProductResponse userProductResponse = new UserProductResponse();
            userProductResponse.setUserId(user.getUserId());
            userProductResponse.setName(user.getName());
            userProductResponse.setAddress(user.getAddress());
            userProductResponse.setPhone(user.getPhone());
            userProductResponse.setEmail(user.getEmail());
            userProductResponse.setRole(user.getRole());
            userProductResponse.setCreatedAt(user.getCreatedAt());
            userProductResponse.setUpdatedAt(user.getUpdatedAt());
            userProductResponses.add(userProductResponse);
        });
        return userProductResponses;
    }
}
