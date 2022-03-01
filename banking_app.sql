-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2022 at 08:40 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` int(10) NOT NULL,
  `id_client` int(10) NOT NULL,
  `number` varchar(20) NOT NULL,
  `cvv` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `blocked` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `id_client`, `number`, `cvv`, `full_name`, `expiration_date`, `blocked`) VALUES
(1, 1, '4679190007880987', 656, 'POSDARESCU MIHAI', '2027-02-12', 0),
(2, 2, '9934556711129090', 555, 'DORO ANA', '2025-01-22', 1),
(3, 3, '1236632100001112', 154, 'POPE ALEXANDRU', '2027-01-07', 0),
(6, 6, '2231190025899981', 433, 'BONO GINA', '2027-02-10', 0),
(9, 11, '2472190307810228', 233, 'POM DORIAN', '2026-02-11', 0);

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `security_code` varchar(100) NOT NULL,
  `IBAN` varchar(20) DEFAULT NULL,
  `account_balance` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`id`, `last_name`, `first_name`, `security_code`, `IBAN`, `account_balance`) VALUES
(1, 'Posdarescu', 'Mihai', 'zhhh12', 'RO19RNCB555599990000', 3199),
(2, 'Doro', 'Ana', 'ddaa11', 'RO19RNCB111122223333', 1539),
(3, 'Pope', 'Alexandru', '123fff', 'GE14RNCB777766665555', 3520),
(6, 'Bono', 'Gina', '11er', 'HU20RNCB515194492020', 99),
(11, 'Pom', 'Dorian', '22rr1u', 'RO07RNCB443592992001', 155);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(10) NOT NULL,
  `id_giver` int(10) DEFAULT NULL,
  `id_receiver` int(10) DEFAULT NULL,
  `amount` float NOT NULL,
  `data` timestamp(6) NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `id_giver`, `id_receiver`, `amount`, `data`) VALUES
(93, NULL, 2, 100, '2022-01-25 11:24:55.765492'),
(94, 2, 1, 120, '2022-01-25 11:25:22.605341'),
(95, NULL, 2, 100, '2022-01-25 11:25:25.034725'),
(96, 2, 1, 120, '2022-01-25 11:25:33.117209'),
(97, 1, 2, 50, '2022-01-25 11:26:13.554765'),
(98, 2, 1, 72, '2022-01-25 11:38:04.432895'),
(99, NULL, 1, 90, '2022-02-02 21:08:51.418022'),
(100, NULL, 1, 100, '2022-02-02 21:08:56.206658'),
(101, NULL, 1, 235, '2022-02-02 21:09:00.842209'),
(102, 1, 3, 325, '2022-02-02 21:55:36.693457'),
(103, 1, 3, 150, '2022-02-02 21:56:32.980310'),
(104, 3, 1, 100, '2022-02-02 21:57:36.074525'),
(105, NULL, 3, 50, '2022-02-02 21:57:51.738424'),
(106, 3, 2, 5, '2022-02-02 21:58:18.415993'),
(107, NULL, 2, 90, '2022-02-02 22:01:32.654888'),
(108, 2, 1, 95, '2022-02-03 18:44:13.285446'),
(109, NULL, 1, 22, '2022-02-03 19:37:18.214174'),
(110, NULL, 9, 100, '2022-02-03 20:00:26.742963'),
(111, 9, 8, 99, '2022-02-03 20:04:52.990249'),
(112, NULL, 8, 80, '2022-02-03 20:05:34.593038'),
(113, NULL, 1, 100, '2022-02-03 20:24:16.040149'),
(114, NULL, 1, 100, '2022-02-03 20:24:17.667030'),
(115, NULL, 11, 20, '2022-02-03 20:30:01.177431'),
(116, NULL, 1, 50, '2022-02-08 20:17:45.993993'),
(117, 1, 2, 317, '2022-02-08 20:18:51.884430'),
(118, NULL, 1, 99, '2022-02-16 11:44:24.373816'),
(119, NULL, 1, 100, '2022-03-01 19:10:47.468696'),
(120, NULL, 1, 22, '2022-03-01 19:14:39.854920'),
(121, 1, 2, 22, '2022-03-01 19:15:24.271954');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(100) NOT NULL,
  `id_client` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `id_client`, `email`, `password`) VALUES
(1, 1, 'posdaresculg@gmail.com', '123456'),
(2, 2, 'doroana@gmail.com', '123456'),
(3, 3, 'popealexandru@gmail.com', '123456'),
(4, 6, 'bonogina@yahoo.com', '123456'),
(7, 11, 'pom.dorian@e-uvt.ro', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
