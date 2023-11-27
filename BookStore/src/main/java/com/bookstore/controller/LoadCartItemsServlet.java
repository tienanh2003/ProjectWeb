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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadCartItemsServlet", value = {"/loadCartItems"})
public class LoadCartItemsServlet extends HttpServlet {
    private final CartDao cartDao = new CartDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        // Retrieve user ID from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();

        // Get the cart ID for the user
        int cartID = cartDao.getCartIdByUserId(userID);

        // Get the list of CartItem for the user
        List<CartItem> cartItems = cartDao.getAllCartItemsByCartId(cartID);

        // Set the list of CartItem as an attribute in the request
        request.setAttribute("cartItems", cartItems);

        // Forward the request to a JSP page for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp"); // Replace "/cartItems.jsp" with the actual path to your JSP page
        dispatcher.forward(request, response);
    }
}