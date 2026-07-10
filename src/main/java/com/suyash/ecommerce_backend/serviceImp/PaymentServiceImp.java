package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.entity.Order;
import com.suyash.ecommerce_backend.entity.Payment;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.repository.OrderRepository;
import com.suyash.ecommerce_backend.repository.PaymentRepository;
import com.suyash.ecommerce_backend.repository.UserRepository;
import com.suyash.ecommerce_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payment makePayment(Long userId, Long orderId, Payment payment) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found"));

        payment.setUser(user);
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {


        System.out.println("Update Payment API Called");

        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment Not Found"));

        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setPaymentStatus(payment.getPaymentStatus());

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
