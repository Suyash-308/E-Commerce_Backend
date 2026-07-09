package com.suyash.ecommerce_backend.service.serviceImp;

import com.suyash.ecommerce_backend.entity.Cart;
import com.suyash.ecommerce_backend.repository.CartRepository;
import com.suyash.ecommerce_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartItem(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {

        Cart existing = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));

        existing.setQuantity(cart.getQuantity());

        return cartRepository.save(existing);
    }

    @Override
    public void deleteCart(Long id) {

        cartRepository.deleteById(id);
    }

}
