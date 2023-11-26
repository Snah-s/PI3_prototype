package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Product> assignedProducts;

    public Set<Product> getAssignedProducts() {
        return this.assignedProducts;
    }

    public void setAssignedProducts(Set<Product> assignedProducts) {
        this.assignedProducts = assignedProducts;
    }

    public Tag() {
    }

    public Tag(String name, String color, Set<Product> assignedProducts) {
        this.name = name;
        this.color = color;
        this.assignedProducts = assignedProducts;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
