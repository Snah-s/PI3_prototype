package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Product;
import com.example.demo.model.Tag;

public class ProductDTO {
  private Long id;

  private String name;

  private String brand;

  private String country;

  private Set<Tag> tags;

  private String imageUrl;

  private Double price;

  public Double getprice() {
    return this.price;
  }

  public void setprice(Double price) {
    this.price = price;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImageUrl() {
    return this.imageUrl;
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

  public String getbrand() {
    return this.brand;
  }

  public void setbrand(String brand) {
    this.brand = brand;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Set<Tag> getTags() {
    return this.tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public ProductDTO() {
  }

  public ProductDTO(Product data) {
    this.id = data.getId();
    this.name = data.getName();
    this.brand = data.getBrand();
    this.country = data.getCountry();
    this.tags = data.getTags();
    this.imageUrl = data.getImageUrl();
    this.price = data.getPrice();
  }
}
