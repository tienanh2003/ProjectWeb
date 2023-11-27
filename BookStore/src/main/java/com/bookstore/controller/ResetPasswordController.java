package com.bookstore.controller;

import com.bookstore.config.EmailConfig;
import com.bookstore.dao.UserDao;
import com.bookstore.modal.User;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/resetPassword")
public class ResetPasswordController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("sendCode".equals(action)) {
            try {
                String email = request.getParameter("email");
                String otp = EmailConfig.generateOTP();
                EmailConfig.send(email, otp);
                UserDao.updateCodeByEmail(email, otp);
                session.setAttribute("email", email);
                session.setAttribute("codeSent", true);
                response.sendRedirect(request.getContextPath() + "/resetpassword.jsp");
            } catch (MessagingException e) {
                e.printStackTrace();

            }
        } else if ("confirmCode".equals(action)) {
            String email = (String) session.getAttribute("email");
            String enteredOTP = request.getParameter("otp");
            if (UserDao.checkCodeByEmail(email, enteredOTP)) {
                session.setAttribute("codeConfirmed", true);
            } else {
                session.setAttribute("codeInvalid", true);
            }

            response.sendRedirect(request.getContextPath() + "/resetpassword.jsp");
        } else if ("confirmNewPassword".equals(action)) {
            String newPassword = request.getParameter("newpassword");
            String email = (String) session.getAttribute("email");
            User user = UserDao.findByEmail(email);
            if (user != null) {
                user.setPassword(newPassword);
                UserDao.updateUser(user);
            }

            // Clear session attributes after password reset
            session.removeAttribute("codeSent");
            session.removeAttribute("codeConfirmed");
            session.removeAttribute("codeInvalid");
            session.removeAttribute("email");

            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
