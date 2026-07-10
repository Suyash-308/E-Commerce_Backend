package com.suyash.ecommerce_backend.service;

import com.suyash.ecommerce_backend.entity.Payment;

import java.util.List;

public interface PaymentService {

    Payment makePayment(Long userId, Long orderId, Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}
