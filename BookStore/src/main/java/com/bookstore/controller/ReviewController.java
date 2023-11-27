package com.bookstore.controller;

import com.bookstore.dao.ProductDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.modal.Product;
import com.bookstore.modal.Review;
import com.bookstore.modal.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ReviewController",urlPatterns = "/review")
public class ReviewController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try {
            // Lấy dữ liệu từ request
            int reviewStar = Integer.parseInt(request.getParameter("reviewStar"));
            String reviewDescription = request.getParameter("description");
            int productID = Integer.parseInt(request.getParameter("productID"));

            // Lấy thông tin người dùng từ session
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                // Kiểm tra xem sản phẩm có tồn tại không
                Product product = ProductDao.getProductByID(productID);

                if (product != null) {
                    // Tạo đối tượng Review
                    Review review = new Review();
                    Date currentDate = new java.util.Date();
                    review.setReviewDate(new java.sql.Date(currentDate.getTime()));
                    review.setReviewStar(reviewStar);
                    review.setReviewDescription(reviewDescription);

                    review.setProductID(product);
                    review.setUserID(user);

                    // Print data for debugging
                    System.out.println("Review Date: " + currentDate);
                    System.out.println("Review Star: " + reviewStar);
                    System.out.println("Review Description: " + reviewDescription);
                    System.out.println("Product ID: " + productID);
                    System.out.println("User ID: " + user.getUserID());

                    // Gọi phương thức thêm đánh giá từ ReviewDao
                    ReviewDao.addReview(review);
                    response.sendRedirect(request.getContextPath() + "/detail?productID=" + productID);
                } else {
                    System.out.println("Product not found");
                }
            } else {
                // Người dùng chưa đăng nhập, xử lý theo ý của bạn (chẳng hạn, chuyển hướng đến trang đăng nhập)
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("/error-page"); // Chuyển hướng đến trang lỗi
        }
    }
}
