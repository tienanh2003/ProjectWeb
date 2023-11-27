package com.bookstore.controller;

import com.bookstore.dao.OrderDao;
import com.bookstore.modal.Orders;
import com.bookstore.modal.User;
import org.hibernate.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowOrderController", urlPatterns = "/showorder")
public class ShowOrderController extends HttpServlet {
    private final OrderDao orderDao = new OrderDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        System.out.println("UserID: " + userID);

        List<Orders> orders = orderDao.getOrdersByUserID(userID);

        request.setAttribute("orders", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher("info.jsp");
        dispatcher.forward(request, response);
    }
}
