-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2024 at 10:19 PM
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
-- Database: `moodle`
--

-- --------------------------------------------------------

--
-- Table structure for table `approved_degrees`
--

CREATE TABLE `approved_degrees` (
  `course_id` bigint(20) NOT NULL,
  `degree_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `approved_degrees`
--

INSERT INTO `approved_degrees` (`course_id`, `degree_id`) VALUES
(1, 2),
(2, 1),
(3, 1),
(4, 2),
(5, 3),
(6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `credit` int(11) NOT NULL,
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`credit`, `id`, `code`, `name`) VALUES
(2, 1, 'VETKMA1243D', 'Discrete Mathematics'),
(4, 2, 'VEMISAB113AV', 'Data Structures and Algorithms II.'),
(6, 3, 'VEMIINP154H', 'Computer Networks I.'),
(6, 4, 'VEMIMAB123MF', 'Mathematical Analysis II.'),
(3, 5, 'VEGTGAB144A', 'Corporate Economics'),
(2, 6, 'VEGTSCB244K', 'Accounting Skills');

-- --------------------------------------------------------

--
-- Table structure for table `courses_seq`
--

CREATE TABLE `courses_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `courses_seq`
--

INSERT INTO `courses_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `degrees`
--

CREATE TABLE `degrees` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `degrees`
--

INSERT INTO `degrees` (`id`, `name`) VALUES
(1, 'IT'),
(2, 'Mathematics'),
(3, 'Economy and management');

-- --------------------------------------------------------

--
-- Table structure for table `degrees_seq`
--

CREATE TABLE `degrees_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `degrees_seq`
--

INSERT INTO `degrees_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `id`, `description`, `name`) VALUES
(2, 1, 'Kötelező', 'Tematika tájékoztató'),
(1, 2, 'Első órai eligazítás', 'Eligazítás');

-- --------------------------------------------------------

--
-- Table structure for table `events_seq`
--

CREATE TABLE `events_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `events_seq`
--

INSERT INTO `events_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `mycourses`
--

CREATE TABLE `mycourses` (
  `course_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `mycourses`
--

INSERT INTO `mycourses` (`course_id`, `user_id`) VALUES
(1, 2),
(2, 1),
(2, 152),
(3, 1),
(5, 202),
(6, 202);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `degree_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`degree_id`, `id`, `name`, `password`, `username`, `is_admin`) VALUES
(1, 1, 'Teszt Elek', '$2a$10$4/HmszWR96smks/MavOtVOp6yF3Lydihct.3kOWKMFuvI9aN/aT0q', 'Test', 0),
(2, 2, 'Teszt Tibor', '$2a$10$dnfZA2OKegXQ6jP3UtXSDOrUFmyBVAQbQ572QGuCRew5A0RxOHMbO', 'Test2', 0),
(1, 152, 'Tibor', '$2a$10$Dm2H6ljMpEus43/i1VU0NewMORCVVDwr1a2/UYo05fL5gEON3.wDW', 'Tibor', 1),
(3, 202, 'Teszt Laci', '$2a$10$2b7cB/m2igSNnPejaPMu7e2sCAfBtvrwi0eZtnm8uBxxL6MeNdzF6', 'Test3', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users_seq`
--

CREATE TABLE `users_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `users_seq`
--

INSERT INTO `users_seq` (`next_val`) VALUES
(301);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `approved_degrees`
--
ALTER TABLE `approved_degrees`
  ADD PRIMARY KEY (`course_id`,`degree_id`),
  ADD KEY `FKp0vfkvrfhmuifr5moviwhi6qb` (`degree_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `degrees`
--
ALTER TABLE `degrees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcrklv7jgr5t4m80vme9xrh1p8` (`event_id`);

--
-- Indexes for table `mycourses`
--
ALTER TABLE `mycourses`
  ADD PRIMARY KEY (`course_id`,`user_id`),
  ADD KEY `FKa6vcoctplqt8m8h5cotdke07t` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhe4bgmqbn7jkmlgb2t6d3v143` (`degree_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `approved_degrees`
--
ALTER TABLE `approved_degrees`
  ADD CONSTRAINT `FKna5n3o9n31r1myawkbtwakwbj` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `FKp0vfkvrfhmuifr5moviwhi6qb` FOREIGN KEY (`degree_id`) REFERENCES `degrees` (`id`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FKcrklv7jgr5t4m80vme9xrh1p8` FOREIGN KEY (`event_id`) REFERENCES `courses` (`id`);

--
-- Constraints for table `mycourses`
--
ALTER TABLE `mycourses`
  ADD CONSTRAINT `FKa6vcoctplqt8m8h5cotdke07t` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKla0g2qdv4aumd1xcd3afaho7y` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKhe4bgmqbn7jkmlgb2t6d3v143` FOREIGN KEY (`degree_id`) REFERENCES `degrees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
