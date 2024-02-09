package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.PasswordRequest;
import com.example.EcommerceApplication.dto.ProductRequest;
import com.example.EcommerceApplication.dto.ProductResponse;
import com.example.EcommerceApplication.exception.PasswordIncorrectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ResponseEntity<?> registerProduct(Long userId, ProductRequest productRequest);
    ResponseEntity<?> updateProduct(Long productId, ProductRequest productRequest);
    ResponseEntity<String> deleteProduct(Long userId, Long productId);
    ResponseEntity<List<ProductResponse>> getProducts();
    ResponseEntity<?> getProductById(Long productId);

    ResponseEntity<?> orderProduct(Long userId, Long productId) throws PasswordIncorrectException;

    ResponseEntity<?> searchProductByName(String name);
    ResponseEntity<?> searchProductByCategory(String category);
    ResponseEntity<?> searchProductByPriceRange(Integer lowest, Integer highest);

    ResponseEntity<?> searchProductByField(String field);

    ResponseEntity<?> searchProductWithPagination(int offset, int pageSize);

    ResponseEntity<?> searchProductWithPaginationAndField(int offset, int pageSize, String field);
    ResponseEntity<String> uploadProfilePicture(Long productId, MultipartFile file);

}
