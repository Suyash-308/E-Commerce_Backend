package com.suyash.ecommerce_backend.entity;

import com.suyash.ecommerce_backend.enumPackage.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which user placed the order
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Product ordered
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Quantity ordered
    private Integer quantity;

    // Total amount
    private Double totalPrice;

    // Order status
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Order date
    private LocalDateTime orderDate;

}
