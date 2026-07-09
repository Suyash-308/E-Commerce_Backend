package com.suyash.ecommerce_backend.repository;

import com.suyash.ecommerce_backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
