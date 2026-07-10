package com.suyash.ecommerce_backend.repository;

import com.suyash.ecommerce_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
