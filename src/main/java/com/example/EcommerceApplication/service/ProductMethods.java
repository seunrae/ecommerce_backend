package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.ProductResponse;
import com.example.EcommerceApplication.dto.UserProductResponse;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.User;

import java.util.List;

public interface ProductMethods {
    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productListToproductResponseList (List<Product> products);
    List<UserProductResponse> userListToUserProductResponseList(List<User> users);
}
