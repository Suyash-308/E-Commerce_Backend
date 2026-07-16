package com.suyash.ecommerce_backend.service;

import com.suyash.ecommerce_backend.entity.Order;

import java.util.List;

public interface OrderService {
    void placeOrder();

    List<Order> getMyOrders();
}
