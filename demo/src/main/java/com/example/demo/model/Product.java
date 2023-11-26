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
    private String country;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany
    @JoinTable(name = "event_comments", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "event_tags", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Product() {

    }

    public Product(Long id, String name, String brand, String country, String description, Double price,
            String imageUrl, Set<Tag> tags, Set<Comment> comments) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.country = country;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.tags = tags;
        this.comments = comments;
    }
}