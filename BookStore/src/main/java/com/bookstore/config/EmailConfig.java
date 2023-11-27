package com.bookstore.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class EmailConfig {
    // Phương thức gửi email
    public static void send(String toEmail, String otp) throws MessagingException {
        // Your email configuration settings
        String username = "nguyendoantienanh2302@gmail.com";
        String password = "uxys yxhl suar fknr";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Your OTP for Password Reset");
        message.setText("Your OTP is: " + otp);
        Transport.send(message);
    }

    public static String generateOTP() {
        // Your OTP generation logic
        // This is just a simple example, you may want to use a more secure approach
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
