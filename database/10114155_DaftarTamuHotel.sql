/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.1.41 : Database - 10114155_daftartamuhotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`10114155_daftartamuhotel` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `10114155_daftartamuhotel`;

/*Table structure for table `t_fasilitas_tambahan` */

DROP TABLE IF EXISTS `t_fasilitas_tambahan`;

CREATE TABLE `t_fasilitas_tambahan` (
  `no_ft` varchar(8) NOT NULL,
  `no_kamar` varchar(2) DEFAULT NULL,
  `extra_bed` varchar(2) DEFAULT NULL,
  `bantal` varchar(2) DEFAULT NULL,
  `laundry` varchar(2) DEFAULT NULL,
  `harga_fasilitas_tambahan` int(50) DEFAULT NULL,
  PRIMARY KEY (`no_ft`),
  KEY `no_kamar` (`no_kamar`),
  CONSTRAINT `t_fasilitas_tambahan_ibfk_1` FOREIGN KEY (`no_kamar`) REFERENCES `t_kamar` (`no_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_fasilitas_tambahan` */

insert  into `t_fasilitas_tambahan`(`no_ft`,`no_kamar`,`extra_bed`,`bantal`,`laundry`,`harga_fasilitas_tambahan`) values ('F0001','3','2','2','0',110000),('F0002','4','1','1','1',60000);

/*Table structure for table `t_identitas` */

DROP TABLE IF EXISTS `t_identitas`;

CREATE TABLE `t_identitas` (
  `no_id` varchar(25) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `kontak` varchar(14) NOT NULL,
  `alamat` varchar(40) NOT NULL,
  PRIMARY KEY (`no_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_identitas` */

insert  into `t_identitas`(`no_id`,`nama`,`kontak`,`alamat`) values ('01042001',' Ismi ',' 081380418344',' Jakarta '),('30101996',' Intan ',' 081210397813',' Jakarta ');

/*Table structure for table `t_kamar` */

DROP TABLE IF EXISTS `t_kamar`;

CREATE TABLE `t_kamar` (
  `no_id` varchar(25) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `no_kamar` varchar(2) NOT NULL,
  `tipe_kamar` varchar(25) NOT NULL,
  `lantai` varchar(2) NOT NULL,
  PRIMARY KEY (`no_kamar`),
  KEY `no_id` (`no_id`),
  CONSTRAINT `t_kamar_ibfk_1` FOREIGN KEY (`no_id`) REFERENCES `t_identitas` (`no_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_kamar` */

insert  into `t_kamar`(`no_id`,`nama`,`no_kamar`,`tipe_kamar`,`lantai`) values ('30101996',' Intan ','3','Twin Room','2'),('01042001','Ismi','4','Twin Room','2');

/*Table structure for table `t_login` */

DROP TABLE IF EXISTS `t_login`;

CREATE TABLE `t_login` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_login` */

insert  into `t_login`(`username`,`password`) values ('admin1','hotel1');

/*Table structure for table `t_pembayaran` */

DROP TABLE IF EXISTS `t_pembayaran`;

CREATE TABLE `t_pembayaran` (
  `no_pembayaran` varchar(8) NOT NULL,
  `no_id` varchar(25) DEFAULT NULL,
  `no_kamar` varchar(2) DEFAULT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `total_hari` int(2) DEFAULT NULL,
  `harga_kamar` int(12) DEFAULT NULL,
  `harga_fasilitas_tambahan` int(14) DEFAULT NULL,
  `total_bayar` int(14) DEFAULT NULL,
  PRIMARY KEY (`no_pembayaran`),
  KEY `no_kamar` (`no_kamar`),
  CONSTRAINT `t_pembayaran_ibfk_1` FOREIGN KEY (`no_kamar`) REFERENCES `t_kamar` (`no_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_pembayaran` */

insert  into `t_pembayaran`(`no_pembayaran`,`no_id`,`no_kamar`,`tgl_masuk`,`tgl_keluar`,`total_hari`,`harga_kamar`,`harga_fasilitas_tambahan`,`total_bayar`) values ('H0001','01042001','3','2016-07-01','2016-07-02',1,250000,60000,310000),('H0002','30111996','4','2016-07-01','2016-07-02',1,250000,110000,360000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
