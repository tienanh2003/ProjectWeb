<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Order</title>
</head>
<body>

<h1>Order Details</h1>

<h2>Order Information</h2>
<p>Order ID: ${order.orderID}</p>
<p>Status: ${order.user.username}</p>
<p>Total Price: ${order.totalPrice}</p>
<p>Order Date: ${order.orderDate}</p>
<p>Status: ${order.status}</p>
<p>Status: ${order.payment.paymentName}</p>

<h2>Order Items</h2>
<c:forEach var="orderItem" items="${orderItems}">
    <p>Product Name: ${orderItem.product.productName}</p>
    <p>Quantity: ${orderItem.quantity}</p>
    <p>Total: ${orderItem.total}</p>
    <hr/>
</c:forEach>

</body>
</html>
