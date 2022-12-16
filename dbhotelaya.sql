-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for dbhotelaya
CREATE DATABASE IF NOT EXISTS `dbhotelaya` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dbhotelaya`;

-- Dumping structure for table dbhotelaya.detail_invoice
CREATE TABLE IF NOT EXISTS `detail_invoice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_inv` int(10) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_inv` (`id_inv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.detail_invoice: ~0 rows (approximately)
DELETE FROM `detail_invoice`;
/*!40000 ALTER TABLE `detail_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_invoice` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.inap
CREATE TABLE IF NOT EXISTS `inap` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_tamu` int(5) DEFAULT NULL,
  `id_kamar` int(4) DEFAULT NULL,
  `id_user` int(3) DEFAULT NULL,
  `tgl_awal` datetime DEFAULT NULL,
  `tgl_akhir` datetime DEFAULT NULL,
  `tgl_masuk` datetime DEFAULT NULL,
  `tgl_keluar` datetime DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tamu` (`id_tamu`),
  KEY `id_kamar` (`id_kamar`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.inap: ~0 rows (approximately)
DELETE FROM `inap`;
/*!40000 ALTER TABLE `inap` DISABLE KEYS */;
/*!40000 ALTER TABLE `inap` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_tamu` int(5) DEFAULT NULL,
  `id_user` int(3) DEFAULT NULL,
  `kode_inv` char(10) DEFAULT NULL,
  `dp` decimal(10,2) DEFAULT NULL,
  `status` enum('0','1','2') DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tamu` (`id_tamu`),
  KEY `id_user` (`id_user`),
  KEY `kode_inv` (`kode_inv`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.invoice: ~0 rows (approximately)
DELETE FROM `invoice`;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.kamar
CREATE TABLE IF NOT EXISTS `kamar` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_tipe` int(2) DEFAULT NULL,
  `no_kamar` char(5) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tipe` (`id_tipe`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.kamar: ~1 rows (approximately)
DELETE FROM `kamar`;
/*!40000 ALTER TABLE `kamar` DISABLE KEYS */;
INSERT INTO `kamar` (`id`, `id_tipe`, `no_kamar`, `status`) VALUES
	(1, 1, 'R001', '1');
/*!40000 ALTER TABLE `kamar` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.reservasi
CREATE TABLE IF NOT EXISTS `reservasi` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_tamu` int(5) DEFAULT NULL,
  `id_user` int(3) DEFAULT NULL,
  `id_tipe` int(2) DEFAULT NULL,
  `tgl-awal` datetime DEFAULT NULL,
  `tgl_akhir` datetime DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tamu` (`id_tamu`),
  KEY `id_user` (`id_user`),
  KEY `id_tipe` (`id_tipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.reservasi: ~0 rows (approximately)
DELETE FROM `reservasi`;
/*!40000 ALTER TABLE `reservasi` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservasi` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.tamu
CREATE TABLE IF NOT EXISTS `tamu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `no_ktp` char(16) DEFAULT NULL,
  `no_telp` char(13) DEFAULT NULL,
  `status_member` enum('Y','N') DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `no_telp` (`no_telp`),
  KEY `no_ktp` (`no_ktp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.tamu: ~0 rows (approximately)
DELETE FROM `tamu`;
/*!40000 ALTER TABLE `tamu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tamu` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.tipe_kamar
CREATE TABLE IF NOT EXISTS `tipe_kamar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(10) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `total_kamar` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.tipe_kamar: ~0 rows (approximately)
DELETE FROM `tipe_kamar`;
/*!40000 ALTER TABLE `tipe_kamar` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipe_kamar` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `level` enum('resepsionis','admin') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.user: ~1 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `nama`, `username`, `password`, `level`) VALUES
	(1, 'admin', 'admin', 'admin', 'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
