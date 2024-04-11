package com.example.EcommerceApplication.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderResponse {
    private Long orderId;

    private ProductUserResponse product;

    private int quantity;

    private UUID referenceNumber;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
