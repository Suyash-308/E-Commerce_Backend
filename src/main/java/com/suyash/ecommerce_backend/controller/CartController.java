package com.suyash.ecommerce_backend.controller;

import com.suyash.ecommerce_backend.entity.Cart;
import com.suyash.ecommerce_backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);
    }

    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Cart getById(@PathVariable Long id){
        return cartService.getCartItem(id);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id,
                       @RequestBody Cart cart){

        return cartService.updateCart(id,cart);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){

        cartService.deleteCart(id);

        return "Cart Item Deleted Successfully";
    }
}
