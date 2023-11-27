package com.bookstore.dao;

import com.bookstore.config.HibernateConfig;
import com.bookstore.modal.Cart;
import com.bookstore.modal.CartItem;
import com.bookstore.modal.Product;
import com.bookstore.modal.User;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CartDao {

    public Cart getCartByCartID(int cartId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.find(Cart.class, cartId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public CartItem getCartItemById(int cartItemID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.find(CartItem.class, cartItemID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CartItem> getAllCartItemsByCartId(int cartId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Cart cart = session.find(Cart.class, cartId);
            List<CartItem> cartItems = cart.getCartItems();
            transaction.commit();
            return cartItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCartItemByCartId(int quantity, long total, int productId, int cartId) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            // Fetch the existing Cart from the database using cartId
            Cart cart = session.find(Cart.class, cartId);

            // Create a new CartItem and set its properties
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setTotal(total);
            cartItem.setProduct(session.load(Product.class, productId));  // Assuming Product is another entity
            cartItem.setCart(cart);

            // Save the CartItem to the database
            session.save(cartItem);

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }


    public void updateCartItem(CartItem updatedCartItem) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(updatedCartItem);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCartItem(int cartItemId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            CartItem cartItem = session.find(CartItem.class, cartItemId);
            session.remove(cartItem);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cart addUserToCart(User user) {
        int cartId = 0;
        Cart newCart = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Check if the user already has a cart
            cartId = getCartIdByUserId(user.getUserID());

            if (cartId == 0) {
                // If the user does not have a cart, create a new one
                newCart = new Cart(user);
                session.save(newCart);
                cartId = newCart.getCartID(); // Get the newly created cartId
            } else {
                System.out.println("User đã có cartID: " + cartId);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCart;
    }

    public int getCartIdByUserId(int userID) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            System.out.println("Searching for Cart with userID: " + userID);

            int cartID = session.createQuery("SELECT c.cartID FROM Cart c WHERE c.userID.id = :userID", Integer.class)
                    .setParameter("userID", userID)
                    .uniqueResult();

            if (cartID != 0) {
                // Log the CartID
                System.out.println("CartID: " + cartID);
                return cartID;
            } else {
                // Log that Cart with userID was not found
                System.out.println("Cart not found for userID: " + userID);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving Cart by userID: " + e.getMessage());
        }
        return 0;
    }
}
