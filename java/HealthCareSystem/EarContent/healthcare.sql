-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2016 at 06:50 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `healthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `ambulance`
--

CREATE TABLE IF NOT EXISTS `ambulance` (
  `ambulance_id` int(10) NOT NULL AUTO_INCREMENT,
  `ambulance_number` varchar(15) NOT NULL,
  `driver_name` varchar(255) NOT NULL,
  `driver_ph` varchar(15) NOT NULL,
  `hospital_ph` varchar(15) NOT NULL,
  `hospital_nm` varchar(255) NOT NULL,
  `ambulance_adr` varchar(255) NOT NULL,
  `ambulance_lat` decimal(20,16) NOT NULL,
  `ambulance_long` decimal(20,16) NOT NULL,
  `ambulane_status` varchar(20) NOT NULL,
  `city` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`ambulance_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `ambulance`
--

INSERT INTO `ambulance` (`ambulance_id`, `ambulance_number`, `driver_name`, `driver_ph`, `hospital_ph`, `hospital_nm`, `ambulance_adr`, `ambulance_lat`, `ambulance_long`, `ambulane_status`, `city`, `description`) VALUES
(2, 'Md 10 BS 5949', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(3, 'Md 10 BS 5948', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(4, 'Md 10 BS 5948', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(5, 'Md 10 BS 5948', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(6, 'Md 10 BS 5948', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(7, 'Md 10 BS 5948', 'Mr .Saurabh Pawar', '9423523922', '9421126028', 'Pawar hospital', 'vita', '21.7854558000000000', '74.3545588000000000', 'ACTIVE', 'VITA', 'maruti van'),
(12, '2324', 'mahadev', '9028437425', '955555555', 'pavan', 'asdf', '24.2255000000000000', '21.2222000000000000', 'ACTIVE', 'vita', 'ava');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(250) NOT NULL,
  `PASSWORD` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `USERNAME`, `PASSWORD`) VALUES
(1, 'saurabh', 'saurabh'),
(2, 'aaaaa', 'aaaaaaa'),
(3, 'vaibhav', 'vaibhav');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
