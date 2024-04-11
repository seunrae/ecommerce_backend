package com.example.EcommerceApplication.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class QuantitySold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantityId;

    private int quantitySold;

    @OneToOne
    @JoinColumn(name = "productId" )
    private Product product;
}
