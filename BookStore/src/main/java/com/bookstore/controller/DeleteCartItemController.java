package com.bookstore.controller;
import com.bookstore.dao.CartDao;
import com.bookstore.dao.DiscountDao;
import com.bookstore.dao.PaymentDao;
import com.bookstore.modal.CartItem;
import com.bookstore.modal.Discount;
import com.bookstore.modal.Payment;
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
@WebServlet(name = "DeleteCartItemController", value = {"/deleteCartItem"})
public class DeleteCartItemController extends HttpServlet {
    private final CartDao cartDao = new CartDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // Retrieve the cartItemID from the request parameters
        int cartItemID = Integer.parseInt(request.getParameter("cartItemID"));

        // Remove the CartItem from the database
        cartDao.removeCartItem(cartItemID);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();

        // Get the cart ID for the user
        int cartID = cartDao.getCartIdByUserId(userID);

        // Get the list of CartItem for the user
        List<CartItem> cartItems = cartDao.getAllCartItemsByCartId(cartID);

        // Set the list of CartItem as an attribute in the request
        request.setAttribute("cartItems", cartItems);

        // Redirect back to the cart.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}