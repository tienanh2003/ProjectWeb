package com.bookstore.controller;

import com.bookstore.dao.CategoryDao;
import com.bookstore.dao.ProductDao;
import com.bookstore.dao.PublisherDao;
import com.bookstore.modal.Category;
import com.bookstore.modal.Product;
import com.bookstore.modal.Publisher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Category", value = {"/category"})
public class CategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        // Get the categoryID parameter from the request
        String categoryIdParam = request.getParameter("categoryID");

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdParam);

                // Retrieve products based on the category ID using ProductDao
                ProductDao productDao = new ProductDao();
                List<Product> products = productDao.getProductByCategoryID(categoryId);

                // Set the products attribute in the request
                request.setAttribute("products", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("shop.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the case where categoryIdParam is not a valid number
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
            }
        } else {
            // Handle the case where categoryID parameter is missing
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing category ID parameter");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
