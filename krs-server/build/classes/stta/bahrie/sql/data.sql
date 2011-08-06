-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 20, 2010 at 07:06 AM
-- Server version: 5.1.41
-- PHP Version: 5.3.2-1ubuntu4.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sister_krs`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_krs`
--

CREATE TABLE IF NOT EXISTS `detail_krs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_wktu` varchar(11) NOT NULL,
  `nim` varchar(8) NOT NULL,
  `kd_mk` char(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mhs_detailkrs` (`nim`),
  KEY `fk_wkt_detail` (`id_wktu`),
  KEY `fk_mk_detail` (`kd_mk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `detail_krs`
--


-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE IF NOT EXISTS `dosen` (
  `kd_dosen` char(5) NOT NULL,
  `dosen` varchar(50) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  PRIMARY KEY (`kd_dosen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dosen`
--


-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `prodi` enum('TP','TF','TI','TM','TE') NOT NULL,
  `kd_dosen` char(5) NOT NULL,
  `jumlah_krs` int(11) DEFAULT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`nim`),
  KEY `fk_mhs_dsn` (`kd_dosen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--


-- --------------------------------------------------------

--
-- Table structure for table `mk`
--

CREATE TABLE IF NOT EXISTS `mk` (
  `kd_mk` char(5) NOT NULL,
  `mk` varchar(30) NOT NULL,
  `sks` int(11) NOT NULL,
  `jurusan` char(2) NOT NULL,
  `kd_dosen` char(5) NOT NULL,
  PRIMARY KEY (`kd_mk`),
  KEY `fk_mk_dsn` (`kd_dosen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mk`
--


-- --------------------------------------------------------

--
-- Table structure for table `waktu_krs`
--

CREATE TABLE IF NOT EXISTS `waktu_krs` (
  `id_wktu` varchar(11) NOT NULL,
  `nim` varchar(8) NOT NULL,
  `semester` enum('genap','ganjil') NOT NULL,
  `ta` enum('2008/2009','2009/2010','2010/2011','2011/2012','2012/2013') NOT NULL,
  `tgl_pengisian` date DEFAULT NULL,
  PRIMARY KEY (`id_wktu`),
  KEY `fk_mhs_wkrs` (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `waktu_krs`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_krs`
--
ALTER TABLE `detail_krs`
  ADD CONSTRAINT `detail_krs_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`),
  ADD CONSTRAINT `detail_krs_ibfk_2` FOREIGN KEY (`kd_mk`) REFERENCES `mk` (`kd_mk`),
  ADD CONSTRAINT `detail_krs_ibfk_3` FOREIGN KEY (`id_wktu`) REFERENCES `waktu_krs` (`id_wktu`);

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_1` FOREIGN KEY (`kd_dosen`) REFERENCES `dosen` (`kd_dosen`);

--
-- Constraints for table `mk`
--
ALTER TABLE `mk`
  ADD CONSTRAINT `mk_ibfk_1` FOREIGN KEY (`kd_dosen`) REFERENCES `dosen` (`kd_dosen`);

--
-- Constraints for table `waktu_krs`
--
ALTER TABLE `waktu_krs`
  ADD CONSTRAINT `waktu_krs_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`);