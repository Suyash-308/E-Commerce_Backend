package com.suyash.ecommerce_backend.service;

import com.suyash.ecommerce_backend.entity.Cart;

import java.util.List;

public interface CartService {

    Cart addToCart(Cart cart);

    List<Cart> getAllCartItems();

    Cart getCartItem(Long id);

    Cart updateCart(Long id, Cart cart);

    void deleteCart(Long id);

}
