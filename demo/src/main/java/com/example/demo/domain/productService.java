package com.example.demo.domain;

import com.example.demo.infrastructure.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class productService {
    @Autowired
    private productRepository productRepository;

    public List<product> getAllProducts(){
        return productRepository.findAll();
    }

    public product saveProduct(product Product){
        return productRepository.save(Product);
    }

    public product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Optional<product> updateProduct(Long id, product Product){
        Optional<product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            product existingProduct = productOptional.get();
            existingProduct.setName(Product.getName());
            existingProduct.setDescription(Product.getDescription());
            existingProduct.setImage(Product.getImage());
            existingProduct.setPrice(Product.getPrice());
            existingProduct.setQuantity(Product.getQuantity());
            existingProduct.setCategory(Product.getCategory());
            existingProduct.setBrand(Product.getBrand());
            existingProduct.setRating(Product.getRating());
            existingProduct.setNumReviews(Product.getNumReviews());
            existingProduct.setAvailability(
            Product.getAvailability());
            existingProduct.setExpiration(Product.getExpiration());
        }
        return productOptional;
    }

    public Optional<product> patchProduct(Long id, product Product){
        Optional<product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            product existingProduct = productOptional.get();
            if (Product.getName() != null) {
                existingProduct.setName(Product.getName());
            }
            if (Product.getDescription() != null) {
                existingProduct.setDescription(Product.getDescription());
            }
            if (Product.getImage() != null) {
                existingProduct.setImage(Product.getImage());
            }
            if (Product.getPrice() > 0) {
                existingProduct.setPrice(Product.getPrice());
            }
            if (Product.getQuantity() > 0) {
                existingProduct.setQuantity(Product.getQuantity());
            }
            if (Product.getCategory() != null) {
                existingProduct.setCategory(Product.getCategory());
            }
            if (Product.getBrand() != null) {
                existingProduct.setBrand(Product.getBrand());
            }
            if (Product.getRating() > 0) {
                existingProduct.setRating(Product.getRating());
            }
            if (Product.getNumReviews() > 0) {
                existingProduct.setNumReviews(Product.getNumReviews());
            }
            if (Product.getExpiration() != null) {
                existingProduct.setExpiration(Product.getExpiration());
            }
            productRepository.save(existingProduct);
        }
        return productOptional;
    }

    public Optional<product> deleteProduct(Long id){
        Optional<product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            productRepository.deleteById(id);
        }
        return productOptional;
    }
}
