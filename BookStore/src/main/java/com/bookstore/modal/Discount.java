package com.bookstore.modal;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Discount")
public class Discount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discountID")
    private int discountID;

    @Column(name = "discountCode", nullable = false)
    private String discountCode;

    @Column(name = "discountPrice")
    private long discountPrice;


    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(long discountPrice) {
        this.discountPrice = discountPrice;
    }


    public Discount() {
        // Default constructor
    }

    public Discount(String discountCode, long discountPrice) {
        this.discountCode = discountCode;
        this.discountPrice = discountPrice;
    }
}

