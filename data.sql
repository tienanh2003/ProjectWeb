

-- Thêm dữ liệu vào bảng Publisher
INSERT INTO Publisher(publisherName, publisherDescription)
VALUES ('NXB Kim Đồng', 'Nhà xuất bản Kim Đồng trực thuộc Trung ương Đoàn TNCS Hồ Chí Minh là Nhà xuất bản tổng hợp có chức năng xuất bản sách và văn hóa phẩm phục vụ thiếu nhi và các bậc phụ huynh trong cả nước, quảng bá và giới thiệu văn hóa Việt Nam ra thế giới.
Nhà xuất bản có nhiệm vụ tổ chức bản thảo, biên soạn, biên dịch, xuất bản và phát hành các xuất bản phẩm có nội dung: giáo dục truyền thống dân tộc, giáo dục về tri thức, kiến thức… trên các lĩnh vực văn học, nghệ thuật, khoa học kỹ thuật nhằm cung cấp cho các em thiếu nhi cũng như các bậc phụ huynh các kiến thức cần thiết trong cuộc sống, những tinh hoa của tri thức nhân loại nhằm góp phần giáo dục và hình thành nhân cách thế hệ trẻ.');

INSERT INTO Publisher(publisherName, publisherDescription)
VALUES ('NXB Tuổi Trẻ', 'Nhà xuất bản Trẻ là một đơn vị chuyên xuất bản và phát hành sách nhiều thể loại có trụ sở chính tại Thành phố Hồ Chí Minh.NXB Trẻ luôn nỗ lực mang đến những món ăn tinh thần phong phú và bổ ích nhất. Năng động, sáng tạo, dám nghĩ, dám làm.
NXB Trẻ tự hào được đồng hành với xã hội Nuôi dưỡng tâm hồn – Khơi nguồn tri thức, mở ra không chỉ những trang sách mà còn là những chân trời bát ngát cho bạn đọc thân yêu của mình.');

INSERT INTO Publisher(publisherName, publisherDescription)
VALUES ('NXB Tri Thức', 'Trở thành một Nhà xuất bản có những đầu sách có chất lượng, về nội dung và hình thức, thuộc nhiều tủ sách khác nhau, một diễn đàn và địa chỉ có uy tín của các học giả, nhà nghiên cứu, học sinh, sinh viên và độc giả Việt Nam ở khắp nơi trên thế giới, để được thừa nhận là Nhà xuất bản tham chiếu trong việc phổ biến các trào lưu tư tưởng lớn, cổ điển lẫn hiện đại.');

-- Thêm dữ liệu vào bảng product
INSERT INTO Product(productName, productAuthor, productImage ,productDescription, productPrice, productDatePublic, publisherID)
VALUES ('Vũ Trụ', 'Nguyễn Mỹ Linh', 'https://www.nxbtre.com.vn/Images/product/nxbtre_full_09342018_093428.jpg', 'Một thế giới bí ẩn về vũ trụ', 30, '2022-12-15', 1);

INSERT INTO Product(productName, productAuthor, productImage , productDescription, productPrice, productDatePublic, publisherID)
VALUES ('Bóng Đá', 'Nguyễn Huỳnh Đức', 'https://i.ebayimg.com/images/g/lKgAAOSw9x1kukOm/s-l400.png', 'Hiểu hơn về bóng đá', 40, '2023-01-20', 2);

INSERT INTO Product(productName, productAuthor, productImage , productDescription, productPrice, productDatePublic, publisherID)
VALUES ('Cuộc đời Faker', 'Nguyễn Văn Tùng', 'https://img-cdn.xemgame.com/2020/11/26/truyen-tranh-faker.jpg' , 'Cuộc đời Faker', 25, '2023-02-25', 3);

-- Thêm dữ liệu vào bảng Category
INSERT INTO Category (categoryName)
VALUES ('Tiểu Thuyết');

INSERT INTO Category (categoryName)
VALUES ('Thể Thao');

INSERT INTO Category (categoryName)
VALUES ('Văn Học');

-- Thêm dữ liệu vào bảng User
INSERT INTO User (username, password, fullname, email, phone, address)
VALUES
('user1', '123', 'User One', 'user1@example.com', 1234567890, '123 Main St'),
('user2', '123', 'User Two', 'user2@example.com', 9876543210, '456 Elm St'),
('user3', '123', 'User Three', 'user3@example.com', 5555555555, '789 Oak St'),
('user4', '123', 'User Four', 'user4@example.com', 1234567890, '456 Pine St'),
('user5', '123', 'User Five', 'user5@example.com', 9876543210, '789 Maple St'),
('user6', '123', 'User Six', 'user6@example.com', 5555555555, '123 Cedar St'),
('user7', '123', 'User Seven', 'user7@example.com', 1234567890, '456 Birch St'),
('user8', '123', 'User Eight', 'user8@example.com', 9876543210, '789 Walnut St'),
('user9', '123', 'User Nine', 'user9@example.com', 5555555555, '123 Oak St'),
('user10', '123', 'User Ten', 'user10@example.com', 1234567890, '456 Maple St');

-- Thêm dữ liệu vào bảng Discount
INSERT INTO Discount (discountCode, discountPrice, discountDateStart, discountEnd)
VALUES
('DS10', 10, '2023-01-01', '2024-02-01'),
('DS20', 20, '2023-03-01', '2024-04-01'),
('DS30', 30, '2023-12-01', '2024-01-01'),
('DS40', 40, '2023-06-01', '2024-07-01'),
('DS50', 50, '2023-09-01', '2024-10-01'),
('DS60', 60, '2023-02-01', '2024-03-01'),
('DS70', 70, '2023-11-01', '2024-12-01'),
('DS80', 80, '2023-05-01', '2024-06-01'),
('DS90', 90, '2023-08-01', '2024-09-01'),
('DS100', 100, '2023-04-01', '2024-05-01');

-- Thêm dữ liệu vào bảng Payment
INSERT INTO Payment (paymentName)
VALUES
('Credit Card'),
('PayPal'),
('Cash'),
('Bank Transfer'),
('Bitcoin'),
('Apple Pay'),
('Google Pay'),
('Venmo'),
('Cash App'),
('Zelle');

-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (orderDate, status, totalPrice, userID, discountID, paymentID)
VALUES
('2023-01-15', 'Shipped', 100, 1, 1, 1),
('2023-02-20', 'Delivered', 80, 2, 2, 2),
('2023-03-25', 'Pending', 50, 1, 3, 3),
('2023-04-10', 'Processing', 120, 3, 4, 4),
('2023-05-05', 'Shipped', 90, 2, 5, 5),
('2023-06-15', 'Delivered', 70, 1, 6, 6),
('2023-07-20', 'Pending', 40, 3, 7, 7),
('2023-08-25', 'Processing', 110, 2, 8, 8),
('2023-09-30', 'Shipped', 60, 1, 9, 9),
('2023-10-05', 'Delivered', 30, 3, 10, 10);

-- Thêm dữ liệu vào bảng Publisher
INSERT INTO Publisher (publisherName, publisherDescription)
VALUES
('Publisher A', 'Description A'),
('Publisher B', 'Description B'),
('Publisher C', 'Description C'),
('Publisher D', 'Description D'),
('Publisher E', 'Description E'),
('Publisher F', 'Description F'),
('Publisher G', 'Description G'),
('Publisher H', 'Description H'),
('Publisher I', 'Description I'),
('Publisher J', 'Description J');

-- Thêm dữ liệu vào bảng Book
INSERT INTO Product(productName, productAuthor, productImage, productDescription, productPrice, productDatePublic, publisherID)
VALUES
('product A', 'Author A', 'Image A', 'Description A', 30, '2022-12-15', 1),
('product B', 'Author B', 'Image B', 'Description B', 40, '2023-01-20', 2),
('product C', 'Author C', 'Image C', 'Description C', 25, '2023-02-25', 3),
('product D', 'Author D', 'Image D', 'Description D', 35, '2023-03-15', 4),
('product E', 'Author E', 'Image E', 'Description E', 45, '2023-04-20', 5),
('product F', 'Author F', 'Image F', 'Description F', 55, '2023-05-25', 6),
('product G', 'Author G', 'Image G', 'Description G', 65, '2023-06-30', 7),
('product H', 'Author H', 'Image H', 'Description H', 75, '2023-07-05', 8),
('product I', 'Author I', 'Image I', 'Description I', 85, '2023-08-10', 9),
('product J', 'Author J', 'Image J', 'Description J', 95, '2023-09-15', 10);

-- Thêm dữ liệu vào bảng Category
INSERT INTO Category (categoryName)
VALUES
('Category A'),
('Category B'),
('Category C'),
('Category D'),
('Category E'),
('Category F'),
('Category G'),
('Category H'),
('Category I'),
('Category J');

-- Thêm dữ liệu vào bảng Review
INSERT INTO Review (reviewDate, reviewStar, reviewDescription, productID, userID)
VALUES
('2023-01-05', 5, 'Great product!', 1, 1),
('2023-02-10', 4, 'Interesting read', 2, 2),
('2023-03-15', 3, 'Could be better', 3, 1),
('2023-04-20', 4, 'Enjoyed it', 4, 3),
('2023-05-25', 5, 'Highly recommended', 5, 2),
('2023-06-30', 3, 'Not bad', 6, 1),
('2023-07-05', 2, 'Disappointing', 7, 3),
('2023-08-10', 4, 'Well written', 8, 2),
('2023-09-15', 5, 'Must read', 9, 1),
('2023-10-20', 3, 'Okayish', 10, 3);

-- Thêm dữ liệu vào bảng Cart
INSERT INTO Cart (userID)
VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10);

-- Thêm dữ liệu vào bảng CartItem
INSERT INTO CartItem (quantity, total, productID, cartID)
VALUES
(2, 60, 1, 1),
(1, 40, 2, 2),
(3, 75, 3, 3),
(4, 140, 4, 4),
(2, 90, 5, 5),
(3, 165, 6, 6),
(1, 65, 7, 7),
(5, 375, 8, 8),
(2, 150, 9, 9),
(3, 285, 10, 10);

-- Thêm dữ liệu vào bảng BookCategory
INSERT INTO ProductCategory (productID, categoryID)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- Thêm dữ liệu vào bảng OrderItem
INSERT INTO OrderItem (quantity, total, productID, orderID)
VALUES
(2, 60, 1, 1),
(1, 40, 2, 2),
(3, 75, 3, 3),
(4, 140, 4, 4),
(2, 90, 5, 5),
(3, 165, 6, 6),
(1, 65, 7, 7),
(5, 375, 8, 8),
(2, 150, 9, 9),
(3, 285, 10, 10);

