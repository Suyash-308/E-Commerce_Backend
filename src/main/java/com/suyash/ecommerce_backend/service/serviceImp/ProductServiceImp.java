package com.suyash.ecommerce_backend.service.serviceImp;

import com.suyash.ecommerce_backend.entity.Product;
import com.suyash.ecommerce_backend.repository.ProductRepository;
import com.suyash.ecommerce_backend.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
        return  productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setImageUrl(product.getImageUrl());

        return productRepository.save(existing);
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.deleteById(id);

        return "Product Deleted Successfully";
    }

    @Override
    public String deleteAllProducts() {
        productRepository.deleteAll();

        return "All Products Deleted Successfully";
    }
}
