package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String type;

    @ManyToMany
    private Set<Comment> comments;

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product() {

    }

    public Product(Long id, String name, String brand, String type) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
    }
}
