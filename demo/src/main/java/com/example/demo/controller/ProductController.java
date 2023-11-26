package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import com.example.demo.dto.ProductDTO;

import com.example.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> read() {
        return productService.read();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        return productService.find(id);
    }

    @GetMapping("/nocomments")
    public ResponseEntity<List<ProductDTO>> readWithoutComments() {
        return productService.readWithoutComments();
    }

    @GetMapping("/nocomments/{searchName}")
    public ResponseEntity<List<ProductDTO>> findWithoutComments(@PathVariable String searchName) {
        return productService.findWithoutComments(searchName);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product data, @PathVariable Long id) {
        return productService.update(data, id);
    }

    @PatchMapping("/{id}/addcomment")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return productService.addComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
