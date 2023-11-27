package com.bookstore.modal;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Review")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private int reviewID;

    @Column(name = "reviewDate", nullable = false)
    private Date reviewDate;

    @Column(name = "reviewStar", nullable = false)
    private int reviewStar;

    @Column(name = "reviewDescription", columnDefinition = "TEXT")
    private String reviewDescription;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product productID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product product) {
        this.productID = product;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User user) {
        this.userID = user;
    }

    public Review() {
        // Default constructor
    }

    public Review(Date reviewDate, int reviewStar, String reviewDescription, Product product, User user) {
        this.reviewDate = reviewDate;
        this.reviewStar = reviewStar;
        this.reviewDescription = reviewDescription;
        this.productID = product;
        this.userID = user;
    }

}