package com.example.demo.application;

import com.example.demo.domain.product;
import com.example.demo.domain.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;


@RestController
@RequestMapping("/product")
public class productController {
    
    @Autowired
    private productService productService;

    @GetMapping
    public ResponseEntity<List<product>> Products() {
        List<product> _Products = productService.getAllProducts();
        return new ResponseEntity<List<product>>(_Products,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<product> createProduct(@RequestBody product _product) {
        productService.saveProduct(_product);
        return new ResponseEntity<product>(_product,HttpStatus.CREATED);
    }

}
