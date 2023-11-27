package com.bookstore.modal;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private int paymentID;

    @Column(name = "paymentName", nullable = false)
    private String paymentName;

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Payment() {
        // Default constructor
    }

    public Payment(String paymentName) {
        this.paymentName = paymentName;
    }

}
