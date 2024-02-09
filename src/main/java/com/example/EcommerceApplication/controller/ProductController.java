package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.PasswordRequest;
import com.example.EcommerceApplication.dto.ProductRequest;
import com.example.EcommerceApplication.dto.ProductResponse;
import com.example.EcommerceApplication.exception.PasswordIncorrectException;
import com.example.EcommerceApplication.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/create-product/{userId}")
    public ResponseEntity<?> registerProduct(@PathVariable Long userId, @RequestBody ProductRequest productRequest){
        return productService.registerProduct(userId, productRequest);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return productService.getProducts();
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/delete-product/{userId}/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long userId,@PathVariable Long productId){
        return productService.deleteProduct(userId, productId);
    }

    @GetMapping("/get-product/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/order-product/{userId}/{productId}")
    public ResponseEntity<?> orderProduct(@PathVariable Long userId, @PathVariable Long productId) throws PasswordIncorrectException {
        return productService.orderProduct(userId, productId);
    }

    @GetMapping("/search-name")
    public ResponseEntity<?> searchProductByName(@RequestParam(name = "name") String name){
        return productService.searchProductByName(name);
    }
    @GetMapping("/search-category")
    public ResponseEntity<?> searchProductByCategory(@RequestParam(name = "name") String category) {
        return productService.searchProductByCategory(category);
    }

    @GetMapping("/search-price-range")
    public ResponseEntity<?> searchProductByPriceRange(@RequestParam(name = "lowest") Integer lowest,@RequestParam("highest") Integer highest){
        return productService.searchProductByPriceRange(lowest, highest);
    }
    @GetMapping("/search-field")
    public ResponseEntity<?> searchProductByField(@RequestParam(name = "field") String field){
        return productService.searchProductByField(field);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<?> searchProductWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        return productService.searchProductWithPagination(offset, pageSize);
    }
    @GetMapping("/paginationAndSort/{offset}/{pageSize}")
    public ResponseEntity<?> searchProductWithPaginationAndField(@PathVariable int offset, @PathVariable int pageSize, @RequestParam(name = "field") String field){
        return productService.searchProductWithPaginationAndField(offset, pageSize, field);
    }
}
