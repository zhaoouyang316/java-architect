-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 192.168.1.110    Database: java-architect
-- ------------------------------------------------------
-- Server version	5.7.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jdbc_student`
--

DROP TABLE IF EXISTS `jdbc_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jdbc_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `age` tinyint(3) DEFAULT NULL COMMENT '年龄',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jdbc_student`
--

LOCK TABLES `jdbc_student` WRITE;
/*!40000 ALTER TABLE `jdbc_student` DISABLE KEYS */;
INSERT INTO `jdbc_student` VALUES (5,'张四',1,18,NULL,NULL,NULL),(6,'张四',1,18,NULL,NULL,NULL);
/*!40000 ALTER TABLE `jdbc_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_big_market`
--

DROP TABLE IF EXISTS `t_big_market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_big_market` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 禁用，1 激活',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `big_market_type` tinyint(1) DEFAULT NULL COMMENT '大盘类型 0 上证，1 深证',
  `sh_composite_index` decimal(19,3) DEFAULT NULL COMMENT '大盘指数',
  `trading_number` bigint(20) DEFAULT NULL COMMENT '交易量',
  `trading_price` bigint(20) DEFAULT NULL COMMENT '交易额',
  `volatility_percentage` decimal(19,3) DEFAULT NULL COMMENT '波动比例',
  `volatility_price` decimal(19,3) DEFAULT NULL COMMENT '波动额',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_big_market`
--

LOCK TABLES `t_big_market` WRITE;
/*!40000 ALTER TABLE `t_big_market` DISABLE KEYS */;
INSERT INTO `t_big_market` VALUES (168675171605938177,'2019-04-11 02:54:55',1,'2019-04-11 02:54:55',1,10293.300,214297493,22148700,-1.360,-141.778),(168675988023017473,'2019-04-11 02:58:09',1,'2019-04-11 02:58:09',0,3222.134,1704107,17538445,-0.610,-19.796),(168677237577482241,'2019-04-11 03:03:07',1,'2019-04-11 03:03:07',0,3216.654,1762273,18123525,-0.780,-25.276),(168677238944825345,'2019-04-11 03:03:08',1,'2019-04-11 03:03:08',0,3216.654,1762273,18123525,-0.780,-25.276),(168678164011155457,'2019-04-11 03:06:48',1,'2019-04-11 03:06:48',1,10256.150,232352989,24030284,-1.710,-178.928),(168678375366328321,'2019-04-11 03:07:39',1,'2019-04-11 03:07:39',0,3214.780,1820839,18735416,-0.840,-27.150),(168679576531107841,'2019-04-11 03:12:25',1,'2019-04-11 03:12:25',0,3206.764,1920025,19705307,-1.080,-35.166),(168679938252079105,'2019-04-11 03:13:51',1,'2019-04-11 03:13:51',0,3206.065,1940471,19904446,-1.110,-35.865),(168707571480264705,'2019-04-11 05:03:39',1,'2019-04-11 05:03:39',0,3194.538,2241679,22919160,-1.460,-47.392),(168711254578298881,'2019-04-11 05:18:18',1,'2019-04-11 05:18:18',1,10231.080,296372403,30665087,-1.950,-203.998),(168713714294325249,'2019-04-11 05:28:04',1,'2019-04-11 05:28:04',1,10210.050,306172949,31706820,-2.160,-225.031),(168714835339509761,'2019-04-11 05:32:31',1,'2019-04-11 05:32:31',1,10207.300,310292445,32137282,-2.180,-227.778);
/*!40000 ALTER TABLE `t_big_market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stock_analysis`
--

DROP TABLE IF EXISTS `t_stock_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_stock_analysis` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 禁用，1 激活',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `buy_time` varchar(200) DEFAULT NULL COMMENT '买入时间',
  `sell_time` varchar(200) DEFAULT NULL COMMENT '卖出时间',
  `stock_name` varchar(200) DEFAULT NULL COMMENT '策略名称',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stock_analysis`
--

LOCK TABLES `t_stock_analysis` WRITE;
/*!40000 ALTER TABLE `t_stock_analysis` DISABLE KEYS */;
INSERT INTO `t_stock_analysis` VALUES (168707356354412544,'2019-04-11 05:02:48',1,'2019-04-11 05:02:48','01:00','9:24','热门行业板块，换手率高，涨幅低，绩优股'),(168674780231237632,'2019-04-11 02:53:21',1,'2019-04-11 02:53:21','11:00','9:24','热门行业板块，涨幅中上，涨幅大于5%，绩优股');
/*!40000 ALTER TABLE `t_stock_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stock_analysis_record`
--

DROP TABLE IF EXISTS `t_stock_analysis_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_stock_analysis_record` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 禁用，1 激活',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `broader_market_status` int(11) DEFAULT NULL COMMENT '状态 0 大盘跌 1 大盘涨',
  `settlement_price` decimal(19,3) DEFAULT NULL COMMENT '结算价',
  `stock_analysis_id` bigint(20) DEFAULT NULL COMMENT '策略编号',
  `stock_name` varchar(200) DEFAULT NULL COMMENT '策略名称',
  `total_settlement` decimal(19,3) DEFAULT NULL COMMENT '总结余',
  `yesterday_settlement` decimal(19,3) DEFAULT NULL COMMENT '上日结算',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stock_analysis_record`
--

LOCK TABLES `t_stock_analysis_record` WRITE;
/*!40000 ALTER TABLE `t_stock_analysis_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_stock_analysis_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stock_price_record`
--

DROP TABLE IF EXISTS `t_stock_price_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_stock_price_record` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 0 禁用，1 激活',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `big_market_id` bigint(20) DEFAULT NULL COMMENT '大盘编号',
  `big_market_type_enum` tinyint(1) DEFAULT NULL COMMENT '大盘类型 0 上证，1 深证',
  `broader_market_status` tinyint(1) DEFAULT NULL COMMENT '状态 0 大盘跌 1 大盘涨',
  `buy_five_number` bigint(20) DEFAULT NULL COMMENT '买五量',
  `buy_five_price` decimal(19,3) DEFAULT NULL COMMENT '买五价',
  `buy_four_number` bigint(20) DEFAULT NULL COMMENT '买四量',
  `buy_four_price` decimal(19,3) DEFAULT NULL COMMENT '买四价',
  `buy_one_number` bigint(20) DEFAULT NULL COMMENT '买一量',
  `buy_one_price` decimal(19,3) DEFAULT NULL COMMENT '买一价',
  `buy_one_price_first` decimal(19,3) DEFAULT NULL COMMENT '买一价',
  `buy_three_number` bigint(20) DEFAULT NULL COMMENT '买三量',
  `buy_three_price` decimal(19,3) DEFAULT NULL COMMENT '买三价',
  `buy_two_number` bigint(20) DEFAULT NULL COMMENT '买二量',
  `buy_two_price` decimal(19,3) DEFAULT NULL COMMENT '买二价',
  `high_price` decimal(19,3) DEFAULT NULL COMMENT '今日最高价',
  `lower_price` decimal(19,3) DEFAULT NULL COMMENT '今日最低价',
  `position_number` bigint(20) DEFAULT NULL COMMENT '持仓数量',
  `positions_status` tinyint(1) DEFAULT NULL COMMENT '状态 0 平仓 1 持仓',
  `sell_five_number` bigint(20) DEFAULT NULL COMMENT '卖五量',
  `sell_five_price` decimal(19,3) DEFAULT NULL COMMENT '卖五价',
  `sell_four_number` bigint(20) DEFAULT NULL COMMENT '卖四量',
  `sell_four_price` decimal(19,3) DEFAULT NULL COMMENT '卖四价',
  `sell_one_number` bigint(20) DEFAULT NULL COMMENT '卖一量',
  `sell_one_price` decimal(19,3) DEFAULT NULL COMMENT '卖一价',
  `sell_one_price_first` decimal(19,3) DEFAULT NULL COMMENT '卖一价',
  `sell_three_number` bigint(20) DEFAULT NULL COMMENT '卖三量',
  `sell_three_price` decimal(19,3) DEFAULT NULL COMMENT '卖三价',
  `sell_two_number` bigint(20) DEFAULT NULL COMMENT '卖二量',
  `sell_two_price` decimal(19,3) DEFAULT NULL COMMENT '卖二价',
  `spot_price` decimal(19,3) DEFAULT NULL COMMENT '当前价格',
  `stock_analysis_id` bigint(20) DEFAULT NULL COMMENT '策略编号',
  `stock_code` varchar(200) DEFAULT NULL COMMENT '股票代码',
  `stock_name` varchar(200) DEFAULT NULL COMMENT '策略名称',
  `time` datetime DEFAULT NULL COMMENT '日期',
  `today_price` decimal(19,3) DEFAULT NULL COMMENT '今日开盘价',
  `turnover` decimal(19,3) DEFAULT NULL COMMENT '成交额',
  `volume` bigint(20) DEFAULT NULL COMMENT '成交量',
  `yesterday_price` decimal(19,3) DEFAULT NULL COMMENT '昨日收盘价',
  `yesterday_settlement` decimal(19,3) DEFAULT NULL COMMENT '上日结算',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stock_price_record`
--

LOCK TABLES `t_stock_price_record` WRITE;
/*!40000 ALTER TABLE `t_stock_price_record` DISABLE KEYS */;
INSERT INTO `t_stock_price_record` VALUES (168679938558263296,'2019-04-11 03:13:51',0,'2019-04-11 03:13:51',168679938252079105,0,0,59400,7.040,61400,7.050,1600,7.080,7.080,58300,7.060,86700,7.070,7.320,7.030,800,1,82900,7.130,22700,7.120,28900,7.090,7.090,62100,7.110,35200,7.100,7.080,168674780231237632,'600787','中储股份','2019-04-11 03:13:42',7.300,90711188.000,12665518,7.270,0.000,0),(168679576795348992,'2019-04-11 03:12:25',0,'2019-04-11 03:12:25',168679576531107841,0,0,19300,10.610,16500,10.620,19900,10.650,10.650,28100,10.630,60500,10.640,11.290,10.270,1000,1,27100,10.700,4700,10.690,40000,10.660,10.660,15200,10.680,11700,10.670,10.650,168674780231237632,'600733','北汽蓝谷','2019-04-11 03:12:11',10.350,774404842.000,71943571,10.350,0.000,0),(168678375630569472,'2019-04-11 03:07:39',0,'2019-04-11 03:07:39',168678375366328321,0,0,3300,13.070,7900,13.080,1300,13.110,13.110,2300,13.090,4300,13.100,13.500,12.250,800,1,300,13.160,300,13.150,2600,13.120,13.120,12700,13.140,8500,13.130,13.110,168674780231237632,'600965','福成股份','2019-04-11 03:07:28',12.320,77959738.000,6122876,12.440,0.000,0),(168678164250230784,'2019-04-11 03:06:48',0,'2019-04-11 03:06:48',168678164011155457,1,0,28890,23.600,12400,23.610,65620,23.640,23.640,18880,23.620,2610,23.630,24.500,22.010,400,1,2980,23.720,340,23.710,13400,23.650,23.650,7380,23.700,100,23.680,23.650,168674780231237632,'002458','益生股份','2019-04-11 03:06:42',22.490,707596830.460,30323530,22.500,0.000,0),(168707571744505856,'2019-04-11 05:03:40',0,'2019-04-11 05:03:40',168707571480264705,0,0,8300,12.590,4300,12.600,100,12.630,12.630,4800,12.610,4700,12.620,12.750,12.190,700,1,30200,12.690,23800,12.680,900,12.650,12.650,12000,12.670,13900,12.660,12.650,168707356354412544,'603787','新日股份','2019-04-11 05:03:31',12.210,46765275.000,3744395,12.240,0.000,0),(168711254976757760,'2019-04-11 05:18:18',0,'2019-04-11 05:18:18',168711254578298881,1,0,27200,6.190,76100,6.200,69200,6.230,6.230,16900,6.210,10600,6.220,6.420,6.110,1600,1,88500,6.280,48800,6.270,44300,6.240,6.240,51400,6.260,109400,6.250,6.230,168707356354412544,'000980','众泰汽车','2019-04-11 05:18:09',6.130,243398376.560,38822784,6.110,0.000,0),(168713714566955008,'2019-04-11 05:28:04',0,'2019-04-11 05:28:04',168713714294325249,1,0,37000,17.280,35600,17.290,500,17.330,17.330,41500,17.300,12100,17.310,17.850,16.450,500,1,6000,17.380,100,17.370,31300,17.340,17.340,22800,17.360,105700,17.350,17.340,168707356354412544,'000723','美锦能源','2019-04-11 05:27:57',16.450,3225865202.030,188027257,16.440,0.000,0),(168714835599556608,'2019-04-11 05:32:31',0,'2019-04-11 05:32:31',168714835339509761,1,0,1900,14.040,11900,14.050,6600,14.080,14.080,7300,14.060,9800,14.070,14.340,13.400,700,1,4200,14.130,52000,14.120,10400,14.090,14.090,5200,14.110,10300,14.100,14.090,168707356354412544,'002639','雪人股份','2019-04-11 05:32:24',13.440,650648742.670,46552950,13.880,0.000,0);
/*!40000 ALTER TABLE `t_stock_price_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-11 13:42:36
