package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;

@RestController
@RequestMapping("api/tags")
public class TagController {
  @Autowired
  private TagRepository tagRepository;

  @GetMapping
  public ResponseEntity<List<Tag>> read() {
    List<Tag> query = tagRepository.findAll();
    return new ResponseEntity<List<Tag>>(query, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody Tag tag) {
    tagRepository.save(tag);
    return new ResponseEntity<>("Tag created :)))", HttpStatus.CREATED);
  }
}
