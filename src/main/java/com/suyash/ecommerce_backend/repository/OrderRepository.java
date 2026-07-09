package com.suyash.ecommerce_backend.repository;

import com.suyash.ecommerce_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
