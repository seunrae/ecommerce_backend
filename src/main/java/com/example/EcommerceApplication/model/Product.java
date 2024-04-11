package com.example.EcommerceApplication.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product_table")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private CATEGORY category;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private QuantitySold quantitySold;

    private String imagePath;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;


}
