-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 29-09-2020 a las 07:51:47
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vitniksanluis`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE IF NOT EXISTS `articulos` (
  `letra` varchar(4) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `tipo` tinyint(3) UNSIGNED NOT NULL,
  `precio_unitario` decimal(10,2) UNSIGNED NOT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`letra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`letra`, `nombre`, `tipo`, `precio_unitario`, `active_row`) VALUES
('AWXT', 'SHORT', 3, '1500.00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos_devueltos`
--

DROP TABLE IF EXISTS `articulos_devueltos`;
CREATE TABLE IF NOT EXISTS `articulos_devueltos` (
  `ejemplar` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `letra` varchar(4) NOT NULL,
  `motivo` tinyint(3) UNSIGNED NOT NULL,
  `recomprado` tinyint(1) NOT NULL DEFAULT '0',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ejemplar`),
  KEY `letra` (`letra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camps`
--

DROP TABLE IF EXISTS `camps`;
CREATE TABLE IF NOT EXISTS `camps` (
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `alias` varchar(60) DEFAULT NULL,
  `mes` tinyint(3) UNSIGNED NOT NULL,
  `year` int(10) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cod_cat` int(10) UNSIGNED DEFAULT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`nro_camp`),
  KEY `cod_cat` (`cod_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `camps`
--

INSERT INTO `camps` (`nro_camp`, `nombre`, `alias`, `mes`, `year`, `fecha_registro`, `cod_cat`, `active_row`) VALUES
(219, 'JULY-2020', 'NUEVOS JEANS DE INVIERNO', 7, 2020, '2020-08-07 11:48:29', NULL, 1),
(220, 'AUGUST-2020', 'BUSOS INVIERNO NUEVOS', 8, 2020, '2020-08-07 09:53:25', NULL, 1),
(221, 'SEPTEMBER-2020', NULL, 9, 2020, '2020-08-29 11:53:13', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogos`
--

DROP TABLE IF EXISTS `catalogos`;
CREATE TABLE IF NOT EXISTS `catalogos` (
  `cod` int(10) UNSIGNED NOT NULL,
  `stock_inicial` int(10) UNSIGNED NOT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `precio` decimal(10,2) UNSIGNED NOT NULL,
  `link` varchar(500) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes_preferenciales`
--

DROP TABLE IF EXISTS `clientes_preferenciales`;
CREATE TABLE IF NOT EXISTS `clientes_preferenciales` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `dni` bigint(20) UNSIGNED DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `lugar` varchar(60) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `tel` bigint(20) UNSIGNED DEFAULT NULL,
  `id_lider` int(10) UNSIGNED DEFAULT NULL,
  `es_lider` tinyint(1) NOT NULL DEFAULT '0',
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_baja` timestamp NULL DEFAULT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`),
  UNIQUE KEY `dni` (`dni`),
  KEY `id_lider` (`id_lider`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes_preferenciales`
--

INSERT INTO `clientes_preferenciales` (`id_cp`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`, `id_lider`, `es_lider`, `fecha_registro`, `fecha_baja`, `active_row`) VALUES
(0, NULL, 'Test Name 0', 'Test Lastname 0', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(2, NULL, 'Test Name 2', 'Test Lastname 2', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(12, NULL, 'Test Name 12', 'Test Lastname 12', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(15, NULL, 'Test Name 15', 'Test Lastname 15', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(33, NULL, 'Test Name 33', 'Test Lastname 33', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(57, NULL, 'Test Name 57', 'Test Lastname 57', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(82, NULL, 'Test Name 82', 'Test Lastname 82', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(90, NULL, 'Test Name 90', 'Test Lastname 90', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(113, NULL, 'Test Name 113', 'Test Lastname 113', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(114, NULL, 'Test Name 114', 'Test Lastname 114', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(139, NULL, 'Test Name 139', 'Test Lastname 139', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(166, NULL, 'Test Name 166', 'Test Lastname 166', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(183, NULL, 'Test Name 183', 'Test Lastname 183', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(221, NULL, 'Test Name 221', 'Test Lastname 221', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(240, NULL, 'Test Name 240', 'Test Lastname 240', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(269, NULL, 'Test Name 269', 'Test Lastname 269', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(276, NULL, 'Test Name 276', 'Test Lastname 276', NULL, NULL, NULL, NULL, NULL, 1, '2020-09-29 07:40:36', NULL, 1),
(288, NULL, 'Test Name 288', 'Test Lastname 288', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(354, NULL, 'Test Name 354', 'Test Lastname 354', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(356, NULL, 'Test Name 356', 'Test Lastname 356', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(406, NULL, 'Test Name 406', 'Test Lastname 406', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(422, NULL, 'Test Name 422', 'Test Lastname 422', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(435, NULL, 'Test Name 435', 'Test Lastname 435', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(468, NULL, 'Test Name 468', 'Test Lastname 468', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(476, NULL, 'Test Name 476', 'Test Lastname 476', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(516, NULL, 'Test Name 516', 'Test Lastname 516', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(535, NULL, 'Test Name 535', 'Test Lastname 535', NULL, NULL, NULL, NULL, NULL, 1, '2020-09-29 07:40:36', NULL, 1),
(549, NULL, 'Test Name 549', 'Test Lastname 549', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(556, NULL, 'Test Name 556', 'Test Lastname 556', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(557, NULL, 'Test Name 557', 'Test Lastname 557', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(558, NULL, 'Test Name 558', 'Test Lastname 558', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(561, NULL, 'Test Name 561', 'Test Lastname 561', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(567, NULL, 'Test Name 567', 'Test Lastname 567', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(580, NULL, 'Test Name 580', 'Test Lastname 580', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(589, NULL, 'Test Name 589', 'Test Lastname 589', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(619, NULL, 'Test Name 619', 'Test Lastname 619', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(639, NULL, 'Test Name 639', 'Test Lastname 639', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(647, NULL, 'Test Name 647', 'Test Lastname 647', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(665, NULL, 'Test Name 665', 'Test Lastname 665', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(686, NULL, 'Test Name 686', 'Test Lastname 686', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(694, NULL, 'Test Name 694', 'Test Lastname 694', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(703, NULL, 'Test Name 703', 'Test Lastname 703', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(704, NULL, 'Test Name 704', 'Test Lastname 704', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(706, NULL, 'Test Name 706', 'Test Lastname 706', NULL, NULL, NULL, NULL, 535, 0, '2020-09-29 07:40:37', NULL, 1),
(732, NULL, 'Test Name 732', 'Test Lastname 732', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(745, NULL, 'Test Name 745', 'Test Lastname 745', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(751, NULL, 'Test Name 751', 'Test Lastname 751', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(757, NULL, 'Test Name 757', 'Test Lastname 757', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(771, NULL, 'Test Name 771', 'Test Lastname 771', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(792, NULL, 'Test Name 792', 'Test Lastname 792', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(796, NULL, 'Test Name 796', 'Test Lastname 796', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(803, NULL, 'Test Name 803', 'Test Lastname 803', NULL, NULL, NULL, NULL, 535, 0, '2020-09-29 07:40:37', NULL, 1),
(804, NULL, 'Test Name 804', 'Test Lastname 804', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(811, NULL, 'Test Name 811', 'Test Lastname 811', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(815, NULL, 'Test Name 815', 'Test Lastname 815', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(823, NULL, 'Test Name 823', 'Test Lastname 823', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(830, NULL, 'Test Name 830', 'Test Lastname 830', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(832, NULL, 'Test Name 832', 'Test Lastname 832', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(835, NULL, 'Test Name 835', 'Test Lastname 835', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(839, NULL, 'Test Name 839', 'Test Lastname 839', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(840, NULL, 'Test Name 840', 'Test Lastname 840', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(844, NULL, 'Test Name 844', 'Test Lastname 844', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(845, NULL, 'Test Name 845', 'Test Lastname 845', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(853, NULL, 'Test Name 853', 'Test Lastname 853', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(859, NULL, 'Test Name 859', 'Test Lastname 859', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(869, NULL, 'Test Name 869', 'Test Lastname 869', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(876, NULL, 'Test Name 876', 'Test Lastname 876', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(878, NULL, 'Test Name 878', 'Test Lastname 878', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(887, NULL, 'Test Name 887', 'Test Lastname 887', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(894, NULL, 'Test Name 894', 'Test Lastname 894', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(898, NULL, 'Test Name 898', 'Test Lastname 898', NULL, NULL, NULL, NULL, 535, 0, '2020-09-29 07:40:37', NULL, 1),
(901, NULL, 'Test Name 901', 'Test Lastname 901', NULL, NULL, NULL, NULL, NULL, 1, '2020-09-29 07:40:36', NULL, 1),
(902, NULL, 'Test Name 902', 'Test Lastname 902', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(904, NULL, 'Test Name 904', 'Test Lastname 904', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(907, NULL, 'Test Name 907', 'Test Lastname 907', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(909, NULL, 'Test Name 909', 'Test Lastname 909', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(914, NULL, 'Test Name 914', 'Test Lastname 914', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(915, NULL, 'Test Name 915', 'Test Lastname 915', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(923, NULL, 'Test Name 923', 'Test Lastname 923', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(925, NULL, 'Test Name 925', 'Test Lastname 925', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(929, NULL, 'Test Name 929', 'Test Lastname 929', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(932, NULL, 'Test Name 932', 'Test Lastname 932', NULL, NULL, NULL, NULL, 901, 0, '2020-09-29 07:40:37', NULL, 1),
(933, NULL, 'Test Name 933', 'Test Lastname 933', NULL, NULL, NULL, NULL, 901, 0, '2020-09-29 07:40:37', NULL, 1),
(939, NULL, 'Test Name 939', 'Test Lastname 939', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(941, NULL, 'Test Name 941', 'Test Lastname 941', NULL, NULL, NULL, NULL, 535, 0, '2020-09-29 07:40:37', NULL, 1),
(943, NULL, 'Test Name 943', 'Test Lastname 943', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(944, NULL, 'Test Name 944', 'Test Lastname 944', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(948, NULL, 'Test Name 948', 'Test Lastname 948', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(949, NULL, 'Test Name 949', 'Test Lastname 949', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(958, NULL, 'Test Name 958', 'Test Lastname 958', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(961, NULL, 'Test Name 961', 'Test Lastname 961', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(963, NULL, 'Test Name 963', 'Test Lastname 963', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(965, NULL, 'Test Name 965', 'Test Lastname 965', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(972, NULL, 'Test Name 972', 'Test Lastname 972', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(974, NULL, 'Test Name 974', 'Test Lastname 974', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(975, NULL, 'Test Name 975', 'Test Lastname 975', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(981, NULL, 'Test Name 981', 'Test Lastname 981', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(984, NULL, 'Test Name 984', 'Test Lastname 984', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(985, NULL, 'Test Name 985', 'Test Lastname 985', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(986, NULL, 'Test Name 986', 'Test Lastname 986', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(987, NULL, 'Test Name 987', 'Test Lastname 987', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(989, NULL, 'Test Name 989', 'Test Lastname 989', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(991, NULL, 'Test Name 991', 'Test Lastname 991', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1005, NULL, 'Test Name 1005', 'Test Lastname 1005', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1006, NULL, 'Test Name 1006', 'Test Lastname 1006', NULL, NULL, NULL, NULL, 901, 0, '2020-09-29 07:40:37', NULL, 1),
(1011, NULL, 'Test Name 1011', 'Test Lastname 1011', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1013, NULL, 'Test Name 1013', 'Test Lastname 1013', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1016, NULL, 'Test Name 1016', 'Test Lastname 1016', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1019, NULL, 'Test Name 1019', 'Test Lastname 1019', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1021, NULL, 'Test Name 1021', 'Test Lastname 1021', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1),
(1022, NULL, 'Test Name 1022', 'Test Lastname 1022', NULL, NULL, NULL, NULL, 276, 0, '2020-09-29 07:40:37', NULL, 1),
(1023, NULL, 'Test Name 1023', 'Test Lastname 1023', NULL, NULL, NULL, NULL, NULL, 0, '2020-09-29 07:40:37', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comisiones`
--

DROP TABLE IF EXISTS `comisiones`;
CREATE TABLE IF NOT EXISTS `comisiones` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `cant_actual` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `cant_1` int(10) UNSIGNED NOT NULL DEFAULT '50',
  `cant_2` int(10) UNSIGNED NOT NULL DEFAULT '120',
  `cant_3` int(10) UNSIGNED NOT NULL DEFAULT '200',
  `cant_4` int(10) UNSIGNED NOT NULL DEFAULT '300',
  `nivel_1` smallint(5) UNSIGNED NOT NULL DEFAULT '5',
  `nivel_2` smallint(5) UNSIGNED NOT NULL DEFAULT '8',
  `nivel_3` smallint(5) UNSIGNED NOT NULL DEFAULT '10',
  `nivel_4` smallint(5) UNSIGNED NOT NULL DEFAULT '13',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`,`nro_camp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devoluciones`
--

DROP TABLE IF EXISTS `devoluciones`;
CREATE TABLE IF NOT EXISTS `devoluciones` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `letra` varchar(4) NOT NULL,
  `cant` int(10) UNSIGNED NOT NULL,
  `monto` decimal(10,2) UNSIGNED NOT NULL,
  `motivo` tinyint(3) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `letra` (`letra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entregas_catalogos`
--

DROP TABLE IF EXISTS `entregas_catalogos`;
CREATE TABLE IF NOT EXISTS `entregas_catalogos` (
  `nro_entrega` int(10) UNSIGNED NOT NULL,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `cod_cat` int(10) UNSIGNED NOT NULL,
  `cant` int(10) UNSIGNED NOT NULL,
  `precio` decimal(10,2) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`nro_entrega`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`cod_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `observaciones`
--

DROP TABLE IF EXISTS `observaciones`;
CREATE TABLE IF NOT EXISTS `observaciones` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `observacion` varchar(500) DEFAULT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`,`nro_camp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

DROP TABLE IF EXISTS `pagos`;
CREATE TABLE IF NOT EXISTS `pagos` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `descriptor` varchar(60) DEFAULT NULL,
  `monto` decimal(10,2) NOT NULL,
  `item` tinyint(4) NOT NULL DEFAULT '1',
  `forma` tinyint(4) NOT NULL DEFAULT '1',
  `banco` tinyint(4) NOT NULL DEFAULT '1',
  `estado` tinyint(4) NOT NULL DEFAULT '1',
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE IF NOT EXISTS `pedidos` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `letra` varchar(4) NOT NULL,
  `cant` int(10) UNSIGNED NOT NULL,
  `monto` decimal(10,2) UNSIGNED NOT NULL,
  `fecha_retiro` timestamp NULL DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comisionable` tinyint(1) NOT NULL DEFAULT '1',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `letra` (`letra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recompras`
--

DROP TABLE IF EXISTS `recompras`;
CREATE TABLE IF NOT EXISTS `recompras` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `ejemplar` int(10) UNSIGNED NOT NULL,
  `precio_recompra` decimal(10,2) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `ejemplar` (`ejemplar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `saldos`
--

DROP TABLE IF EXISTS `saldos`;
CREATE TABLE IF NOT EXISTS `saldos` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `pedidos_comisionables` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `pedidos_no_comisionables` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `catalogos` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `recompras` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `pagos` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `devoluciones` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `comision` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`,`nro_camp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `saldos`
--

INSERT INTO `saldos` (`id_cp`, `nro_camp`, `balance`, `pedidos_comisionables`, `pedidos_no_comisionables`, `catalogos`, `recompras`, `pagos`, `devoluciones`, `comision`, `active_row`) VALUES
(0, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(2, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(12, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(15, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(33, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(57, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(82, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(90, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(113, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(114, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(139, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(166, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(183, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(221, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(240, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(269, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(276, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(288, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(354, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(356, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(406, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(422, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(435, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(468, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(476, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(516, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(535, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(549, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(556, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(557, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(558, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(561, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(567, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(580, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(589, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(619, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(639, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(647, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(665, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(686, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(694, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(703, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(704, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(706, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(732, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(745, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(751, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(757, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(771, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(792, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(796, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(803, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(804, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(811, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(815, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(823, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(830, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(832, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(835, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(839, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(840, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(844, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(845, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(853, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(859, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(869, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(876, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(878, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(887, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(894, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(898, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(901, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(902, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(904, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(907, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(909, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(914, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(915, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(923, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(925, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(929, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(932, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(933, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(939, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(941, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(943, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(944, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(948, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(949, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(958, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(961, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(963, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(965, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(972, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(974, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(975, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(981, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(984, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(985, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(986, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(987, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(989, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(991, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1005, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1006, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1011, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1013, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1016, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1019, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1021, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1022, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1),
(1023, 220, '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', '0.00', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos_devueltos`
--
ALTER TABLE `articulos_devueltos`
  ADD CONSTRAINT `articulos_devueltos_ibfk_1` FOREIGN KEY (`letra`) REFERENCES `articulos` (`letra`);

--
-- Filtros para la tabla `camps`
--
ALTER TABLE `camps`
  ADD CONSTRAINT `camps_ibfk_1` FOREIGN KEY (`cod_cat`) REFERENCES `catalogos` (`cod`);

--
-- Filtros para la tabla `clientes_preferenciales`
--
ALTER TABLE `clientes_preferenciales`
  ADD CONSTRAINT `clientes_preferenciales_ibfk_1` FOREIGN KEY (`id_lider`) REFERENCES `clientes_preferenciales` (`id_cp`);

--
-- Filtros para la tabla `comisiones`
--
ALTER TABLE `comisiones`
  ADD CONSTRAINT `comisiones_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `comisiones_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`);

--
-- Filtros para la tabla `devoluciones`
--
ALTER TABLE `devoluciones`
  ADD CONSTRAINT `devoluciones_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `devoluciones_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`),
  ADD CONSTRAINT `devoluciones_ibfk_3` FOREIGN KEY (`letra`) REFERENCES `articulos` (`letra`);

--
-- Filtros para la tabla `entregas_catalogos`
--
ALTER TABLE `entregas_catalogos`
  ADD CONSTRAINT `entregas_catalogos_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `entregas_catalogos_ibfk_2` FOREIGN KEY (`cod_cat`) REFERENCES `catalogos` (`cod`);

--
-- Filtros para la tabla `observaciones`
--
ALTER TABLE `observaciones`
  ADD CONSTRAINT `observaciones_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `observaciones_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `pagos_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`),
  ADD CONSTRAINT `pedidos_ibfk_3` FOREIGN KEY (`letra`) REFERENCES `articulos` (`letra`);

--
-- Filtros para la tabla `recompras`
--
ALTER TABLE `recompras`
  ADD CONSTRAINT `recompras_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `recompras_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`),
  ADD CONSTRAINT `recompras_ibfk_3` FOREIGN KEY (`ejemplar`) REFERENCES `articulos_devueltos` (`ejemplar`);

--
-- Filtros para la tabla `saldos`
--
ALTER TABLE `saldos`
  ADD CONSTRAINT `saldos_ibfk_1` FOREIGN KEY (`id_cp`) REFERENCES `clientes_preferenciales` (`id_cp`),
  ADD CONSTRAINT `saldos_ibfk_2` FOREIGN KEY (`nro_camp`) REFERENCES `camps` (`nro_camp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
