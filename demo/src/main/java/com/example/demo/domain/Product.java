package com.example.demo.domain;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String image;

    private String brand;

    private String category;

    public Product() {
    }

    public Product(String name, String description, Double price, String image, String brand, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.brand = brand;
        this.category = category;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String productDescription) {
        if (productDescription == null || productDescription.isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be empty");
        }
        this.description = productDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double productPrice) {
        if (productPrice == null || productPrice < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        this.price = productPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String productImage) {
        if (productImage == null || productImage.isEmpty()) {
            throw new IllegalArgumentException("Product image cannot be empty");
        }
        this.image = productImage;
    }

    public String getBrand() {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Product brand cannot be empty");
        }
        return brand;
    }

    public void setBrand(String productBrand) {
        if (productBrand == null || productBrand.isEmpty()) {
            throw new IllegalArgumentException("Product brand cannot be empty");
        }
        this.brand = productBrand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String productCategory) {
        if (productCategory == null || productCategory.isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be empty");
        }
        this.category = productCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + name + '\'' +
                ", productDescription='" + description + '\'' +
                ", productPrice=" + price +
                ", productImage='" + image + '\'' +
                ", productBrand='" + brand + '\'' +
                ", productCategory='" + category + '\'' +
                '}';
    }

    //equals and hashCode

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return this.id.equals(product.id);
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
    
}
