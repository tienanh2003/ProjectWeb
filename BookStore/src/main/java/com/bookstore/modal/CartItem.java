package com.bookstore.modal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CartItem")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartitemID")
    private int cartItemID;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total", nullable = false)
    private long total;

    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartID")
    private Cart cart;

    // Constructors, getters, and setters

    public CartItem() {
        // Default constructor
    }

    public CartItem(int quantity, long total, Product product, Cart cart) {
        this.quantity = quantity;
        this.total = total;
        this.product = product;
        this.cart = cart;
    }
    public CartItem(int quantity, long total, int productID, int cartID) {
        this.quantity = quantity;
        this.total = total;

        Product product = new Product();
        product.setProductID(productID);
        this.product = product;

        // Tạo một đối tượng Cart từ cartID
        Cart cart = new Cart();
        cart.setCartID(cartID);
        this.cart = cart;
    }

    public int getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
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

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
