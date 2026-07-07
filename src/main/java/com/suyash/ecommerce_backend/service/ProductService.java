package com.suyash.ecommerce_backend.service;

import com.suyash.ecommerce_backend.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();


    Product updateProduct(Long id, Product product);

    String deleteProduct(Long id);

    String deleteAllProducts();
}
