package com.suyash.ecommerce_backend.serviceImp;

import com.suyash.ecommerce_backend.entity.Cart;
import com.suyash.ecommerce_backend.entity.User;
import com.suyash.ecommerce_backend.repository.CartRepository;
import com.suyash.ecommerce_backend.repository.UserRepository;
import com.suyash.ecommerce_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public Cart addToCart(Cart cart) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        cart.setUser(user);

        Optional<Cart> existingCart =
                cartRepository.findByUserAndProduct(user, cart.getProduct());

        if (existingCart.isPresent()) {
            Cart existing = existingCart.get();
            existing.setQuantity(existing.getQuantity() + cart.getQuantity());
            return cartRepository.save(existing);
        }

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartItems() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return cartRepository.findByUser(user);
    }

    @Override
    public Cart getCartItem(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {

        Cart existing = cartRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cart Item Not Found"));

        existing.setQuantity(cart.getQuantity());

        return cartRepository.save(existing);
    }

    @Override
    public void deleteCart(Long id) {

        cartRepository.deleteById(id);
    }

}
