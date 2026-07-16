package com.suyash.ecommerce_backend.controller;

import com.suyash.ecommerce_backend.entity.Order;
import com.suyash.ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public String placeOrder() {
        orderService.placeOrder();
        return "Order Placed Successfully";
    }

    @GetMapping
    public List<Order> getMyOrders() {
        return orderService.getMyOrders();
    }
}
