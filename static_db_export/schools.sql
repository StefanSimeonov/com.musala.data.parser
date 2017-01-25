-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2017 at 02:25 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `schools`
--

-- --------------------------------------------------------

--
-- Table structure for table `schoolclasses`
--

CREATE TABLE `schoolclasses` (
  `id` int(11) NOT NULL,
  `teacherId` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `schoolId` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `sybject` varchar(150) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schoolclasses`
--

INSERT INTO `schoolclasses` (`id`, `teacherId`, `schoolId`, `sybject`) VALUES
(1, '1', '1', 'building engineering'),
(2, '2', '2', 'database'),
(3, '3', '1', 'architecture engineering');

-- --------------------------------------------------------

--
-- Table structure for table `schools`
--

CREATE TABLE `schools` (
  `id` int(11) NOT NULL,
  `name` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `location` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `trend` varchar(150) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schools`
--

INSERT INTO `schools` (`id`, `name`, `location`, `trend`) VALUES
(1, 'Ivan Vazov', 'Martca 23', 'engineering'),
(2, 'Hristo Botev', 'Iordan Iosifov 42', 'informatic');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `facultyNumber` int(11) DEFAULT NULL,
  `schoolClassId` varchar(150) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `name`, `age`, `facultyNumber`, `schoolClassId`) VALUES
(1, 'Ivan Kostov', 21, 15500, '1'),
(2, 'Petur Beron', 22, 15503, '3'),
(3, 'Maria Spasova', 23, 15505, '2'),
(4, 'Pavel Doichinov', 21, 15523, '2'),
(5, 'Iordan Iliev', 22, 15543, '3'),
(6, 'Alex Slavov', 22, 15507, '1');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `name`, `age`) VALUES
(1, 'Stanka Doichinova', 45),
(2, 'Ivanka Stankova', 32),
(3, 'Iordanka Atanasova', 55);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `schoolclasses`
--
ALTER TABLE `schoolclasses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schools`
--
ALTER TABLE `schools`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `schoolclasses`
--
ALTER TABLE `schoolclasses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `schools`
--
ALTER TABLE `schools`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
