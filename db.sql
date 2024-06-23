CREATE DATABASE  IF NOT EXISTS `mpds` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mpds`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: mpds
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Seiko'),(2,'Fossil'),(3,'Casio'),(4,'Gshock');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_info`
--

DROP TABLE IF EXISTS `invoice_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_info` (
  `amount` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `id_invoice` int DEFAULT NULL,
  `id_product` int DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtlkl19xuanbbkyodf4whdgm43` (`id_invoice`),
  KEY `FKb6lufqrkre5sfnmgfuknkmck8` (`id_product`),
  CONSTRAINT `FKb6lufqrkre5sfnmgfuknkmck8` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`),
  CONSTRAINT `FKtlkl19xuanbbkyodf4whdgm43` FOREIGN KEY (`id_invoice`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_info`
--

LOCK TABLES `invoice_info` WRITE;
/*!40000 ALTER TABLE `invoice_info` DISABLE KEYS */;
INSERT INTO `invoice_info` VALUES (6,1,1,29,180),(9,2,2,2,100),(9,3,3,2,100),(3,4,3,6,170),(1,5,4,2,100),(1,6,5,6,170),(1,7,6,8,219),(1,8,7,5,130);
/*!40000 ALTER TABLE `invoice_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `totalmoney` double NOT NULL,
  `user_id` int DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `createdate` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbwr4d4vyqf2bkoetxtt8j9dx7` (`user_id`),
  CONSTRAINT `FKbwr4d4vyqf2bkoetxtt8j9dx7` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` VALUES (1,1080,NULL,'TP HCM',NULL,'vietanhthantai4@gmail.com','123213412','Pending Processing'),(2,900,NULL,'TP HCM',NULL,'20110352@student.hcmute.edu.vn','123213412','Pending Processing'),(3,1410,NULL,'TP HCM',NULL,'20110352@student.hcmute.edu.vn','0123213','Pending Processing'),(4,100,NULL,'TP HCM',NULL,'vietanhthantai4@gmail.com','0123213','Pending Processing'),(5,170,NULL,'TP HCM',NULL,'vietanhthantai4@gmail.com','0123213','Pending Processing'),(6,219,NULL,'TP HCM',NULL,'vietanhthantai4@gmail.com','0123213','Pending Processing'),(7,130,NULL,'TP HCM',NULL,'vietanhthantai4@gmail.com','0123213','Pending Processing');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `catergory_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `status` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `image3` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `dial_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgtb1j0jdcyeydi2gfqc772weo` (`catergory_id`),
  CONSTRAINT `FKgtb1j0jdcyeydi2gfqc772weo` FOREIGN KEY (`catergory_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,121,1,'Phiên bản Seiko SRPD63K1 đồng hồ lặn với vỏ viền bezel xanh nổi bật các cọc số trắng, kiểu dáng dày dặn nam tính phần vỏ máy cơ 13mm với khả năng chống nước 10ATM.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F2.jpg?alt=media&token=8bff0880-fd79-492e-b5d2-d9db31e0ab54','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F1%2Fcasio1.jpg?alt=media&token=81b4b443-891a-4a32-a4c3-e991f32badac','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F1%2Fcasio1.jpg?alt=media&token=81b4b443-891a-4a32-a4c3-e991f32badac','Seiko SRPD63K1','Digital','50mm'),(4,2,100,1,'Mẫu G-Shock GA-B2100-1A1DR máy pin với phiên bản Solar (năng lượng ánh sáng), điểm nhấn nổi bật với tính Bluetooth có khả năng kết nối với điện thoại thông minh.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fgshock%2F1.jpg?alt=media&token=56e08c2a-876a-45b0-9a53-400e2e9ba5f8','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F1%2Fcasio1.jpg?alt=media&token=81b4b443-891a-4a32-a4c3-e991f32badac','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F1%2Fcasio1.jpg?alt=media&token=81b4b443-891a-4a32-a4c3-e991f32badac','G-Shock GA-B2100',NULL,'42mm'),(4,3,121,1,'Mẫu Baby-G BGD-570-1DR phiên bản dây vỏ nhựa phối tone màu đen năng động, mặt số điện tử hiện thị đa chức năng cùng với khả năng chịu nước lên đến 20atm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fgshock%2F2.jpg?alt=media&token=ef14bb35-1602-43cc-82a9-0fcebf18c2b6',NULL,NULL,'Baby-G BGD-570',NULL,NULL),(3,4,150,1,'Đồng hồ Casio EFR-526L-1AVUDF có vỏ kim loại bằng chất liệu thép không gỉ tinh tế, kim chỉ và vạch số được phủ phản quang nổi bật trên nền số màu đen mạnh mẽ, có ô lịch ngày vị trí 3h, dây đeo da màu đen có vân đem lãi vẻ lịch lãm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F10%2FEFR-526L-1AVUDF-1.jpg?alt=media&token=631ee486-caeb-418a-85b2-69239cbbb14c',NULL,NULL,'Casio EFR-526L-1AVUDF',NULL,'45mm'),(3,5,130,1,'Mẫu Casio EFV-540D-1AVUDF kiểu dáng 6 kim kẻm theo 3 núm vặn bên hông với vẻ ngoài đặc trưng thuộc dòng Edifice mang trên mình phong cách thể thao đầy lịch lãm cho các phái mạnh cùng khả năng chịu nước 10ATM.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F11%2FCasio%20EFV-540D-1AVUDF.jpg?alt=media&token=57e48326-27fb-47ae-81eb-bc15ac512c88',NULL,NULL,'Casio EFV-540D-1AVUD',NULL,NULL),(3,6,170,1,'Đồng hồ dây da Casio EFR-526L-7AVUDF với mặt đồng hồ nền trắng viền đen tinh tế, 3 ô phụ màu đen đặc biệt, vỏ làm bằng chất liệu thép không gỉ, dây da màu nâu đậm có vân còn có 1 lịch ngày.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F12%2FCasio%20EFR-526L-7AVUDF.jpg?alt=media&token=d4a95315-1fec-4ea9-b3ce-f579723f1167',NULL,NULL,'Casio EFR-526L-7AVUDF',NULL,NULL),(3,7,190,1,'Mẫu Casio ECB-950MP-1ADF tính năng vượt trội bộ máy được trang bị công nghệ Solar (Năng lượng ánh sáng), phiên bản Edifice nổi bật tính năng Blue tooth kết nối điện thoại thông minh.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F13%2FCasio%20ECB-950MP-1ADF.jpg?alt=media&token=6053c130-1024-49e0-a493-76fbb5545cbc',NULL,NULL,'Casio ECB-950MP-1ADF',NULL,NULL),(3,8,219,1,'Đồng hồ Casio EFR-526L-1AVUDF có vỏ kim loại bằng chất liệu thép không gỉ tinh tế, kim chỉ và vạch số được phủ phản quang nổi bật trên nền số màu đen mạnh mẽ, có ô lịch ngày vị trí 3h, dây đeo da màu đen có vân đem lãi vẻ lịch lãm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F14%2FCasio%20EFV-570D-2AVUDF.jpg?alt=media&token=e24a2175-3999-4502-804e-4c75075cd9d2',NULL,NULL,'Casio EFV-570D-2AVUDF',NULL,'42mm'),(3,9,123,1,'Mẫu Casio EFV-540D-1AVUDF kiểu dáng 6 kim kẻm theo 3 núm vặn bên hông với vẻ ngoài đặc trưng thuộc dòng Edifice mang trên mình phong cách thể thao đầy lịch lãm cho các phái mạnh cùng khả năng chịu nước 10ATM.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F15%2FCasio%20EFR-552D-1A2VUDF.jpg?alt=media&token=85f8984d-1b81-467d-96ec-4cc1d88e1350',NULL,NULL,'Casio EFR-552D-1A2VUDF',NULL,'50mm'),(3,10,212,1,'Đồng hồ Casio EFR-539BK-1AVUDF với tông màu đen chủ đạo từ mặt số tới dây đeo kim loại mạnh mẽ, kim chỉ và vạch số màu đồng nổi bật trên nền số, làm tô điểm nét lịch lãm, sang trọng cho phái mạnh.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2F16%2FCasio%20EFR-539BK.jpg?alt=media&token=289b114f-7490-4372-8656-5e2680f47106',NULL,NULL,'Casio EFR-539BK',NULL,NULL),(1,11,125,1,'Mẫu Seiko 5 Sports SRPK39K1 phiên bản giới hạn chỉ 15.000 chiếc trên toàn cầu, là ấn phẩm kỷ niệm 55 năm ra mắt bộ sưu tập Seiko 5 Sports, đồng thời vinh danh ngôi sao điện ảnh võ thuật Lý Tiểu Long qua toàn bộ thiết kế đồng hồ lấy cảm hứng từ ông.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F3.jpg?alt=media&token=c010422e-445f-431f-a5d2-33a4ad1c5426',NULL,NULL,'Seiko 5 55th',NULL,NULL),(1,12,130,1,'Mẫu Seiko 5 Sports Retro Color SRPK13K là phiên bản đặc biệt, lấy cảm hứng từ chiếc đồng hồ có 2 viền benzel được ra mắt vào năm 1969 của Seiko. Đặc trưng với thiết kế đầy màu sắc trên mặt số có kích thước 42,5mm và kim giây màu cam khác biệt.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F4.jpg?alt=media&token=19ce6d4d-9fb8-4af1-aceb-663a61623f4e',NULL,NULL,'Seiko 5 Retro Color',NULL,NULL),(1,13,160,1,'Mẫu Seiko SSC913P1 thiết kế 3 núm vặn điều chỉnh các tính năng Chronograph (đo thời gian) tạo nên vẻ ngoài độc đáo trên nền mặt số kích thước 41.4mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F5.jpg?alt=media&token=09338dd3-34a8-4ca3-a470-674d700a1921',NULL,NULL,'Seiko SSC913P1',NULL,NULL),(1,14,190,1,'Mẫu Seiko SRPJ47K1 kim giây tone màu vàng nổi bật giữa các chi tiết cọc chấm tròn tạo hình dày dặn và thiết kế phủ dạ quang trên nền mặt số khoác bởi một lớp áo nâu thẫm điềm đạm mà cuốn hút.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F6.jpg?alt=media&token=c6ab05bc-08ec-4971-bda7-844c942aff43',NULL,NULL,'Seiko SRPJ47K1',NULL,NULL),(1,15,200,1,'Mẫu Seiko SRPG35K1 phiên bản dây vải phong cách năng động, chi tiết kim chỉ cùng các cọc chấm nhỏ được phủ dạ quang trên nền mặt số đen kích thước 39.4mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F7.jpg?alt=media&token=b13d6f22-41bb-46f6-97dd-3b2ef641df28',NULL,NULL,'Seiko 5 SRPG35K1',NULL,NULL),(1,16,213,1,'Mẫu Seiko SRPH21K1 phiên bản dây lưới kim loại mạ bạc trẻ trung, chi tiết kim chỉ cùng các cọc chấm tròn nhỏ được phủ dạ quang trên nền mặt số kích thước 39.4mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F8.jpg?alt=media&token=acf4e4dd-20d9-4a61-9325-df8e487414c0',NULL,NULL,'Seiko 5 SRPH21K1',NULL,NULL),(1,17,220,1,'Mẫu Seiko SSC911P1 thiết kế 3 núm vặn điều chỉnh các tính năng Chronograph (đo thời gian) tạo nên vẻ ngoài độc đáo trên nền mặt số kích thước 41.4mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F9.jpg?alt=media&token=3a0e6cc8-e72d-4c21-b091-c5b474a7f682',NULL,NULL,'Seiko SSC911P1',NULL,NULL),(1,18,200,1,'Seiko SSA425J1, đồng hồ cơ Nhật Bản sang trọng lấy cảm hứng từ phong cách cổ điển thập niên 60. Trang bị máy 4R39 độc quyền cho thời gian trữ cót 41 giờ.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fseiko%2F10.jpg?alt=media&token=fe71f204-8e15-4028-9988-49e4f94d0eec',NULL,NULL,'Seiko Presage Style',NULL,NULL),(2,19,195,1,'Mẫu Fossil FS5251SET dây da nâu sậm phiên bản da trơn phong cách thời trang nam tính với lối thiết kế tính năng Chronograph (đo thời gian) trên nền mặt số lớn size 42mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F1.jpg?alt=media&token=8a6f0ec0-4d9b-4ffc-881e-351d61c09aef',NULL,NULL,'Fossil FS5251SET',NULL,NULL),(2,20,185,1,'Mẫu đồng hồ Fossil ME3061 với kiểu thiết kế cổ điển dành cho nam, kim chỉ và vạch chữ số la mã nổi bật trên nền đen, kết hợp cùng với dây đeo bằng da nâu tạo vẻ lịch lãm cho phái mạnh.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F2.jpg?alt=media&token=8b45cb31-34f2-4041-96f8-c5586c199063',NULL,NULL,'Fossil ME3061',NULL,NULL),(2,21,175,1,'Fossil Bronson Chronograph Smoke FS6017, chiếc đồng hồ thể thao dành cho nam với mặt số màu đỏ thẫm mạnh mẽ và quyền lực. Trang bị tính năng Chronograph bấm giờ thể thao đạt độ chính xác cao nhờ bộ máy quartz Nhật Bản.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F3.jpg?alt=media&token=2cba957c-0583-4aa9-8d6f-a982b9e8dc82',NULL,NULL,'Fossil FS6017',NULL,NULL),(2,22,215,1,'Fossil Blue Dive FS6013 là mẫu đồng hồ nam phong cách thể thao, có đường kính 42mm phù hợp với đại đa số nam giới. Viền bezel màu đỏ nâu có thể xoay được phục vụ các mục đích bơi lặn thể thao.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F4.jpg?alt=media&token=2e4f3c69-6b14-4235-ae8e-a67aad09f5da',NULL,NULL,'Fossil FS6013',NULL,NULL),(2,23,220,1,'Mẫu Fossil FS5961 các chi tiết kim chỉ cùng các cọc chấm tròn tạo hình dày dặn phủ dạ quang trên nền mặt số lớn kích thước 42mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F5.jpg?alt=media&token=13e23712-91ea-48cf-94f2-305b0f3ab8b1',NULL,NULL,'Fossil FS5961',NULL,'42mm'),(2,24,235,1,'Mẫu Fossil FS5944 thiết kế 3 núm vặn điều chỉnh các tính năng Chronograp (đo thời gian) tạo nên kiểu dáng độc đáo đồng hồ 6 kim hiện thị trên nền mặt số kích thước 42mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F6.jpg?alt=media&token=b5ffbc2c-f47d-4022-a1ff-84bada079f21',NULL,NULL,'Fossil FS5944',NULL,NULL),(2,25,198,1,'Mẫu Fossil FS5936 dây đeo kim loại phiên bản dây lưới phong cách thời trang, thiết kế đơn chức năng 3 kim cùng lịch ngày lịch thứ hiện thị trên nền mặt số kích thước 42mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F7.jpg?alt=media&token=3d8efb32-ce94-47ff-a0ac-a64f5c6e0bbf',NULL,NULL,'Fossil FS5936',NULL,NULL),(2,26,216,1,'Mẫu Fossil FS5934 phiên bản dây da trơn tone màu nâu sẫm kết hợp cùng nền mặt số vuông, kết hợp vỏ máy pin kích thước dày dặn với thiết kế tạo hình vỏ nhám phong cách nam tính.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F8.jpg?alt=media&token=81426dde-6852-4792-a7ec-7e00626fe260',NULL,NULL,'Fossil FS5934',NULL,NULL),(2,27,130,1,'Mẫu Fossil FS5950 chi tiết kim chỉ cùng các cọc chấm tròn được tạo hình dày dặn phủ dạ quang nổi bật trên nền mặt số xanh tone màu thời trang với kích thước lớn 42mm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F9.jpg?alt=media&token=ff16b6f3-8040-4277-8ce3-f18107c577af',NULL,NULL,'Fossil FS5950',NULL,NULL),(2,28,145,1,'Mẫu Fossil ES4713 vẻ ngoài thời trang sang trọng với thiết kế đính pha lê trên phần vỏ viền xung quanh, kết hợp nền cọc số la mã được tạo hình mỏng trẻ trung cho phái đẹp.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Ffossil%2F10.jpg?alt=media&token=3764b306-0e39-43c3-969d-bec5d4984234',NULL,NULL,'Fossil ES4713',NULL,NULL),(4,29,180,1,'Mẫu G-Shock GA-2000S-1ADR mặt số tròn điện tử hiện thị đa chức năng, phiên bản đồng hồ thể thao với thiết kế dây vỏ nhựa cùng với khả năng chịu nước lên đến 20atm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fgshock%2F3.jpg?alt=media&token=b0373999-89ba-4458-9904-f1859087c6cd',NULL,NULL,'G-Shock GA-2000S',NULL,NULL),(4,30,170,1,'Mẫu G-Shock GA-2200BNR-1ADR mặt số tròn điện tử hiện thị đa chức năng, phiên bản đồng hồ thể thao với thiết kế dây vỏ nhựa cùng với khả năng chịu nước lên đến 20atm.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fgshock%2F4.jpg?alt=media&token=a0957d24-1012-4bd8-b5eb-720c3905432a',NULL,NULL,'G-Shock GA-2200BNR',NULL,NULL),(4,31,200,1,'Mẫu G-Shock GA-B2100C-9ADR máy pin với phiên bản Solar (năng lượng ánh sáng), điểm nhấn nổi bật với tính năng Bluetooth có khả năng kết nối với điện thoại thông minh.','https://firebasestorage.googleapis.com/v0/b/android-watches.appspot.com/o/product%2Fgshock%2F5.jpg?alt=media&token=f0306825-2e79-46d0-9473-352941702756',NULL,NULL,'G-Shock GA-B2100C',NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'NT','admin@','admin','123','0123214','admin'),(2,0,'21321','123','test','123','1239817222','vanh');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mpds'
--

--
-- Dumping routines for database 'mpds'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09  6:07:27
