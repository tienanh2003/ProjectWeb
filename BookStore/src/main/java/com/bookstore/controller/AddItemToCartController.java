package com.bookstore.controller;

import com.bookstore.dao.CartDao;
import com.bookstore.dao.ProductDao;
import com.bookstore.modal.CartItem;
import com.bookstore.modal.Product;
import com.bookstore.modal.User;
import com.bookstore.modal.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddItemToCartController", value = {"/addtocart"})
public class AddItemToCartController extends HttpServlet {
    private final CartDao cartDao = new CartDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // Step 1: Retrieve user ID from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // Redirect to login page
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int userID = user.getUserID();

        // Step 3: Check if User has a Cart
        int cartID = cartDao.getCartIdByUserId(userID);

        try {
            System.out.println("cartId:"+cartID);
            if (cartID == 0) {
                // Use the modified addUserToCart method to get the new cart
                Cart cart = cartDao.addUserToCart(user);
                cartID = cart.getCartID(); // Get the newly created cartId
                System.out.println("Newly created cartId: " + cartID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Step 4: Retrieve Product Information
        int productId = Integer.parseInt(request.getParameter("productID"));
        Product product = ProductDao.getProductByID(productId);

        // Step 5: Check if Product is in the Cart
        List<CartItem> cartItems = cartDao.getAllCartItemsByCartId(cartID);
        boolean productExists = false;

        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductID() == productId) {
                // Product is already in the cart, update quantity and total
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setTotal(cartItem.getProduct().getProductPrice() * cartItem.getQuantity());
                cartDao.updateCartItem(cartItem);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            // Product is not in the cart, add a new CartItem
            CartItem newCartItem = new CartItem(1, product.getProductPrice(), productId, cartID);

            System.out.println("Adding new CartItem:");
            System.out.println("Quantity: " + newCartItem.getQuantity());
            System.out.println("Total: " + newCartItem.getTotal());

            cartDao.addCartItemByCartId(1, product.getProductPrice(), productId, cartID);
        }
        // Step 6: Gọi đến 1 servlet để load List<cartItem của user
        RequestDispatcher dispatcher = request.getRequestDispatcher("/loadCartItems"); // Replace "/loadCartItems" with the actual URL mapping of your servlet
        dispatcher.forward(request, response);
    }

}
