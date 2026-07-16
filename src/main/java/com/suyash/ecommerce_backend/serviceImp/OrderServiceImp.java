package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.entity.Cart;
import com.suyash.ecommerce_backend.entity.Order;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.enumPackage.OrderStatus;
import com.suyash.ecommerce_backend.repository.CartRepository;
import com.suyash.ecommerce_backend.repository.OrderRepository;
import com.suyash.ecommerce_backend.repository.UserRepository;
import com.suyash.ecommerce_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public void placeOrder() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        List<Cart> cartItems = cartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is Empty");
        }

        for (Cart cart : cartItems) {

            Order order = Order.builder()
                    .user(user)
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .totalPrice(cart.getProduct().getPrice() * cart.getQuantity())
                    .status(OrderStatus.PENDING)
                    .orderDate(LocalDateTime.now())
                    .build();

            orderRepository.save(order);
        }

        cartRepository.deleteAll(cartItems);
    }

    @Override
    public List<Order> getMyOrders() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return orderRepository.findByUser(user);
    }
}

