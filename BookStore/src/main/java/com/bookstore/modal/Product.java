package com.bookstore.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private int productID;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productAuthor")
    private String productAuthor;

    @Column(name = "productImage")
    private String productImage;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productPrice")
    private long productPrice;

    @Column(name = "productDatePublic")
    private Date productDatePublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisherID")
    private Publisher publisher;

    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories;

    // Constructors, getters, and setters

    public Product() {
        // Default constructor
    }

    public Product(String productName, String productAuthor, String productImage, String productDescription,
                   long productPrice, Date productDatePublic, Publisher publisher, List<ProductCategory> productCategories) {
        this.productName = productName;
        this.productAuthor = productAuthor;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDatePublic = productDatePublic;
        this.publisher = publisher;
        this.productCategories = productCategories;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAuthor() {
        return productAuthor;
    }

    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public Date getProductDatePublic() {
        return productDatePublic;
    }

    public void setProductDatePublic(Date productDatePublic) {
        this.productDatePublic = productDatePublic;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
}
