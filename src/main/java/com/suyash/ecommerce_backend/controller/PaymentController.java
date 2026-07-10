package com.suyash.ecommerce_backend.controller;

import com.suyash.ecommerce_backend.entity.Payment;
import com.suyash.ecommerce_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {


    private final PaymentService paymentService;

    // Make Payment
    @PostMapping
    public Payment makePayment(@RequestParam Long userId,
                               @RequestParam Long orderId,
                               @RequestBody Payment payment) {

        return paymentService.makePayment(userId, orderId, payment);
    }

    // Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get Payment By Id
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Update Payment
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id,
                                 @RequestBody Payment payment) {

        return paymentService.updatePayment(id, payment);
    }

    // Delete Payment
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {

        paymentService.deletePayment(id);
        return "Payment Deleted Successfully";
    }
}
