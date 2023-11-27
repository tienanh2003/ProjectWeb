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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "SearchController", value = {"/search"})
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            String searchProduct = request.getParameter("searchInput");

            // Tiếp tục xử lý tìm kiếm và chuyển đến trang shop.jsp
            List<Product> products = ProductDao.searchProductByName(searchProduct);
            request.setAttribute("products", products);

            RequestDispatcher rq = request.getRequestDispatcher("/shop.jsp");
            rq.forward(request, response);
        } catch (Exception e) {
            // Xử lý bất kỳ ngoại lệ nào xảy ra trong quá trình xử lý tìm kiếm
            e.printStackTrace(); // Hoặc thực hiện xử lý lỗi khác tùy theo yêu cầu
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
