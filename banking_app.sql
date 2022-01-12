-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2022 at 01:37 PM
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
(1, 1, '4679190007880987', 666, 'POSDARESCU MIHAI', '2027-02-12', 1),
(2, 2, '9934556711129090', 555, 'DORO ANA', '2025-01-22', 1),
(3, 3, '1236632100001112', 154, 'POPE ALEXANDRU', '2027-01-07', 0);

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `Id` int(11) NOT NULL,
  `Nume` varchar(100) NOT NULL,
  `Prenume` varchar(100) NOT NULL,
  `Security_code` varchar(100) NOT NULL,
  `IBAN` varchar(20) DEFAULT NULL,
  `account_balance` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`Id`, `Nume`, `Prenume`, `Security_code`, `IBAN`, `account_balance`) VALUES
(1, 'Posdarescu', 'Mihai', 'zhhh12', 'RO19RNCB555599990000', 2800),
(2, 'Doro', 'Ana', 'ddaa11', 'RO19RNCB111122223333', 1050),
(3, 'Pope', 'Alexandru', '123fff', 'GE14RNCB777766665555', 3100);

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
(59, NULL, 1, 50, '2021-12-27 23:59:02.306719'),
(60, NULL, 1, 100, '2022-01-06 12:48:07.904360'),
(61, NULL, 1, 100, '2022-01-08 17:16:59.023232'),
(62, NULL, 1, 100, '2022-01-08 17:17:55.876024'),
(63, NULL, 1, 444, '2022-01-08 17:19:38.845118'),
(64, NULL, 1, 1, '2022-01-08 17:20:23.109294'),
(65, NULL, 1, 1, '2022-01-08 17:20:27.947892'),
(76, NULL, 1, 1, '2022-01-08 17:47:19.772649'),
(83, 1, 2, 10, '2022-01-08 17:51:20.986421'),
(84, 1, 2, 10, '2022-01-08 17:51:23.530977'),
(85, 1, 2, 10, '2022-01-09 10:42:21.882845'),
(86, 1, 2, 14, '2022-01-09 10:42:33.900717'),
(87, 1, 2, 14, '2022-01-09 10:42:59.584698'),
(88, NULL, 1, 4, '2022-01-09 11:02:36.133475'),
(89, 1, 2, 10, '2022-01-09 11:03:12.068645'),
(90, NULL, 1, 100, '2022-01-09 16:25:46.725308'),
(91, NULL, 3, 100, '2022-01-10 11:24:40.642360');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Id` int(11) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `Email`, `Password`) VALUES
(1, 'posdaresculg@gmail.com', '123456'),
(2, 'doroana@gmail.com', '123456'),
(3, 'popealexandru@gmail.com', '123456');

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
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
