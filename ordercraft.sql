-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2023 at 04:18 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ordercraft`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `idClient` int(11) NOT NULL,
  `nameClient` varchar(255) NOT NULL,
  `emailClient` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`idClient`, `nameClient`, `emailClient`) VALUES
(14, 'yassine', 'yassine@email.com'),
(15, 'fati', 'fati@email.com'),
(18, 'mohammed', 'mohammed@email.com'),
(19, 'ahmad', 'ahmad@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems` (
  `idOrder` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` double(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderitems`
--

INSERT INTO `orderitems` (`idOrder`, `idProduct`, `quantity`, `total`) VALUES
(20, 38, 10, 202.00),
(20, 40, 30, 1500.00),
(24, 38, 1, 20.20),
(25, 40, 22, 1100.00),
(25, 41, 1, 2500.00),
(26, 38, 1, 20.20);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `idOrder` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `dateOrder` date NOT NULL,
  `totalOrder` double(8,2) NOT NULL,
  `statusOrder` enum('INPROGRESS','CANCELED','DELIVERED') NOT NULL DEFAULT 'INPROGRESS'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`idOrder`, `idClient`, `dateOrder`, `totalOrder`, `statusOrder`) VALUES
(20, 15, '2023-12-24', 1702.00, 'DELIVERED'),
(24, 14, '2023-12-24', 20.20, 'CANCELED'),
(25, 18, '2023-12-24', 3600.00, 'CANCELED'),
(26, 15, '2023-12-24', 20.20, 'DELIVERED');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `idProduct` int(11) NOT NULL,
  `nameProduct` varchar(255) NOT NULL,
  `priceProduct` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`idProduct`, `nameProduct`, `priceProduct`, `stock`) VALUES
(38, 'mouse', 20.20, 196),
(40, 'keyboard', 50.00, 244),
(41, 'laptop lenovo', 2500.00, 18),
(43, 'laptop hp', 2400.00, 9);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`idClient`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`idOrder`,`idProduct`),
  ADD KEY `orderitems_idproduct_foreign` (`idProduct`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`idOrder`),
  ADD KEY `orders_idclient_foreign` (`idClient`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`idProduct`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `idProduct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD CONSTRAINT `orderitems_idorder_foreign` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`),
  ADD CONSTRAINT `orderitems_idproduct_foreign` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_idclient_foreign` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
