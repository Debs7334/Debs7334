/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 6.0.8-alpha-community : Database - ttpadb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ttpadb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ttpadb`;

/*Table structure for table `tblapproval` */

DROP TABLE IF EXISTS `tblapproval`;

CREATE TABLE `tblapproval` (
  `srlno` char(20) DEFAULT NULL,
  `daten` char(20) DEFAULT NULL,
  `refno` char(30) DEFAULT NULL,
  `apdate` char(20) DEFAULT NULL,
  `empid` char(30) DEFAULT NULL,
  `empnm` char(30) DEFAULT NULL,
  `gender` char(20) DEFAULT NULL,
  `dpnm` char(30) DEFAULT NULL,
  `shnm` char(20) DEFAULT NULL,
  `srttym` char(20) DEFAULT NULL,
  `endtym` char(20) DEFAULT NULL,
  `ltype` char(20) DEFAULT NULL,
  `dfrom` char(20) DEFAULT NULL,
  `dto` char(20) DEFAULT NULL,
  `nar` char(60) DEFAULT NULL,
  `sts` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblapproval` */

insert  into `tblapproval`(`srlno`,`daten`,`refno`,`apdate`,`empid`,`empnm`,`gender`,`dpnm`,`shnm`,`srttym`,`endtym`,`ltype`,`dfrom`,`dto`,`nar`,`sts`) values ('1','13/05/2023','4','13/05/2023','E006','UMESH CH. NAYAK','Male','Purchase Department','Day','14:00','22:00','Sick Leave(SL)','13/05/2023','13/05/2023','sick leave','Approved'),('2','13/05/2023','3','12/05/2023','E003','BIJAY KUMAR MOHAPATRA','Male','Sales Department','Morning','6:00','14:00','Casual Leave(CL)','15/05/2023','20/05/2023','Due to illness','Cancelled'),('3','13/05/2023','7','15/05/2023','E015','BABULA SWAIN','Male','Purchase Department','Night','22:00','6:00','Casual Leave(CL)','15/05/2023','19/05/2023','Due to some hospital work','Approved'),('4','13/05/2023','6','14/05/2023','E014','MANMAYEE MOHAPATRA','Female','Human Resource Department','Day','14:00','22:00','Marriage Leave','14/05/2023','19/05/2023','Due to my brother marriage','Approved'),('5','13/05/2023','4','13/05/2023','E006','UMESH CH. NAYAK','Male','Purchase Department','Day','14:00','22:00','Sick Leave(SL)','16/05/2023','25/05/2023','sick leave','Cancelled');

/*Table structure for table `tblattendance` */

DROP TABLE IF EXISTS `tblattendance`;

CREATE TABLE `tblattendance` (
  `srlno` varchar(30) DEFAULT NULL,
  `daten` varchar(30) DEFAULT NULL,
  `empid` varchar(30) DEFAULT NULL,
  `empnm` varchar(30) DEFAULT NULL,
  `gender` varchar(15) DEFAULT NULL,
  `dpnm` varchar(30) DEFAULT NULL,
  `shnm` varchar(30) DEFAULT NULL,
  `srttym` varchar(10) DEFAULT NULL,
  `endtym` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblattendance` */

insert  into `tblattendance`(`srlno`,`daten`,`empid`,`empnm`,`gender`,`dpnm`,`shnm`,`srttym`,`endtym`,`status`) values ('1','04/05/2023','E002','SATYA SUNDAR DAS','Male','Operations Department','Morning','6:00','14:00','P'),('2','02/04/2023','E004','BHUBANANANDA SWAIN','Male','Marketing Department','Night','22:00','6:00','A'),('3','04/03/2023','E003','BIJAY KUMAR MOHAPATRA','Male','Sales Department','Morning','6:00','14:00','P/X'),('4','04/05/2023','E012','BIJAYALAXMI DAS','Female','Sales Department','Day','14:00','22:00','P/X'),('5','04/07/2020','E006','UMESH CH. NAYAK','Male','Purchase Department','Day','14:00','22:00','P'),('6','06/07/2020','E007','LALITA MANTRI','Female','Operations Department','Morning','6:00','14:00','A'),('7','06/01/2021','E010','INDRAJIT PANI','Male','Purchase Department','Night','22:00','6:00','A'),('8','30/04/2023','E013','SUJIT KUMAR SHARANGI','Male','Marketing Department','Night','22:00','6:00','P'),('9','14/10/2022','E011','ASHUTOSH DUTTA','Male','Operations Department','Day','14:00','22:00','X/P'),('10','05/07/2019','E008','BISWAJIT DAS','Male','Sales Department','Day','14:00','22:00','P');

/*Table structure for table `tbldepartment` */

DROP TABLE IF EXISTS `tbldepartment`;

CREATE TABLE `tbldepartment` (
  `dpcd` char(20) DEFAULT NULL,
  `dpnm` char(30) DEFAULT NULL,
  `hodp` char(30) DEFAULT NULL,
  `cno` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbldepartment` */

insert  into `tbldepartment`(`dpcd`,`dpnm`,`hodp`,`cno`) values ('A123','Operations Department','Aman','5698745632'),('B123','Sales Department','Rakesh','1587469569'),('C123','Marketing Department','Mithun','4587126956'),('D123','Human Resource Department','Umesh','6915428523'),('E123','Purchase Department','Akash','6372809948');

/*Table structure for table `tblemployee` */

DROP TABLE IF EXISTS `tblemployee`;

CREATE TABLE `tblemployee` (
  `empid` char(30) DEFAULT NULL,
  `empnm` char(30) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `dob` char(20) DEFAULT NULL,
  `fnm` char(30) DEFAULT NULL,
  `loc` char(30) DEFAULT NULL,
  `city` char(20) DEFAULT NULL,
  `state` char(20) DEFAULT NULL,
  `pin` char(10) DEFAULT NULL,
  `pno` char(15) DEFAULT NULL,
  `mail` char(40) DEFAULT NULL,
  `dpnm` char(30) DEFAULT NULL,
  `dojoin` char(30) DEFAULT NULL,
  `shnm` char(30) DEFAULT NULL,
  `srttym` char(15) DEFAULT NULL,
  `endtym` char(15) DEFAULT NULL,
  `bsicsal` float DEFAULT NULL,
  `da` float DEFAULT NULL,
  `pf` float DEFAULT NULL,
  `hra` float DEFAULT NULL,
  `tax` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblemployee` */

insert  into `tblemployee`(`empid`,`empnm`,`gender`,`dob`,`fnm`,`loc`,`city`,`state`,`pin`,`pno`,`mail`,`dpnm`,`dojoin`,`shnm`,`srttym`,`endtym`,`bsicsal`,`da`,`pf`,`hra`,`tax`) values ('E001','SUBRAT KUMAR ROUT','Male','03/05/1996','JAYAPRAKASH ROUT','MADHUPATNA','CUTTACK','Odisha','751002','6372859665','subrat@gmail.com','Marketing Department','22/05/2016','Day','14:00','22:00',22000,60,9,5,3),('E002','SATYA SUNDAR DAS','Male','03/02/1990','NARAYAN CHANDRA DAS','BADAMBADI','CUTTACK','Odisha','751003','6398745632','satya@gmail.com','Operations Department','04/03/2015','Morning','6:00','14:00',20000,51,6,9,4),('E003','BIJAY KUMAR MOHAPATRA','Male','04/02/1991','MONOJ KUMAR MOHAPATRA','RASULGARH','KHURDHA','Odisha','751020','6378521496','bijaya@yahoo.com','Sales Department','05/03/2016','Morning','6:00','14:00',21000,52,5,6,5),('E004','BHUBANANANDA SWAIN','Male','05/12/1962','BANSIDHAR SWAIN','SALIPUR','CUTTACK','Odisha','754202','7894563215','bhuban@gmail.com','Marketing Department','06/03/1990','Night','22:00','6:00',25000,56,5,4,4),('E005','RABINDRA NATH PATRA','Male','25/12/1962','RADHA NATH PATRA','KHANDAGIRI','KHURDHA','Odisha','754808','9337569845','nathrabi@yahoo.com','Human Resourse Department','09/03/1990','Day','14:00','22:00',30000,63,6,9,5),('E006','UMESH CH. NAYAK','Male','17/09/1970','NARAYAN NAYAK','CHOUDWAR','CUTTACK','Odisha','754026','6378521496','umesh123@gmail.com','Purchase Department','22/11/1995','Day','14:00','22:00',18000,51,5,4,3),('E007','LALITA MANTRI','Female','12/10/1975','LALATENDU MANTRI','RAIPUR','RAIPUR','Chhattisgarh','420026','8249586932','mlalita@email.com','Operations Department','21/02/2000','Morning','6:00','14:00',27000,54,6,4,3),('E008','BISWAJIT DAS','Male','22/05/1975','BANSIDHARA DAS','TANPUR','GUWAHATI','Assam','602012','9438789654','biswa@gmail.com','Sales Department','12/12/2005','Day','14:00','22:00',17000,51,4,8,5),('E009','ASHUTOSH PANIGRAHI','Male','07/07/2000','SUJIT PANIGRAHI','KHANDAGIRI','KHURDHA','Odisha','751002','6372859665','ashu@gmail.com','Human Resource Department','22/05/2020','Morning','6:00','14:00',20000,60,6,4,3),('E010','INDRAJIT PANI','Male','10/09/1980','RAJENDRA PANI','BARIPADA','MAYURBHANJ','Odisha','721021','2587469321','indrajit@email.com','Purchase Department','24/08/2005','Night','22:00','6:00',27000,54,5,6,3),('E011','ASHUTOSH DUTTA','Male','27/07/1985','AMAN DUTTA','UNIT-9','BHUBANESWAR','Odisha','721021','2587469321','duttaashu@email.com','Operations Department','24/08/2015','Day','14:00','22:00',21000,56,6,6,5),('E012','BIJAYALAXMI DAS','Female','15/10/1975','BAIKUNTHA BIHARI DAS','ATHGARH','CUTTACK','Odisha','758694','5486321795','bijaya@gmail.com','Sales Department','05/06/2003','Day','14:00','22:00',32000,66,5,4,2),('E013','SUJIT KUMAR SHARANGI','Male','25/09/1980','SANTUNU KUMAR SHARANGI','BANIBIHAR','KHORDHA','Odisha','751003','5987463212','sujit321@gmail.com','Marketing Department','21/05/2010','Night','22:00','6:00',22000,61,6,5,4),('E014','MANMAYEE MOHAPATRA','Female','27/03/1987','NIMAI CHARAN MOHAPATRA','NAKHARA','CUTTACK','Odisha','751022','9568715884','manmayee21@gmail.com','Human Resource Department','08/06/2008','Day','14:00','22:00',27000,52,5,5,4),('E015','BABULA SWAIN','Male','24/10/1982','AKHINA SWAIN','CHAULIAGANJ','CUTTACK','Odisha','751046','6985234716','sbabula@gmail.com','Purchase Department','25/09/2004','Night','22:00','6:00',35000,55,4,6,3);

/*Table structure for table `tblindustry` */

DROP TABLE IF EXISTS `tblindustry`;

CREATE TABLE `tblindustry` (
  `rgno` varchar(30) DEFAULT NULL,
  `eyr` varchar(30) DEFAULT NULL,
  `nmoi` varchar(30) DEFAULT NULL,
  `hoi` char(30) DEFAULT NULL,
  `loc` varchar(60) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `pno` varchar(20) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL,
  `gstno` int(30) DEFAULT NULL,
  `gstdt` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblindustry` */

insert  into `tblindustry`(`rgno`,`eyr`,`nmoi`,`hoi`,`loc`,`city`,`state`,`pno`,`mail`,`gstno`,`gstdt`) values ('I1','2025','imfa','JOSEF','CHOUDWAR','CUTTACK','ODISHA','4864865168','imfa@gmail.com',6884686,'21/03/31'),('I2','1669','PRAVAT SOFTWIRE LTD','Pravat Ranjan Sahoo','Bhubneswar','Khurdha','Odisha','6985231469','pravatsoftwire@yahoo,com',154626,'21/05/35');

/*Table structure for table `tblleave` */

DROP TABLE IF EXISTS `tblleave`;

CREATE TABLE `tblleave` (
  `refno` char(30) DEFAULT NULL,
  `daten` char(15) DEFAULT NULL,
  `empid` char(30) DEFAULT NULL,
  `empnm` char(30) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `dpnm` char(40) DEFAULT NULL,
  `shnm` char(20) DEFAULT NULL,
  `srttym` char(10) DEFAULT NULL,
  `endtym` char(10) DEFAULT NULL,
  `ltype` char(20) DEFAULT NULL,
  `dfrom` char(15) DEFAULT NULL,
  `dto` char(15) DEFAULT NULL,
  `nar` char(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblleave` */

insert  into `tblleave`(`refno`,`daten`,`empid`,`empnm`,`gender`,`dpnm`,`shnm`,`srttym`,`endtym`,`ltype`,`dfrom`,`dto`,`nar`) values ('1','12/05/2023','E002','SATYA SUNDAR DAS','Male','Operations Department','Morning','6:00','14:00','Marriage Leave','12/05/2023','15/05/2023','Due to my Marriage'),('2','13/05/2023','E006','UMESH CH. NAYAK','Male','Purchase Department','Day','14:00','22:00','Casual Leave(CL)','13/05/2023','16/05/2023','Due to some official work'),('3','12/05/2023','E003','BIJAY KUMAR MOHAPATRA','Male','Sales Department','Morning','6:00','14:00','Paternity Leave','15/05/2023','20/05/2023',''),('4','13/05/2023','E006','UMESH CH. NAYAK','Male','Purchase Department','Day','14:00','22:00','Sick Leave(SL)','16/05/2023','25/05/2023','sick leave'),('5','14/05/2023','E012','BIJAYALAXMI DAS','Female','Sales Department','Day','14:00','22:00','Marriage Leave','18/05/2023','22/05/2023','Due to my marriage'),('6','14/05/2023','E014','MANMAYEE MOHAPATRA','Female','Human Resource Department','Day','14:00','22:00','Marriage Leave','14/05/2023','19/05/2023','Due to my brother marriage'),('7','15/05/2023','E015','BABULA SWAIN','Male','Purchase Department','Night','22:00','6:00','Casual Leave(CL)','15/05/2023','19/05/2023','Due to some hospital work'),('8','13/05/2023','E013','SUJIT KUMAR SHARANGI','Male','Marketing Department','Night','22:00','6:00','Paternity Leave','20/05/2023','30/05/2023','');

/*Table structure for table `tbllogin` */

DROP TABLE IF EXISTS `tbllogin`;

CREATE TABLE `tbllogin` (
  `logid` char(50) DEFAULT NULL,
  `pword` char(30) DEFAULT NULL,
  `hint` char(60) DEFAULT NULL,
  `hintans` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbllogin` */

insert  into `tbllogin`(`logid`,`pword`,`hint`,`hintans`) values ('l001','p001','Name of Your Pet ?','Timy'),('l002','p002','Name of Your Pet ?','Tom'),('l003','p003','Name of Your Pet ?','pet'),('bubu','Boss','Your Mother Nick Name ?','mom');

/*Table structure for table `tblperformance` */

DROP TABLE IF EXISTS `tblperformance`;

CREATE TABLE `tblperformance` (
  `slpno` char(30) DEFAULT NULL,
  `daten` char(20) DEFAULT NULL,
  `empid` char(30) DEFAULT NULL,
  `empnm` char(30) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `dpnm` char(30) DEFAULT NULL,
  `evey` char(30) DEFAULT NULL,
  `job` char(30) DEFAULT NULL,
  `confit` char(30) DEFAULT NULL,
  `cskill` char(30) DEFAULT NULL,
  `iskill` char(30) DEFAULT NULL,
  `intive` char(30) DEFAULT NULL,
  `twork` char(30) DEFAULT NULL,
  `dimprv` char(30) DEFAULT NULL,
  `operfom` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblperformance` */

insert  into `tblperformance`(`slpno`,`daten`,`empid`,`empnm`,`gender`,`dpnm`,`evey`,`job`,`confit`,`cskill`,`iskill`,`intive`,`twork`,`dimprv`,`operfom`) values ('1','16/05/2023','E005','RABINDRA NATH PATRA','Male','Human Resourse Department','RABINDRA NATH PATRA','Meets Expectation','Exceed Expectation','Meets Expectation','Exceed Expectation','Needs Improvement','Exceptional','Meets Expectation','Meets Expectation'),('2','09/06/2023','E002','SATYA SUNDAR DAS','Male','Operations Department','SUBRAT KUMAR ROUT','Exceptional','Exceed Expectation','Exceed Expectation','Exceptional','Meets Expectation','Exceed Expectation','Exceed Expectation','Exceptional'),('3','09/06/2023','E007','LALITA MANTRI','Female','Operations Department','BIJAY KUMAR MOHAPATRA','Meets Expectation','Needs Improvement','Exceptional','Exceed Expectation','Meets Expectation','Exceed Expectation','Exceptional','Needs Improvement'),('4','09/06/2023','E014','MANMAYEE MOHAPATRA','Female','Human Resource Department','BHUBANANANDA SWAIN','Exceptional','Exceed Expectation','Exceed Expectation','Exceptional','Needs Improvement','Meets Expectation','Exceptional','Exceed Expectation'),('5','09/06/2023','E012','BIJAYALAXMI DAS','Female','Sales Department','SATYA SUNDAR DAS','Meets Expectation','Exceptional','Exceptional','Meets Expectation','Meets Expectation','Needs Improvement','Exceed Expectation','Exceptional'),('6','09/05/2023','E001','SUBRAT KUMAR ROUT','Male','Marketing Department','SUBRAT KUMAR ROUT','Exceed Expectation','Needs Improvement','Exceed Expectation','Needs Improvement','Exceptional','Exceed Expectation','Meets Expectation','Exceed Expectation'),('7','09/05/2023','E002','SATYA SUNDAR DAS','Male','Operations Department','SATYA SUNDAR DAS','Meets Expectation','Needs Improvement','Meets Expectation','Exceptional','Exceptional','Meets Expectation','Exceed Expectation','Meets Expectation'),('8','09/05/2023','E003','BIJAY KUMAR MOHAPATRA','Male','Sales Department','BIJAY KUMAR MOHAPATRA','Exceptional','Exceed Expectation','Exceed Expectation','Exceptional','Meets Expectation','Needs Improvement','Needs Improvement','Exceptional');

/*Table structure for table `tblsalaryslip` */

DROP TABLE IF EXISTS `tblsalaryslip`;

CREATE TABLE `tblsalaryslip` (
  `slpno` char(22) DEFAULT NULL,
  `daten` char(20) DEFAULT NULL,
  `mmyyyy` char(20) DEFAULT NULL,
  `empid` char(30) DEFAULT NULL,
  `empnm` char(30) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `phno` char(15) DEFAULT NULL,
  `mail` char(30) DEFAULT NULL,
  `dpnm` char(30) DEFAULT NULL,
  `shnm` char(20) DEFAULT NULL,
  `bsal` float DEFAULT NULL,
  `da` float DEFAULT NULL,
  `daam` float DEFAULT NULL,
  `pf` float DEFAULT NULL,
  `pfam` float DEFAULT NULL,
  `hra` float DEFAULT NULL,
  `hraam` float DEFAULT NULL,
  `tax` float DEFAULT NULL,
  `taxam` float DEFAULT NULL,
  `gsal` float DEFAULT NULL,
  `nsal` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblsalaryslip` */

insert  into `tblsalaryslip`(`slpno`,`daten`,`mmyyyy`,`empid`,`empnm`,`gender`,`phno`,`mail`,`dpnm`,`shnm`,`bsal`,`da`,`daam`,`pf`,`pfam`,`hra`,`hraam`,`tax`,`taxam`,`gsal`,`nsal`) values ('1','01/05/2023','04/2023','E003','BIJAY KUMAR MOHAPATRA','Male','6378521496','bijaya@yahoo.com','Sales Department','Morning',21000,52,10920,5,1050,6,1260,5,1050,33180,31080),('2','03/04/2023','03/2023','E004','BHUBANANANDA SWAIN','Male','7894563215','bhuban@gmail.com','Marketing Department','Night',25000,56,14000,5,1250,4,1000,4,1000,40000,37750),('3','02/02/2023','01/2023','E004','BHUBANANANDA SWAIN','Male','7894563215','bhuban@gmail.com','Marketing Department','Night',25000,56,14000,5,1250,4,1000,4,1000,40000,37750),('4','02/03/2023','02/2023','E005','RABINDRA NATH PATRA','Male','9337569845','nathrabi@yahoo.com','Human Resourse Department','Day',30000,63,18900,6,1800,9,2700,5,1500,51600,48300),('5','10/05/2023','05/2023','E005','RABINDRA NATH PATRA','Male','9337569845','nathrabi@yahoo.com','Human Resourse Department','Day',30000,63,18900,6,1800,9,2700,5,1500,51600,48300),('6','16/07/2023','07/2023','E007','LALITA MANTRI','Female','8249586932','mlalita@email.com','Operations Department','Morning',27000,54,14580,6,1620,4,1080,3,810,42660,40230);

/*Table structure for table `tblshift` */

DROP TABLE IF EXISTS `tblshift`;

CREATE TABLE `tblshift` (
  `sfcd` char(20) DEFAULT NULL,
  `sfnm` char(30) DEFAULT NULL,
  `srttym` char(15) DEFAULT NULL,
  `endtym` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tblshift` */

insert  into `tblshift`(`sfcd`,`sfnm`,`srttym`,`endtym`) values ('A','Morning','6:00','14:00'),('B','Day','14:00','22:00'),('C','Night','22:00','6:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
