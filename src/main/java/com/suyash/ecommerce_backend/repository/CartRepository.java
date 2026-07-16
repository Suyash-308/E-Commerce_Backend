package com.suyash.ecommerce_backend.repository;

import com.suyash.ecommerce_backend.entity.Cart;
import com.suyash.ecommerce_backend.entity.Product;
import com.suyash.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);   // <-- Add this
}