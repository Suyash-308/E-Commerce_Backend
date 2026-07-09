package com.suyash.ecommerce_backend.controller;

import com.suyash.ecommerce_backend.entity.Order;
import com.suyash.ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // Add Order
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    // Get All Orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get Order By Id
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // Update Order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id,
                             @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // Delete Order By Id
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order Deleted Successfully";
    }

    // Delete All Orders
    @DeleteMapping
    public String deleteAllOrders() {
        orderService.deleteAllOrders();
        return "All Orders Deleted Successfully";
    }
}
