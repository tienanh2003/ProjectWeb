package com.bookstore.controller;

import com.bookstore.dao.CartDao;
import com.bookstore.modal.CartItem;
import com.bookstore.modal.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateQuantityController", value = {"/updatequantity"})
public class UpdateQuantityController extends HttpServlet {
    private final CartDao cartDao = new CartDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        // Retrieve parameters from the request
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));
        String action = request.getParameter("action");

        // Retrieve the current quantity from the database
        CartItem cartItem = cartDao.getCartItemById(cartItemID);
        int currentQuantity = cartItem.getQuantity();

        // Update the quantity based on the action
        if ("plus".equals(action)) {
            currentQuantity++;
        } else if ("minus".equals(action) && currentQuantity > 0) {
            currentQuantity--;
        }

        // Update the quantity in the database
        cartItem.setQuantity(currentQuantity);
        cartItem.setTotal(cartItem.getProduct().getProductPrice() * currentQuantity);
        cartDao.updateCartItem(cartItem);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();

        // Get the cart ID for the user
        int cartID = cartDao.getCartIdByUserId(userID);

        // Get the list of CartItem for the user
        List<CartItem> cartItems = cartDao.getAllCartItemsByCartId(cartID);

        // Set the list of CartItem as an attribute in the request
        request.setAttribute("cartItems", cartItems);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}