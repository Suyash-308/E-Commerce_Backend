package com.suyash.ecommerce_backend.serviceImp;

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

        Product product1 = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        product1.setImageUrl(product.getImageUrl());

        return productRepository.save(product1);
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
