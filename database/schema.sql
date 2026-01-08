-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2026 at 09:01 AM
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
-- Database: `oop_group03_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `lecture_course_details`
--

CREATE TABLE `lecture_course_details` (
  `Lecture_Id` varchar(50) NOT NULL,
  `Course_Code` varchar(20) NOT NULL,
  `Course_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecture_course_details`
--

INSERT INTO `lecture_course_details` (`Lecture_Id`, `Course_Code`, `Course_Name`) VALUES
('Theshan', '21042', 'Software Engineering'),
('rathnayake', '31067', 'Cyber Security');

-- --------------------------------------------------------

--
-- Table structure for table `lecture_dashboard`
--

CREATE TABLE `lecture_dashboard` (
  `Lecture_Id` varchar(50) NOT NULL,
  `Full_Name` varchar(200) NOT NULL,
  `Department` varchar(100) NOT NULL,
  `Lecture_Email` varchar(100) NOT NULL,
  `Lecture_Mobile_Number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lecture_dashboard`
--

INSERT INTO `lecture_dashboard` (`Lecture_Id`, `Full_Name`, `Department`, `Lecture_Email`, `Lecture_Mobile_Number`) VALUES
('Lec_2022_042', 'Dilshan Rathnayake', 'Cyber Security', 'rathnayake@gmail.com', '0715648724'),
('SE_2022_082', 'Kavindu', 'software', 'kavindu@gmail.com', '0725364973');

-- --------------------------------------------------------

--
-- Table structure for table `sign_in_table`
--

CREATE TABLE `sign_in_table` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sign_up_table`
--

CREATE TABLE `sign_up_table` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sign_up_table`
--

INSERT INTO `sign_up_table` (`username`, `password`, `role`) VALUES
('dilsahn', '1q2w3e4r', 'Admin'),
('Dilshan', '1q2w3e4r', 'Student'),
('Dilshan', '1q2w3e4r', 'Student'),
('Dilshan', '1q2w3e4r', 'Student'),
('DilshanRathnayake', '1q2w3e4r5t', 'Student'),
('rathnayake', '12345678', 'Lecturer'),
('kavindu', '1q2w3easd', 'Student'),
('Theshan', '1qa2ws3ed4rf', 'Lecturer');

-- --------------------------------------------------------

--
-- Table structure for table `student_dashboard`
--

CREATE TABLE `student_dashboard` (
  `Student_id` varchar(50) NOT NULL,
  `Full_name` varchar(200) NOT NULL,
  `Degree` varchar(200) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Mobile_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_dashboard`
--

INSERT INTO `student_dashboard` (`Student_id`, `Full_name`, `Degree`, `Email`, `Mobile_number`) VALUES
('CS_2022_042', 'Lahiru Rathnayake', 'Computer Science', 'dilshan@gmail.com', '0724835682');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lecture_dashboard`
--
ALTER TABLE `lecture_dashboard`
  ADD PRIMARY KEY (`Lecture_Id`);

--
-- Indexes for table `student_dashboard`
--
ALTER TABLE `student_dashboard`
  ADD PRIMARY KEY (`Student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
