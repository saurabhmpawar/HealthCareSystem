-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 03, 2016 at 04:46 PM
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
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(250) NOT NULL,
  `PASSWORD` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `login`
--
CREATE TABLE IF NOT EXISTS `ambulance` (
  `ambulance_id` int(10) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `login` (`id`, `USERNAME`, `PASSWORD`) VALUES
(1, 'saurabh', 'saurabh');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
