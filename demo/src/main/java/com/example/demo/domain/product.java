package com.example.demo.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
public class product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    private String image;

    private double price;

    private int quantity;

    private String category;

    private String brand;

    private int rating;

    private int numReviews;

    private boolean availability;

    private Date expiration;

    public product() {
    }

    public product(Long id, String name, String description, String image, double price, int quantity, String category, String brand, int rating, int numReviews, boolean availability, Date expiration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.brand = brand;
        this.rating = rating;
        this.numReviews = numReviews;
        this.availability = availability;
        this.expiration = expiration;
    }

    //getters and setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name=name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description=description;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        if (image == null || image.trim().isEmpty()) {
            throw new IllegalArgumentException("Image cannot be null or empty");
        }
        this.image=image;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price=price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity=quantity;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category){
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        this.category=category;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String brand){
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        this.brand=brand;
    }

    public int getRating(){
        return this.rating;
    }

    public void setRating(int rating){
        if (rating < 0) {
            throw new IllegalArgumentException("Rating cannot be negative");
        }
        this.rating=rating;
    }

    public int getNumReviews(){
        return this.numReviews;
    }

    public void setNumReviews(int numReviews){
        if (numReviews < 0) {
            throw new IllegalArgumentException("Number of reviews cannot be negative");
        }
        this.numReviews=numReviews;
    }

    public boolean getAvailability(){
        return this.availability;
    }

    public void setAvailability(boolean availability){
        if (availability != true && availability != false) {
            throw new IllegalArgumentException("Availability must be a boolean value");
        }
        this.availability=availability;
    }

    public Date getExpiration(){
        return this.expiration;
    }

    public void setExpiration(Date expiration){
        if (expiration == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        this.expiration=expiration;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", price='" + getPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", category='" + getCategory() + "'" +
            ", brand='" + getBrand() + "'" +
            ", rating='" + getRating() + "'" +
            ", numReviews='" + getNumReviews() + "'" +
            ", availability='" + getAvailability() + "'" +
            ", expiration='" + getExpiration() + "'" +
            "}";
    }
}