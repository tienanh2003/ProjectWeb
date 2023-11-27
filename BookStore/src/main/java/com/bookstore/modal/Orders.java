package com.bookstore.modal;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "totalPrice")
    private long totalPrice;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;

    @ManyToOne
    @JoinColumn(name = "discountID")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "paymentID")
    private Payment paymentID;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Orders(User user, Cart cart,int totalPrice,Payment payment) {
        this.orderDate = new Date(System.currentTimeMillis()); // set the current date
        this.status = "Đang xử lý";
        this.userID = user;
        this.totalPrice = totalPrice ;
        this.paymentID =payment;
    }

    public Orders() {

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return userID;
    }

    public void setUser(User user) {
        this.userID = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Payment getPayment() {
        return paymentID;
    }

    public void setPayment(Payment payment) {
        this.paymentID = payment;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}