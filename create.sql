-- Tạo cơ sở dữ liệu BOOKSTORE
CREATE DATABASE BOOKSTORE;
USE BOOKSTORE;

-- Tạo bảng User
CREATE TABLE User (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone BIGINT,
    address TEXT,
    role VARCHAR(255) DEFAULT 'user'
);

-- Tạo bảng Discount
CREATE TABLE Discount (
    discountID INT AUTO_INCREMENT PRIMARY KEY,
    discountCode VARCHAR(255) NOT NULL,
    discountPrice BIGINT,
    discountDateStart DATE,
    discountEnd DATE
);

-- Tạo bảng Payment
CREATE TABLE Payment (
    paymentID INT AUTO_INCREMENT PRIMARY KEY,
    paymentName VARCHAR(255) NOT NULL
);

-- Tạo bảng Order
CREATE TABLE Orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    orderDate DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    totalPrice BIGINT,
    userID INT NOT NULL,
    discountID INT,
    paymentID INT,
    FOREIGN KEY (userID) REFERENCES User(userID),
    FOREIGN KEY (discountID) REFERENCES Discount(discountID),
    FOREIGN KEY (paymentID) REFERENCES Payment(paymentID)
);

-- Tạo bảng Publisher
CREATE TABLE Publisher (
    publisherID INT AUTO_INCREMENT PRIMARY KEY,
    publisherName VARCHAR(255) NOT NULL,
    publisherDescription TEXT
);

-- Tạo bảng Product
CREATE TABLE Product (
    productID INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255) NOT NULL,
    productAuthor VARCHAR(255),
    productImage TEXT,
    productDescription TEXT,
    productPrice BIGINT,
    productDatePublic DATE,
    publisherID INT,
    FOREIGN KEY (publisherID) REFERENCES Publisher(publisherID)
);

-- Tạo bảng Category
CREATE TABLE Category (
    categoryID INT AUTO_INCREMENT PRIMARY KEY,
    categoryName VARCHAR(255) NOT NULL
);

-- Tạo bảng Review
CREATE TABLE Review (
    reviewID INT AUTO_INCREMENT PRIMARY KEY,
    reviewDate DATE,
    reviewStar INT NOT NULL,
    reviewDescription TEXT,
    productID INT NOT NULL,
    userID INT NOT NULL,
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (userID) REFERENCES User(userID)
);

-- Tạo bảng Cart
CREATE TABLE Cart (
    cartID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

-- Tạo bảng CartItem
CREATE TABLE CartItem (
    cartitemID INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    total BIGINT,
    productID INT NOT NULL,
    cartID INT NOT NULL,
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (cartID) REFERENCES Cart(cartID)
);

-- Tạo bảng ProductCategory
CREATE TABLE ProductCategory (
    productcategoryID INT AUTO_INCREMENT PRIMARY KEY,
    productID INT NOT NULL,
    categoryID INT NOT NULL,
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (categoryID) REFERENCES Category(categoryID)
);

-- Tạo bảng OrderItem
CREATE TABLE OrderItem (
    orderitemID INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    total BIGINT,
    productID INT NOT NULL,
    orderID INT NOT NULL,
    FOREIGN KEY (productID) REFERENCES Product(productID),
    FOREIGN KEY (orderID) REFERENCES Orders(orderID)
);