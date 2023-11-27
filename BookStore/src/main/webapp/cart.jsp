<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<%@ include file="header.jsp" %>

<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Shopping Cart</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Cart Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-bordered text-center mb-0">
                <thead class="bg-secondary text-dark">
                <tr>
                    <th>Products</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Remove</th>
                </tr>
                </thead>
                <tbody class="align-middle">
                <c:forEach var="cartItem" items="${cartItems}">
                    <tr>
                        <td class="align-middle">
                            <img src="${cartItem.product.productImage}" alt="" style="width: 50px;">
                                ${cartItem.product.productName}
                        </td>
                        <td class="align-middle">${cartItem.product.productPrice}</td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <form action="updatequantity" method="post">
                                    <div class="input-group quantity mx-auto" style="width: 100px;">
                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-primary btn-minus" name="action"
                                                    value="minus">
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>
                                        <input type="text" class="form-control form-control-sm bg-secondary text-center"
                                               name="quantity" value="${cartItem.quantity}">
                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-primary btn-plus" name="action" value="plus">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <input type="hidden" name="cartItemID" value="${cartItem.cartItemID}">
                                </form>
                            </div>
                        </td>
                        <td class="align-middle">${cartItem.total}</td>
                        <td class="align-middle">
                            <form action="deleteCartItem" method="post">
                                <button class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button>
                                <input type="hidden" name="cartItemID" value="${cartItem.cartItemID}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-4">
            <form action="addorder" method="post">
                <div class="card border-secondary mb-5">
                    <select class="btn btn-primary" name="paymentID" id="paymentID">
                        <c:forEach var="payment" items="${payments}">
                            <option name="paymentName" value="${payment.paymentID}">${payment.paymentName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold" name="total" id="total">$0</h5>
                            <!-- Add a hidden input field for total -->
                            <input type="hidden" name="total" id="hiddenTotal" value="">
                        </div>
                        <div class="col-lg-8 mx-auto text-center mb-5">
                            <input type="hidden" name="cartID" id="cartID" value="${cart.cartID}">
                            <button type="submit" class="btn btn-primary">Proceed to Payment</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Cart End -->


<!-- Footer Start -->
<div class="container-fluid bg-secondary text-dark mt-5 pt-5">
    <div class="row px-xl-5 pt-5">
        <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
            <a href="" class="text-decoration-none">
                <h1 class="mb-4 display-5 font-weight-semi-bold"><span
                        class="text-primary font-weight-bold border border-white px-3 mr-1">E</span>Shopper</h1>
            </a>
            <p>Dolore erat dolor sit lorem vero amet. Sed sit lorem magna, ipsum no sit erat lorem et magna ipsum dolore
                amet erat.</p>
            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>123 Street, New York, USA</p>
            <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>info@example.com</p>
            <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 345 67890</p>
        </div>
        <div class="col-lg-8 col-md-12">
            <div class="row">
                <div class="col-md-4 mb-5">
                    <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-dark mb-2" href="home.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-dark mb-2" href="shop.jsp"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-dark mb-2" href="detail.jsp"><i class="fa fa-angle-right mr-2"></i>Shop
                            Detail</a>
                        <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping
                            Cart</a>
                        <a class="text-dark mb-2" href="checkout.jsp"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-dark" href="contact.jsp"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-dark mb-2" href="home.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-dark mb-2" href="shop.jsp"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-dark mb-2" href="detail.jsp"><i class="fa fa-angle-right mr-2"></i>Shop
                            Detail</a>
                        <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping
                            Cart</a>
                        <a class="text-dark mb-2" href="checkout.jsp"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-dark" href="contact.jsp"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                    </div>
                </div>
                <div class="col-md-4 mb-5">
                    <h5 class="font-weight-bold text-dark mb-4">Newsletter</h5>
                    <form action="">
                        <div class="form-group">
                            <input type="text" class="form-control border-0 py-4" placeholder="Your Name"
                                   required="required"/>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control border-0 py-4" placeholder="Your Email"
                                   required="required"/>
                        </div>
                        <div>
                            <button class="btn btn-primary btn-block border-0 py-3" type="submit">Subscribe Now</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row border-top border-light mx-xl-5 py-4">
        <div class="col-md-6 px-xl-0">
            <p class="mb-md-0 text-center text-md-left text-dark">
                &copy; <a class="text-dark font-weight-semi-bold" href="#">Your Site Name</a>. All Rights Reserved.

                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                Designed by <a class="text-dark font-weight-semi-bold" href="https://htmlcodex.com">HTML Codex</a>
            </p>
        </div>
        <div class="col-md-6 px-xl-0 text-center text-md-right">
            <img class="img-fluid" src="img/payments.png" alt="">
        </div>
    </div>
</div>
<!-- Footer End -->

<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<%--<script>--%>
<%--    document.addEventListener("DOMContentLoaded", function () {--%>
<%--        updateTotal(); // Initial calculation when the page is loaded--%>

<%--        // Add event listener to quantity inputs for real-time updates--%>
<%--        var quantityInputs = document.querySelectorAll('.form-control-sm');--%>
<%--        quantityInputs.forEach(function (input) {--%>
<%--            input.addEventListener('input', function () {--%>
<%--                updateTotal();--%>
<%--            });--%>
<%--        });--%>

<%--        // Function to update the total based on cart items--%>
<%--        function updateTotal() {--%>
<%--            var total = 0;--%>

<%--            // Loop through each row in the table--%>
<%--            var tableRows = document.querySelectorAll('tbody tr');--%>
<%--            tableRows.forEach(function (row) {--%>
<%--                // Get the total value from the current row and add it to the overall total--%>
<%--                var rowTotal = parseInt(row.querySelector('.align-middle:nth-child(4)').textContent);--%>
<%--                total += isNaN(rowTotal) ? 0 : rowTotal;--%>
<%--            });--%>

<%--            // Display the calculated total in the 'total' element--%>
<%--            var totalElement = document.getElementById('total');--%>
<%--            if (totalElement) {--%>
<%--                totalElement.textContent = total; // Display the total as an integer--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Add event listener to quantity inputs for real-time updates
        var quantityInputs = document.querySelectorAll('.form-control-sm');
        quantityInputs.forEach(function (input) {
            input.addEventListener('input', function () {
                updateTotal();
            });
        });

        // Function to update the total based on cart items
        function updateTotal() {
            var total = 0;

            // Loop through each row in the table
            var tableRows = document.querySelectorAll('tbody tr');
            tableRows.forEach(function (row) {
                // Get the total value from the current row and add it to the overall total
                var rowTotal = parseInt(row.querySelector('.align-middle:nth-child(4)').textContent);
                total += isNaN(rowTotal) ? 0 : rowTotal;
            });

            // Display the calculated total in the 'total' element
            var totalElement = document.getElementById('total');
            if (totalElement) {
                totalElement.textContent = total; // Display the total as an integer
            }

            // Update the hidden input field with the total value
            var hiddenTotalInput = document.getElementById('hiddenTotal');
            if (hiddenTotalInput) {
                hiddenTotalInput.value = total;
            }

            // Show or hide the total section based on whether there are products
            var cartSummary = document.querySelector('.card.border-secondary.mb-5');
            if (cartSummary) {
                cartSummary.style.display = total > 0 ? 'block' : 'none';
            }
        }

        // Initial calculation when the page is loaded
        updateTotal();

        // Add event listener to the form submission
        var orderForm = document.querySelector('form[action="addorder"]');
        if (orderForm) {
            orderForm.addEventListener('submit', function () {
                // Ensure the total is up-to-date before submitting the form
                updateTotal();
            });
        }
    });
</script>
<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>

</html>