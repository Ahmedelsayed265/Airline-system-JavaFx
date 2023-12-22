-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2023 at 10:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airline_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `name`, `email`, `password`) VALUES
(1, 'admin', 'admin@admin.com', '123456'),
(2, 'Ahmed Elsayed', 'ahmedelsayed@icloud.com', 'test1234'),
(3, 'Abd Elrahman', 'AbdoSamy@gmail.com', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `aircrafts`
--

CREATE TABLE `aircrafts` (
  `id` int(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `capacity` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aircrafts`
--

INSERT INTO `aircrafts` (`id`, `type`, `capacity`) VALUES
(1, 'Boeing 747 ', 416),
(2, 'Embraer E190', 457),
(3, 'LS234', 450),
(4, 'Boenng67', 679);

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE `airports` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`id`, `name`, `location`) VALUES
(1, 'Cairo International Airport', 'Cairo - Egypt'),
(2, ' Aéroport Paris-Charles-de-Gaulle', 'Paris - France'),
(3, 'Elsaloum', 'Elsaloum Egypt');

-- --------------------------------------------------------

--
-- Table structure for table `crews`
--

CREATE TABLE `crews` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `captainName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `crews`
--

INSERT INTO `crews` (`id`, `name`, `captainName`) VALUES
(1, 'ducks', 'Ahmed Elsayed');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `phone`) VALUES
(1, 'Abdulrahman Osama', '01121863185');

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `id` int(255) NOT NULL,
  `departureAirport_id` int(255) NOT NULL,
  `arrivalAirport_id` int(255) NOT NULL,
  `departureTime` date NOT NULL,
  `arrivalTime` date NOT NULL,
  `airCraftId` int(255) NOT NULL,
  `crew_id` int(255) NOT NULL,
  `availableSeats` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`id`, `departureAirport_id`, `arrivalAirport_id`, `departureTime`, `arrivalTime`, `airCraftId`, `crew_id`, `availableSeats`) VALUES
(3, 1, 2, '2023-12-05', '2023-12-12', 1, 1, 416);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` int(255) NOT NULL,
  `customer_id` int(255) NOT NULL,
  `message` text NOT NULL,
  `time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `customer_id`, `message`, `time`) VALUES
(1, 1, 'Hello Mr. Abdo,\r\n\r\nWe have reserved seat number 56 for you on Flight 255 from Cairo, Egypt, to Paris, France, scheduled for departure on March 21, 2023. The flight is set to commence at 3:00 PM on April 17, 2023, and is expected to arrive in France on April 18, 2023.\r\n\r\nThe aircraft for this journey is a Boeing 777, and the captain in charge is Ahmed Elsayed. The crew\'s name is Ducks.\r\n\r\nThank you.', '2023-03-22');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(255) NOT NULL,
  `ticket_id` int(255) NOT NULL,
  `customer_id` int(255) NOT NULL,
  `payTime` date NOT NULL,
  `method` varchar(255) NOT NULL,
  `ticketsCount` int(255) NOT NULL,
  `totalPrice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(255) NOT NULL,
  `admin_id` int(255) NOT NULL,
  `flight_id` int(255) NOT NULL,
  `seatNumber` int(255) NOT NULL,
  `reservationTime` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `id` int(255) NOT NULL,
  `departureAirport_id` int(255) NOT NULL,
  `arrivalAirport_id` int(255) NOT NULL,
  `distance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`id`, `departureAirport_id`, `arrivalAirport_id`, `distance`) VALUES
(1, 1, 2, 600);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` int(255) NOT NULL,
  `reservation_id` int(255) NOT NULL,
  `customer_id` int(255) NOT NULL,
  `price` double NOT NULL,
  `paymentStatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `aircrafts`
--
ALTER TABLE `aircrafts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `airports`
--
ALTER TABLE `airports`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `crews`
--
ALTER TABLE `crews`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`id`),
  ADD KEY `flights_ibfk_1` (`airCraftId`),
  ADD KEY `flights_ibfk_2` (`departureAirport_id`),
  ADD KEY `flights_ibfk_3` (`arrivalAirport_id`),
  ADD KEY `crew_id` (`crew_id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ticket_id` (`ticket_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `admin_id` (`admin_id`),
  ADD KEY `flight_id` (`flight_id`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `routes_ibfk_1` (`departureAirport_id`),
  ADD KEY `arrivalAirport_id` (`arrivalAirport_id`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `aircrafts`
--
ALTER TABLE `aircrafts`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `airports`
--
ALTER TABLE `airports`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `crews`
--
ALTER TABLE `crews`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `flights`
--
ALTER TABLE `flights`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `flights_ibfk_1` FOREIGN KEY (`airCraftId`) REFERENCES `aircrafts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flights_ibfk_2` FOREIGN KEY (`departureAirport_id`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flights_ibfk_3` FOREIGN KEY (`arrivalAirport_id`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flights_ibfk_4` FOREIGN KEY (`crew_id`) REFERENCES `crews` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `routes`
--
ALTER TABLE `routes`
  ADD CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`departureAirport_id`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`arrivalAirport_id`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
