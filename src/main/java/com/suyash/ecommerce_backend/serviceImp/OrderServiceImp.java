package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.entity.Order;
import com.suyash.ecommerce_backend.enumPackage.OrderStatus;
import com.suyash.ecommerce_backend.repository.OrderRepository;
import com.suyash.ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(Order order) {

        // Automatically set order date
        order.setOrderDate(LocalDateTime.now());

        // Default status
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateOrder(Long id, Order order) {

        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setUser(order.getUser());
        existingOrder.setProduct(order.getProduct());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setStatus(order.getStatus());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
