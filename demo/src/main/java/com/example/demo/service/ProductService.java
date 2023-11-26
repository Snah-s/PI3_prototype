package com.example.demo.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CommentRepository commentRepository;

  public ResponseEntity<List<Product>> read() {
    List<Product> query = productRepository.findAll();
    return new ResponseEntity<>(query, HttpStatus.OK);
  }

  public ResponseEntity<Product> find(Long id) {
    Optional<Product> query = productRepository.findById(id);
    if (query.isPresent()) {
      return new ResponseEntity<>(query.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<List<ProductDTO>> readWithoutComments() {
    List<Product> query = productRepository.findAll();
    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<ProductDTO>>() {
    }.getType();
    List<ProductDTO> dtos = modelMapper.map(query, listType);
    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  public ResponseEntity<List<ProductDTO>> findWithoutComments(String searchName) {
    List<Product> query = productRepository.findByNameContainingIgnoreCase(searchName);
    ModelMapper modelMapper = new ModelMapper();
    Type listType = new TypeToken<List<ProductDTO>>() {
    }.getType();
    List<ProductDTO> dtos = modelMapper.map(query, listType);
    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  public ResponseEntity<Product> create(Product product) {
    productRepository.save(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  public ResponseEntity<Product> update(Product data, Long id) {
    Optional<Product> query = productRepository.findById(id);
    if (query.isPresent()) {
      Product instance = query.get();
      instance = data;
      productRepository.save(instance);
      return new ResponseEntity<>(instance, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<String> delete(Long id) {
    Optional<Product> query = productRepository.findById(id);
    if (query.isPresent()) {
      productRepository.deleteById(id);
      return new ResponseEntity<>("Product deleted.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("The product was not found.", HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Comment> addComment(Long id, Comment comment) {
    Optional<Product> query = productRepository.findById(id);
    if (query.isPresent()) {
      Comment savedComment = commentRepository.save(comment);
      Product productInstance = query.get();

      savedComment.setParentProduct(productInstance);
      productInstance.addComment(savedComment);
      commentRepository.save(savedComment);
      productRepository.save(productInstance);
      return new ResponseEntity<>(savedComment, HttpStatus.CREATED);

    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
