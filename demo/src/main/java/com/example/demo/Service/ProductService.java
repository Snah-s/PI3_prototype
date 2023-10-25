package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.Product;
import com.example.demo.Repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product product){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
        }
        return optionalProduct;
    }

    public Optional<Product> patchProduct(Long id, Product product){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            if (product.getName() != null){
                existingProduct.setName(product.getName());
            }
            if (product.getDescription() != null){
                existingProduct.setDescription(product.getDescription());
            }
            if (product.getPrice() != null){
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getImage() != null){
                existingProduct.setImage(product.getImage());
            }
            if (product.getBrand() != null){
                existingProduct.setBrand(product.getBrand());
            }
            if (product.getCategory() != null){
                existingProduct.setCategory(product.getCategory());
            }
            productRepository.save(existingProduct);
        }
        return optionalProduct;
    }

    public Optional<Product> deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            // Add logic to delete product from cart
            productRepository.delete(existingProduct);
        }
        return optionalProduct;
    }
}
