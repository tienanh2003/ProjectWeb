package com.bookstore.modal;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitemID")
    private int orderitemID;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total")
    private long total;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderID")
    private Orders order;

    public int getOrderitemID() {
        return orderitemID;
    }

    public void setOrderitemID(int orderitemID) {
        this.orderitemID = orderitemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setBook(Product book) {
        this.product = book;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderItem() {
        // Default constructor
    }

    public OrderItem(int quantity, long total, Product book, Orders order) {
        this.quantity = quantity;
        this.total = total;
        this.product = book;
        this.order = order;
    }

}
