package com.bookstore.controller;

import com.bookstore.config.EmailConfig;
import com.bookstore.dao.*;
import com.bookstore.modal.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", urlPatterns = {"/home","/login","/register","/resetpassword","/sendcode","/verifycode"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String url= request.getRequestURL().toString();
        if (url.contains("register")) {
            getRegister(request, response);
        }else if(url.contains("login")) {
            getLogin(request,response);
        } else if (url.contains("resetpassword")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/resetpassword.jsp");
            dispatcher.forward(request, response);
        }else {
            homePage(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String url=request.getRequestURL().toString();
        if (url.contains("register")) {
            postRegister(request, response);
        }else if(url.contains("login")) {
            postLogin(request, response);
        }
    }
    protected void homePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAllProduct();
        request.setAttribute("products", products);
        request.getSession().setAttribute("products", products);
        // Get publishers
        PublisherDao publisherDao = new PublisherDao();
        List<Publisher> publishers = publisherDao.getAllPublishers();
        request.setAttribute("publishers", publishers);
        request.getSession().setAttribute("publishers", publishers);

        // Get categories
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getAllCategories();
        request.setAttribute("categories", categories);
        request.getSession().setAttribute("categories", categories);

        //Get discount
        DiscountDao discountDao = new DiscountDao();
        List<Discount> discounts = DiscountDao.getAllDiscounts();
        request.setAttribute("discounts", discounts);
        request.getSession().setAttribute("discounts", discounts);

        //Get Payment
        PaymentDao paymentDao=new PaymentDao();
        List<Payment> payments=PaymentDao.getAllPayments();
        request.setAttribute("payments",payments);
        request.getSession().setAttribute("payments", payments);

        RequestDispatcher rq = request.getRequestDispatcher("/home.jsp");
        rq.forward(request, response);
    }

    protected void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    protected void postRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (UserDao.isExistEmail(email)) {
            // Create a new user
            User user = new User(username, password, fullname, email);

            // Insert the new user into the database
            UserDao.insertUser(user);
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            //Tạo session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("fullname", fullname);

            // Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
        else {
            System.out.println("lỗi ");
        }

    }

    protected void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        postLogin(request,response);
    }

    protected void postLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // Perform authentication
        User user = UserDao.Login(username, password);
        if (user != null) {
            // Authentication successful, set session attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("fullname", user.getFullname());
            // Check if the user is an admin
            if (user.isAdmin()) {
                // Redirect to the admin page
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
            } else {
                // Redirect to the home page
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
        } else {
            // Authentication failed, set error message and forward to login page
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }


}
