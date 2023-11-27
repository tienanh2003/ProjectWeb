package com.bookstore.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private int cartID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    // Constructors, getters, and setters

    public Cart() {
        // Default constructor
    }
    public Cart(User user) {
        this.userID = user;
    }

    public Cart(User user, List<CartItem> cartItems) {
        this.userID = user;
        this.cartItems = cartItems;
    }


    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUser(User user) {
        this.userID = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
