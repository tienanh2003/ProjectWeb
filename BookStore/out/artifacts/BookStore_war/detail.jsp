<%@ page import="com.bookstore.dao.ReviewDao" %>
<%@ page import="com.bookstore.modal.User" %>
<%@ page import="com.bookstore.modal.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
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
    <style>
        .rating-form {
            text-align: center;
        }

        .rating-stars {
            font-size: 2em;
            margin: 10px;
        }

        .star {
            cursor: pointer;
        }
    </style>
</head>

<body>
<%@ include file="header.jsp" %>

<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Shop Detail</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="">Home</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">Shop Detail</p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100" src="${product.productImage}" alt="Image">
                    </div>
                    <div class="carousel-item">
                        <img class="w-100 h-100" src="${product.productImage}" alt="Image">
                    </div>
                    <div class="carousel-item">
                        <img class="w-100 h-100" src="${product.productImage}" alt="Image">
                    </div>
                    <div class="carousel-item">
                        <img class="w-100 h-100" src="${product.productImage}" alt="Image">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                    <i class="fa fa-2x fa-angle-left text-dark"></i>
                </a>
                <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                    <i class="fa fa-2x fa-angle-right text-dark"></i>
                </a>
            </div>
        </div>

        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold">${product.productName}</h3>
            <div class="d-flex mb-3">
                <div class="text-primary mr-2">
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star-half-alt"></small>
                    <small class="far fa-star"></small>
                </div>
                <small class="pt-1">(50 Reviews)</small>
            </div>
            <h3 class="font-weight-semi-bold mb-4">${product.productPrice}</h3>
            <p class="mb-4">${product.productDescription}</p>
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group quantity mr-3" style="width: 130px;">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-minus">
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <input type="text" class="form-control bg-secondary text-center" value="1">
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-plus">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
            </div>
            <div class="d-flex pt-2">
                <p class="text-dark font-weight-medium mb-0 mr-2">Share on:</p>
                <div class="d-inline-flex">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-pinterest"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="mb-3">Book Description</h4>
                    <p>${product.productDescription}</p>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-md-6">
                    <h4>Danh sách Review</h4>
                    <c:forEach var="review" items="${reviews}">
                        <div>
                            <h6>${review.getUserID().getUsername()} <small> - <i>${review.getReviewDate()}</i></small>
                            </h6>
                            <div class="text-primary mb-2">
                                <c:forEach begin="1" end="${review.getReviewStar()}">
                                    <i class="fas fa-star"></i>
                                </c:forEach>
                                <c:forEach begin="${review.getReviewStar() + 1}" end="5">
                                    <i class="far fa-star"></i>
                                </c:forEach>
                            </div>
                            <p>${review.getReviewDescription()}</p>
                        </div>
                    </c:forEach>

                    <c:if test="${empty reviews}">
                        <p>No reviews available.</p>
                    </c:if>

                </div>
                <div class="col-md-6">
                    <form class="rating-form" action="review" method="post">
                        <h1>Đánh Giá</h1>

                        <div class="rating-stars text-primary" id="star-container">
                            <i class="star fas fa-star" data-value="1"></i>
                            <i class="star fas fa-star" data-value="2"></i>
                            <i class="star fas fa-star" data-value="3"></i>
                            <i class="star fas fa-star" data-value="4"></i>
                            <i class="star fas fa-star" data-value="5"></i>
                        </div>
                        <input type="hidden" id="reviewStar" name="reviewStar" value="5">

                        <br>

                        <input type="hidden" name="productID" value="${product.productID}">
                        <br>

                        <input type="hidden" name="userID" value="${sessionScope.user.userID}">

                        <textarea class="text-primary mb-2" id="description" name="description" placeholder="Nhập mô tả đánh giá"></textarea>
                        <br>
                        <button class="text-primary mb-2" type="submit">Gửi Đánh Giá</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Shop Detail End -->

    <%@ include file="footer.jsp" %>

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>
    <script>
        const starContainer = document.getElementById('star-container');
        const reviewStarInput = document.getElementById('reviewStar');

        starContainer.addEventListener('click', function (event) {
            const starValue = event.target.dataset.value;
            if (starValue) {
                // Update the hidden input with the selected star value
                reviewStarInput.value = starValue;

                // Highlight the selected stars
                const stars = document.querySelectorAll('.star');
                stars.forEach(star => {
                    const value = parseInt(star.dataset.value);
                    star.classList.toggle('fas', value <= starValue);
                    star.classList.toggle('far', value > starValue);
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
<%--    JS mới--%>

</body>

</html>