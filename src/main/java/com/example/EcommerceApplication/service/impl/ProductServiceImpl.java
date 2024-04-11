package com.example.EcommerceApplication.service.impl;


import com.example.EcommerceApplication.cloudinary.CloudinaryUtils;
import com.example.EcommerceApplication.dto.ProductRequest;
import com.example.EcommerceApplication.dto.ProductResponse;
import com.example.EcommerceApplication.exception.UserNotFoundException;
import com.example.EcommerceApplication.model.*;
import com.example.EcommerceApplication.repository.ProductRepository;
import com.example.EcommerceApplication.repository.UserRepository;
import com.example.EcommerceApplication.service.ProductMethods;
import com.example.EcommerceApplication.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CloudinaryUtils cloudinaryUtils;
    @Override
    public ResponseEntity<?> registerProduct(Long userId, ProductRequest productRequest) {
        ProductMethods productMethods = new ProductMethodsImpl();
        User user =  null;
        List<User> users = new ArrayList<>();
        QuantitySold quantitySold = new QuantitySold();
        try {
            user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
            users.add(user);
            Product product = Product.builder()
                    .productName(productRequest.getProductName())
                    .description(productRequest.getDescription())
                    .category(CATEGORY.valueOf(productRequest.getCategory().toUpperCase()))
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .quantitySold(quantitySold)
                    .users(users)
                    .build();
            quantitySold.setProduct(product);
            productRepository.save(product);

            return new ResponseEntity<>(productMethods.productToProductResponse(product), HttpStatus.CREATED);

        } catch (UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

   private Product getProduct(Product newProduct, Product product) {
      newProduct = Product.builder()
              .productName(product.getProductName())
              .description(product.getDescription())
              .quantity(product.getQuantity())
              .price(product.getPrice())
              .createdAt(LocalDate.now())
              .category(product.getCategory())
              .users(new ArrayList<>())
              .build();

      return newProduct;

   }

    @Override
    public ResponseEntity<?> updateProduct(Long productId, ProductRequest productRequest) {
        ProductMethods productMethods = new ProductMethodsImpl();
        Product product = null;
        try {
            product = productRepository.findById(productId).orElseThrow(()-> new UserNotFoundException("product not found"));
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setCategory(CATEGORY.valueOf(productRequest.getCategory().toUpperCase()));
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            productRepository.save(product);
            return new ResponseEntity<>(productMethods.productToProductResponse(product), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<String> deleteProduct(Long userId, Long productId) {
        User user = null;
        Product product = null;
        try {
            user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
            product = productRepository.findById(productId).orElseThrow(()-> new UserNotFoundException("product not found"));
            product.getUsers().remove(user);
            productRepository.delete(product);
            return new ResponseEntity<>("product deleted", HttpStatus.OK);
        }catch (UserNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProducts() {
        ProductMethods productMethods = new ProductMethodsImpl();
        List<Product> products = productRepository.findAll();
        try {
            return new ResponseEntity<>(productMethods.productListToproductResponseList(products), HttpStatus.OK);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> getProductById(Long productId) {
        ProductMethods productMethods = new ProductMethodsImpl();
        Product product = null;
        try {
            product = productRepository.findById(productId).orElseThrow(()-> new UserNotFoundException("product not found"));
            return new ResponseEntity<>(productMethods.productToProductResponse(product), HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> orderProduct(Long userId, Long productId, Integer quantitySold) {
        ProductMethods productMethods = new ProductMethodsImpl();
        OrderItem orderItem = new OrderItem();
        User user = null;
        Product product = null;
        try {
                user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
                product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("product not found"));
                if (product.getQuantity() > quantitySold){
                    if(product.getQuantity() >= 1) {
                        int newQuantity = product.getQuantity() - quantitySold;
                        product.setQuantity(newQuantity);

                        orderItem.setProduct(product);
                        orderItem.setUser(user);
                        orderItem.setQuantity(quantitySold);

                        user.getOrderItems().add(orderItem);

                        product.getUsers().add(user);

                        productRepository.save(product);
                        return new ResponseEntity<>(productMethods.productToProductResponse(product), HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Product out of stock", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>("Quantity in stock is not sufficient", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> searchProductByName(String name) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try {
            List<Product> products = productRepository.findAll();
            List<Product> searchName = products.stream().filter(p-> p.getProductName().toLowerCase().contains(name.toLowerCase())).toList();
            return new ResponseEntity<>(productMethods.productListToproductResponseList(searchName), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> searchProductByCategory(String category) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try {
            List<Product> products = productRepository.findAll();
            List<Product> searchCategory = products.stream().filter(p-> p.getCategory().name().equals(category)).toList();
            return new ResponseEntity<>(productMethods.productListToproductResponseList(searchCategory), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> searchProductByPriceRange(Integer lowest, Integer highest) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try{
            List<Product> products = productRepository.findAll();
            List<Product> searchPriceRange = products.stream().filter(p-> p.getPrice() >= lowest && p.getPrice() <= highest).toList();
            return new ResponseEntity<>(productMethods.productListToproductResponseList(searchPriceRange), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> searchProductByField(String field) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try {
            List<Product> sortedProducts = productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
            return new ResponseEntity<>(productMethods.productListToproductResponseList(sortedProducts), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> searchProductWithPagination(int offset, int pageSize) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try {
        Page<Product> productPageable = productRepository.findAll(PageRequest.of(offset - 1, pageSize));
        return new ResponseEntity<>(productMethods.productListToproductResponseList(productPageable.toList()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> searchProductWithPaginationAndField(int offset, int pageSize, String field) {
        ProductMethods productMethods = new ProductMethodsImpl();
        try {
            Page<Product> productPageable = productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC,field)));
            return new ResponseEntity<>(productMethods.productListToproductResponseList(productPageable.toList()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> uploadProfilePicture(Long productId, MultipartFile file) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException("product not found"));

            String pictureUrl = cloudinaryUtils.createOrUpdateImage(file);

            if (Objects.isNull(pictureUrl))
                return ResponseEntity.noContent().build();

            product.setImagePath(pictureUrl);
            productRepository.save(product);
            return ResponseEntity.ok(pictureUrl);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
