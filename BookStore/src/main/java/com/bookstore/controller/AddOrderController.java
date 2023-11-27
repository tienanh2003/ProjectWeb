package com.bookstore.controller;

import com.bookstore.config.HibernateConfig;
import com.bookstore.dao.CartDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.PaymentDao;
import com.bookstore.modal.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddOrderController" , urlPatterns = "/addorder")
public class AddOrderController extends HttpServlet {
    private final CartDao cartDao = new CartDao();
    private final OrderDao orderDao = new OrderDao();

    private final PaymentDao paymentDao = new PaymentDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userID = user.getUserID();
        System.out.println("UserID: " + userID);

        int cartID = cartDao.getCartIdByUserId(userID);
        System.out.println("CartID: " + cartID);

        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        System.out.println("PaymentID: " + paymentID);

        int total = Integer.parseInt(request.getParameter("total"));
        System.out.println("Total: " + total);

        try (Session hibernateSession = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = hibernateSession.beginTransaction();

            Cart cart = cartDao.getCartByCartID(cartID);
            Payment payment = paymentDao.getPaymentByPaymentID(paymentID);
            Orders order = new Orders(user, cart, total,payment);
            orderDao.addOrder(order);

            List<CartItem> cartItems = cartDao.getAllCartItemsByCartId(cartID);
            //Duyệt qua từng cartItem và thêm nó vào OrderItem , đồng thời xóa cartItem đi
            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem(cartItem.getQuantity(), cartItem.getTotal(), cartItem.getProduct(), order);
                orderDao.addOrderItem(orderItem);
                cartDao.removeCartItem(cartItem.getCartItemID());
            }
            request.setAttribute("order", order);  // Set the Orders object
            request.setAttribute("orderItems", cartItems);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/showorder.jsp");
        dispatcher.forward(request, response);
    }
}
