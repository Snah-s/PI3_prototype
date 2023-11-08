package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository eventRepository;

    @Secured({ "ADMIN", "USER" })
    @GetMapping
    public ResponseEntity<List<Product>> read() {
        List<Product> query = eventRepository.findAll();
        return new ResponseEntity<List<Product>>(query, HttpStatus.OK);
    }

    @Secured({ "ADMIN", "USER" })
    @GetMapping("/{id}")
    public ResponseEntity<Product> readId(@PathVariable Long id) {
        Optional<Product> query = eventRepository.findById(id);
        if (query.isPresent()) {
            return new ResponseEntity<Product>(query.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Product Event) {
        eventRepository.save(Event);
        return new ResponseEntity<>("Product created :)))", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Product data, @PathVariable Long id) {
        Optional<Product> query = eventRepository.findById(id);
        if (query.isPresent()) {
            Product instance = query.get();
            instance = data;
            eventRepository.save(instance);
            return new ResponseEntity<>("Product updated :D", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found :((", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Product> query = eventRepository.findById(id);
        if (query.isPresent()) {
            eventRepository.deleteById(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found :((", HttpStatus.NOT_FOUND);
        }
    }
}
