<%@ page import="java.util.List" %>
<%@ page import="com.bookstore.modal.Orders" %>
<%@ page import="com.bookstore.modal.OrderItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Orders</title>
    <style>
        /* Add your custom styles here */
        body {
            font-family: Arial, sans-serif;
        }

        .order-container {
            margin-top: 20px;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
        }

        .order-header {
            font-weight: bold;
            margin-bottom: 10px;
        }

        .order-item {
            margin-bottom: 10px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<div class="order-container">
    <h2 class="order-header">List Order</h2>

    <!-- Danh sách đơn đặt hàng -->
    <c:forEach var="order" items="${orders}">
        <div class="order-item">
            <p><strong>Order ID:</strong> ${order.orderID}</p>
            <p><strong>Order Date:</strong> ${order.orderDate}</p>
            <p><strong>Status:</strong> ${order.status}</p>
            <p><strong>Total Price:</strong> ${order.totalPrice}</p>
        </div>
    </c:forEach>
</div>
</body>
</html>
