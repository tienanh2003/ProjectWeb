package com.bookstore.modal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productcategoryID")
    private int productcategoryID;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;

    // Constructors, getters, and setters

    public ProductCategory() {
        // Default constructor
    }

    public ProductCategory(Product product, Category category) {
        this.product = product;
        this.category = category;
    }

    public int getProductcategoryID() {
        return productcategoryID;
    }

    public void setProductcategoryID(int productcategoryID) {
        this.productcategoryID = productcategoryID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}