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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.inap: ~0 rows (approximately)
DELETE FROM `inap`;
/*!40000 ALTER TABLE `inap` DISABLE KEYS */;
INSERT INTO `inap` (`id`, `id_tamu`, `id_kamar`, `id_user`, `tgl_awal`, `tgl_akhir`, `tgl_masuk`, `tgl_keluar`, `timestamp`) VALUES
	(1, 1, 1, 11, '2022-12-15 17:34:41', '2022-12-23 17:34:43', NULL, NULL, '2022-12-22 17:34:48'),
	(2, 5, 1, 11, '2022-12-20 18:02:50', '2022-12-23 00:00:00', NULL, NULL, '2022-12-22 18:04:31');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.invoice: ~2 rows (approximately)
DELETE FROM `invoice`;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`id`, `id_tamu`, `id_user`, `kode_inv`, `dp`, `status`, `timestamp`) VALUES
	(1, 1, 1, 'INV/123', 100000.00, '0', '2022-12-17 17:27:07'),
	(2, 2, 1, 'INV/124', 10000.00, '0', '2022-12-18 17:28:53');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.kamar
CREATE TABLE IF NOT EXISTS `kamar` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_tipe` int(2) DEFAULT NULL,
  `no_kamar` char(5) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tipe` (`id_tipe`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.kamar: ~3 rows (approximately)
DELETE FROM `kamar`;
/*!40000 ALTER TABLE `kamar` DISABLE KEYS */;
INSERT INTO `kamar` (`id`, `id_tipe`, `no_kamar`, `status`) VALUES
	(1, 1, 'R001', '0'),
	(11, 2, 'R345', '0'),
	(24, 1, '765', '1'),
	(25, 2, '123', '0'),
	(26, 1, '123', '1'),
	(27, 1, '1234', '1');
/*!40000 ALTER TABLE `kamar` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.reservasi
CREATE TABLE IF NOT EXISTS `reservasi` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_tamu` int(5) DEFAULT NULL,
  `id_user` int(3) DEFAULT NULL,
  `id_tipe` int(2) DEFAULT NULL,
  `tgl_awal` datetime DEFAULT NULL,
  `tgl_akhir` datetime DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_tamu` (`id_tamu`),
  KEY `id_user` (`id_user`),
  KEY `id_tipe` (`id_tipe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.reservasi: ~0 rows (approximately)
DELETE FROM `reservasi`;
/*!40000 ALTER TABLE `reservasi` DISABLE KEYS */;
INSERT INTO `reservasi` (`id`, `id_tamu`, `id_user`, `id_tipe`, `tgl_awal`, `tgl_akhir`, `timestamp`) VALUES
	(1, 1, 1, 2, '2022-12-01 16:15:53', '2022-12-23 00:00:00', '2022-12-22 16:16:04'),
	(2, 11, 1, 1, '2022-12-20 18:02:50', '2022-12-23 00:00:00', '2022-12-22 18:04:03');
/*!40000 ALTER TABLE `reservasi` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.tamu
CREATE TABLE IF NOT EXISTS `tamu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `no_ktp` char(16) DEFAULT NULL,
  `no_telp` char(13) DEFAULT NULL,
  `status_member` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `no_telp` (`no_telp`),
  KEY `no_ktp` (`no_ktp`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.tamu: ~1 rows (approximately)
DELETE FROM `tamu`;
/*!40000 ALTER TABLE `tamu` DISABLE KEYS */;
INSERT INTO `tamu` (`id`, `nama`, `no_ktp`, `no_telp`, `status_member`) VALUES
	(1, 'pawit wahib', '7897879', '98798798', '0'),
	(4, 'ajskbdkjb', '319826398', '176876', '0'),
	(5, 'tes', '96889987', '987897', '0'),
	(6, 'kjgkj', '1234', '1234', '0'),
	(7, 'jhgjhg', '7786786', '87678', '0'),
	(8, 'lkdklnalk', '876876', '876876', '0'),
	(9, 'kajsbdkj', '876', '887678', '0'),
	(11, 'aksbdjka', '6876', '8587875', '0'),
	(13, 'jhvsadjvh', '798798', '87989798', '0');
/*!40000 ALTER TABLE `tamu` ENABLE KEYS */;

-- Dumping structure for table dbhotelaya.tipe_kamar
CREATE TABLE IF NOT EXISTS `tipe_kamar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(10) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `total_kamar` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table dbhotelaya.tipe_kamar: ~2 rows (approximately)
DELETE FROM `tipe_kamar`;
/*!40000 ALTER TABLE `tipe_kamar` DISABLE KEYS */;
INSERT INTO `tipe_kamar` (`id`, `kategori`, `harga`, `total_kamar`) VALUES
	(1, 'VIP', 500000.00, 20),
	(2, 'REGULAR', 200000.00, 100);
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

-- Dumping data for table dbhotelaya.user: ~0 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `nama`, `username`, `password`, `level`) VALUES
	(1, 'admin', 'admin', 'admin', 'admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
