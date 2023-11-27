package com.bookstore.modal;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Publisher")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherID")
    private int publisherID;

    @Column(name = "publisherName", nullable = false)
    private String publisherName;

    @Column(name = "publisherDescription", columnDefinition = "TEXT")
    private String publisherDescription;
    @OneToMany(mappedBy = "publisher")
    private List<Product> products;

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherDescription() {
        return publisherDescription;
    }

    public void setPublisherDescription(String publisherDescription) {
        this.publisherDescription = publisherDescription;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Publisher() {
        // Default constructor
    }

    public Publisher(String publisherName, String publisherDescription) {
        this.publisherName = publisherName;
        this.publisherDescription = publisherDescription;
    }
}