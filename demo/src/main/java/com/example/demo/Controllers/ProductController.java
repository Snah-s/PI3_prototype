package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Domain.Product;
import com.example.demo.Service.ProductService;

import java.util.*;


@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> product(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> products(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> updatedProduct = productService.updateProduct(id, product);
        return updatedProduct.isPresent() ? ResponseEntity.status(200).body("Updated") : ResponseEntity.status(404).body("Not Found");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> patchedProduct = productService.patchProduct(id, product);
        return patchedProduct.isPresent() ? ResponseEntity.status(200).body("Patched") : ResponseEntity.status(404).body("Not Found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Optional<Product> deletedProduct = productService.deleteProduct(id);
        return deletedProduct.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }
}