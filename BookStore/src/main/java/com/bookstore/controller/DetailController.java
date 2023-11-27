package com.bookstore.controller;

import com.bookstore.dao.ProductDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.modal.Product;
import com.bookstore.modal.Review;
import com.bookstore.modal.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@WebServlet(name = "DetailController", value = {"/detail"})
public class DetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String productIdParam = request.getParameter("productID");

        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdParam);

                // Gọi hàm getProductByID từ ProductDao để lấy thông tin sản phẩm theo ID
                Product product = ProductDao.getProductByID(productId);
                List<Object[]> reviewData = ReviewDao.getReviewAndUsernameByProductID(productId);

                if (product != null) {
                    // Đặt thuộc tính product trong request để sử dụng trong detail.jsp
                    request.setAttribute("product", product);

                    // Xử lý dữ liệu đánh giá trả về từ ReviewDao
                    List<Review> reviews = new ArrayList<>();
                    for (Object[] data : reviewData) {
                        Review review = new Review();
                        review.setReviewID((int) data[0]);
                        // Các giá trị khác tương ứng với reviewDate, reviewStar, reviewDescription, và username
                        review.setReviewDate((Date) data[1]);
                        review.setReviewStar((int) data[2]);
                        review.setReviewDescription((String) data[3]);

                        // Tạo một đối tượng User hoặc chỉ lấy thông tin username tùy thuộc vào cấu trúc dữ liệu của Object[]
                        User user = new User();
                        user.setUsername((String) data[4]);

                        // Đặt đối tượng User vào đối tượng Review
                        review.setUserID(user);

                        reviews.add(review);
                    }

                    // Đặt thuộc tính reviews trong request để sử dụng trong detail.jsp
                    request.setAttribute("reviews", reviews);
                    // Chuyển hướng đến trang detail.jsp
                    RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                }
            } catch (NumberFormatException e) {
                // Xử lý nếu productIdParam không phải là số
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
            }
        } else {
            // Xử lý nếu không có productIdParam
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing product ID parameter");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
