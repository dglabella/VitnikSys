-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 16-03-2021 a las 20:19:53
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
  `nombre` varchar(100) NOT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`letra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`letra`, `nombre`, `active_row`) VALUES
('AIYZ', 'SHORT INDIGO  CORTE JEAN - T.L', 1),
('AOUN', 'REMERA BLANCO  CON TAJOS LATERALES - T.S', 1),
('AOUO', 'REMERA BLANCO  CON TAJOS LATERALES - T.M', 1),
('AOUQ', 'REMERA NEGRO  CON TAJOS LATERALES - T.S', 1),
('AOUR', 'REMERA NEGRO  CON TAJOS LATERALES - T.M', 1),
('AOUY', 'REMERA FRANCIA  CON TAJOS LATERALES - T.L', 1),
('AOVA', 'REMERA LIMÓN  CON TAJOS LATERALES - T.M', 1),
('AOVD', 'REMERA VERDE ESMERALDA  CON TAJOS LATERALES - T.M', 1),
('AOVE', 'REMERA VERDE ESMERALDA  CON TAJOS LATERALES - T.L', 1),
('APKM', 'MUSCULOSA BLANCO  CON TAJOS LATERALES - T.M', 1),
('APKP', 'MUSCULOSA NEGRO  CON TAJOS LATERALES - T.M', 1),
('APKT', 'MUSCULOSA VERDE ESMERALDA  CON TAJOS LATERALES - T.L', 1),
('APKW', 'MUSCULOSA NARANJA FLUO  CON TAJOS LATERALES - T.L', 1),
('AQQB', 'MALLA NEGRO  ENTERIZA CON VOLADOS - T.3', 1),
('AUIM', 'SHORT NEGRO  COMBINADO CON POWER SOFT SILVER - T.M', 1),
('AWLY', 'LEGGING NEGRO  ELÁSTICO CON LOGO Y REFLEX - T.1', 1),
('AWLZ', 'LEGGING NEGRO  ELÁSTICO CON LOGO Y REFLEX - T.2', 1),
('AXJD', 'REMERA NARANJA FLUO  CON TAJOS LATERALES - T.M', 1),
('AXXV', 'TOP NEGRO  CON RECORTE DE MICROTUL AL TONO - T.1', 1),
('AYFY', 'REMERA NEGRO  BÁSICA - T.M', 1),
('AYRF', 'CULOTELESS BLANCO  ALTO CON PUNTILLA - T.3', 1),
('AYRN', 'CULOTTELESS NEGRO  ALTO CON PUNTILLA - T.3', 1),
('AYRZ', 'TOP NEGRO  CON TIRAS CRUZADAS - T.2', 1),
('AYSN', 'TOP GRIS MELANGE OSCURO  DEPORTIVO CON TIRAS  T.1', 1),
('AYUX', 'VEDETINA NEGRO  DE BAÑO TIRO ALTO - T.2', 1),
('AYXC', '\"GORRA VERDE MILITAR  PRINT CAMUFLADO \"\"PLAY\"\" - T.UNICO-TU\"', 1),
('AZMK', 'SKUNZIE LIMÓN - T.UNICO-TU', 1),
('BABO', 'TOP FUCSIA  ELÁSTICO CON LOGO EN CINTURA - T.2', 1),
('BABQ', '\"REMERA FUCSIA  CON CINTA SUPERPUESTA \"\"VK NEVER STOP\"\" - T.M\"', 1),
('BAKV', 'VEDETINA NEGRO  ALTA CAVADA CON ELÁSTICO ANTIDESLIZANTE - T.', 1),
('BAMB', 'TOP LILA  DEPORTIVO SUBLIMADO FLORES - T.2', 1),
('BAMC', 'TOP LILA  DEPORTIVO SUBLIMADO FLORES - T.3', 1),
('BARU', 'BOXER NEGRO  BÁSICO  - T.1', 1),
('BARW', 'BOXER NEGRO  BÁSICO  - T.3', 1),
('BATN', 'LEGGING NEGRO  CON BOLSILLO PORTA CELULAR EN LATERAL - T.2', 1),
('BBIO', 'REMERA FUCSIA  BÁSICA - T.M', 1),
('BBIP', 'REMERA FUCSIA  BÁSICA - T.L', 1),
('BBIT', 'REMERA FRANCIA  BÁSICA - T.S', 1),
('BBIU', 'REMERA FRANCIA  BÁSICA - T.M', 1),
('BBIV', 'REMERA FRANCIA  BÁSICA - T.L', 1),
('BBJC', 'REMERA VERDE ENERGIA  BÁSICA - T.S', 1),
('BBJD', 'REMERA VERDE ENERGIA  BÁSICA - T.M', 1),
('BBJE', 'REMERA VERDE ENERGIA  BÁSICA - T.L', 1),
('BBJI', 'MUSCULOSA VERDE FLUO  BÁSICA - T.S', 1),
('BBJJ', 'MUSCULOSA VERDE FLUO  BÁSICA - T.M', 1),
('BBJN', 'MUSCULOSA CORAL FLUO  BÁSICA - T.L', 1),
('BBJP', 'MUSCULOSA FRANCIA MELANGE  BÁSICA - T.M', 1),
('BBKS', 'CICLISTA NEGRO  BÁSICO - T.2', 1),
('BBKW', 'CAPRI NEGRO  CON BOLSILLOS LATERALES EN RED - T.2', 1),
('BBKX', 'CAPRI NEGRO  CON BOLSILLOS LATERALES EN RED - T.3', 1),
('BBKY', 'CAPRI NEGRO  PRINT TROPICAL  - T.1', 1),
('BBLQ', 'TOP NEGRO  COMBINADO TIRAS CRUZADAS - T.2', 1),
('BBMF', 'REMERA BLANCO  CON VIVO A CONTRATONO Y BOLSILLO - T.L', 1),
('BBMV', 'REMERA NEGRO  COLOR-BLOCK ESTAMPA RAYAS  - T.L', 1),
('BBNK', 'REMERA AZUL NAVY  CON RECORTES COMBINADOS A CONTRATONO - T.M', 1),
('BBNX', 'REMERA VERDE MUSGO  CUELLO POLO CON CINTA EN HOMBROS - T.L', 1),
('BBNY', 'REMERA VERDE MUSGO  CUELLO POLO CON CINTA EN HOMBROS - T.XL', 1),
('BBOV', 'PANTALON NEGRO  CROPPED COMBINADO CON RED - T.S', 1),
('BBOW', 'PANTALON NEGRO  CROPPED COMBINADO CON RED - T.M', 1),
('BBPR', 'BUZO NEGRO  COMBINADO CON RED - T.S', 1),
('BBPT', 'BUZO NEGRO  COMBINADO CON RED - T.L', 1),
('BBRT', '\"REMERA NARANJA FLUO  SUBLIMADO \"\"GO FOR IT VK\"\" - T.XL\"', 1),
('BBRU', 'MUSCULOSA NEGRO  COMBINADA CON TRANSPARENCIAS - T.S', 1),
('BBSB', 'MUSCULOSA NEGRO  DELATERO COMBINADO JASPEADO - T.M', 1),
('BBSH', 'MUSCULOSA NEGRO  DELANTERO PRINT CAMUFLADO - T.M', 1),
('BBSR', 'MUSCULOSA GRIS MELANGE OSCURO  COMBINADA SISAS CAVADAS - T.M', 1),
('BBSZ', 'REMERA ROJO  HOMBROS DESCUBIERTOS - T.L', 1),
('BBTL', 'MUSCULOSA NEGRO  ESCOTE EN V - T.L', 1),
('BBTO', 'MUSCULOSA AMARILLO  ESCOTE EN V - T.L', 1),
('BBTX', 'MUSCULOSA VERDE ESMERALDA  ESCOTE EN V - T.L', 1),
('BBUS', '\"LEGGING NEGRO  CON FRANJA COMBINADA \"\"VITNIK LIFE POWER\"\" - T.\"', 1),
('BBUZ', 'SHORT BLANCO  CON FALDA COMBINADA - T.2', 1),
('BBWL', 'REMERA FRANCIA  MANGAS COMBINADAS CON RECORTES - T.L', 1),
('BBWZ', 'SHORT FRANCIA  DEPORTIVO RECORTES COMBINADOS - T.L', 1),
('BBXA', 'SHORT FRANCIA  DEPORTIVO RECORTES COMBINADOS - T.XL', 1),
('BBXE', 'SHORT GRIS PLATINO  DEPORTIVO PIERNA COMBINADA - T.XL', 1),
('BBYL', 'SHORT NEGRO  CON LAZO PRINT LIBERTY - T.L', 1),
('BCBM', 'PANTALON GRIS MELANGE OSCURO  CON RECORTES Y CINTA LATERAL -', 1),
('BCCN', 'CAMISOLA BLANCO  CON PUNTILLA EN MANGAS - T.M', 1),
('BCDC', '\"REMERA GRIS MELANGE  \"\"CREATING NEW DREAMS\"\" - T.M\"', 1),
('BCDS', 'REMERA VERDE ENERGIA  CANESÚ EN MICROTUL NEGRO - T.L', 1),
('BCDZ', '\"REMERA GRIS MELANGE OSCURO  \"\"VITNIK LIFE POWER\"\" - T.M\"', 1),
('BCEA', '\"REMERA GRIS MELANGE OSCURO  \"\"VITNIK LIFE POWER\"\" - T.L\"', 1),
('BCEB', '\"REMERA SANDIA  \"\"VITNIK LIFE POWER\"\" - T.S\"', 1),
('BCED', '\"REMERA SANDIA  \"\"VITNIK LIFE POWER\"\" - T.L\"', 1),
('BCEE', 'REMERA GRIS MELANGE OSCURO  CON TIRA DE LENTEJUELAS EN MANGA', 1),
('BCEK', 'CAMISOLA ARENA  CON LAZO EN PUÑOS  T.1', 1),
('BCEW', '\"REMERA GRIS MELANGE  \"\"FIGHT FOR MORE BREAK BARRIERS\"\" - T.L\"', 1),
('BCFU', 'REMERA GRIS MELANGE OSCURO  PALMERAS SUBLIMADO DEGRADE - T.L', 1),
('BCFX', 'REMERA GRIS MELANGE OSCURO  COLOR-BLOCK CON TEXTO SOBRETONO ', 1),
('BCGB', '\"REMERA GRIS MELANGE OSCURO  \"\"VTNK LIVE IN SIN\"\" - T.M\"', 1),
('BCGZ', 'MUSCULOSA NEGRO  CON CANESÚ EN MICROTUL - T.S', 1),
('BCHC', '\"MUSCULOSA NEGRO  \"\"LOVE\"\" - T.S\"', 1),
('BCHF', 'VESTIDO NEGRO  CAMISERO - T.S', 1),
('BCHH', 'VESTIDO NEGRO  CAMISERO - T.L', 1),
('BCHL', 'MUSCULOSA GRIS MELANGE OSCURO  CANESÚ EN MICROTUL NEGRO - T.', 1),
('BCHW', 'MUSCULOSA VERDE ESMERALDA  CON MICROTUL NEGRO EN ESPALDA - T', 1),
('BCIF', '\"MUSCULOSA GRIS MELANGE OSCURO  \"\"WILD AND FREE\"\" - T.L\"', 1),
('BCJF', 'MUSCULOSA FUCSIA FLUO  BRETELES CON ELÁSTICO  T.L', 1),
('BCJH', 'MUSCULOSA LIMÓN  BRETELES CON ELÁSTICO - T.M', 1),
('BCKE', 'MUSCULOSA NEGRO  CON DETALLE DE TACHAS EN BRETELES - T.S', 1),
('BCLP', 'SHORT ROJO  CORTE JEAN - T.L', 1),
('BCLX', '\"BERMUDA NEGRO  BÁSICA \"\"ORIGINALS\"\" - T.M\"', 1),
('BCMC', 'REMERÓN GRIS PLOMO  RECORTES COMBINADOS - T.L', 1),
('BCMO', 'SKUNZIE TURQUESA - T.UNICO-TU', 1),
('BCNA', 'SHORT NEGRO  DE BAÑO FLORES FONDO NEGRO - T.M', 1),
('BCNB', 'SHORT NEGRO  DE BAÑO FLORES FONDO NEGRO - T.L', 1),
('BCNS', 'RIÑONERA NEGRO  DETALLE RÉFLEX - T.UNICO-TU', 1),
('BCOI', 'BOXER NEGRO  DE MUJER CON RECORTES ESTAMPADOS - T.3', 1),
('BCPE', '\"MUSCULOSA NEGRO  \"\"SPORT 382\"\" - T.S\"', 1),
('BCQK', 'CORPIÑO TURQUESA  DE BAÑO PRINT LIMONES - T.1', 1),
('BCRH', 'CAMISÓN CRUDO  LENCERO CON PUNTILLAS - T.3', 1),
('BCRV', 'BOXER GRIS MELANGE OSCURO  ELÁSTICO SUBLIMADO - T.3', 1),
('BECQ', 'REMERA LIMÓN  DELANTERO SUBLIMADO DEGRADE PUNTOS - T.S', 1),
('BECW', 'REMERA BLANCO  DELANTERO SUBLIMADO DEGRADE PUNTOS - T.S', 1),
('BEDJ', 'VESTIDO NEGRO  CON CINTA PARA ANUDAR - T.2', 1),
('BEDV', 'MUSCULOSA VERDE  CON RECORTE DE MICROTUL - T.S', 1),
('BEJW', 'CICLISTA VERDE INGLES  SUBLIMADO REPTIL - T.2', 1),
('ZCUI', 'CUADERNO INSTITUCIONAL', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos_devueltos`
--

DROP TABLE IF EXISTS `articulos_devueltos`;
CREATE TABLE IF NOT EXISTS `articulos_devueltos` (
  `ejemplar` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cod_pedido` int(10) UNSIGNED NOT NULL,
  `motivo` tinyint(3) UNSIGNED NOT NULL,
  `recomprado` tinyint(1) NOT NULL DEFAULT '0',
  `reenviado_vitnik` tinyint(1) NOT NULL DEFAULT '0',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ejemplar`),
  KEY `cod_pedido` (`cod_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulos_devueltos`
--

INSERT INTO `articulos_devueltos` (`ejemplar`, `cod_pedido`, `motivo`, `recomprado`, `reenviado_vitnik`, `active_row`) VALUES
(63, 2825, 15, 1, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camps`
--

DROP TABLE IF EXISTS `camps`;
CREATE TABLE IF NOT EXISTS `camps` (
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `cod_cat` int(10) UNSIGNED DEFAULT NULL,
  `alias` varchar(60) DEFAULT NULL,
  `mes` tinyint(3) UNSIGNED NOT NULL,
  `year` int(10) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`nro_camp`),
  UNIQUE KEY `mes` (`mes`,`year`),
  KEY `cod_cat` (`cod_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `camps`
--

INSERT INTO `camps` (`nro_camp`, `cod_cat`, `alias`, `mes`, `year`, `fecha_registro`, `active_row`) VALUES
(232, NULL, NULL, 1, 2021, '2021-03-15 06:21:43', 1);

--
-- Disparadores `camps`
--
DROP TRIGGER IF EXISTS `newBalancesPerCamp`;
DELIMITER $$
CREATE TRIGGER `newBalancesPerCamp` AFTER INSERT ON `camps` FOR EACH ROW INSERT INTO `saldos`(`id_cp`,`nro_camp`) SELECT `id_cp`, NEW.nro_camp FROM `clientes_preferenciales` WHERE `active_row` = 1
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogos`
--

DROP TABLE IF EXISTS `catalogos`;
CREATE TABLE IF NOT EXISTS `catalogos` (
  `cod` int(10) UNSIGNED NOT NULL,
  `stock_inicial` int(10) UNSIGNED NOT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `precio` decimal(16,6) UNSIGNED NOT NULL,
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
  `id_lider` int(10) UNSIGNED DEFAULT NULL,
  `dni` bigint(20) UNSIGNED DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `lugar` varchar(60) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `tel` bigint(20) UNSIGNED DEFAULT NULL,
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

INSERT INTO `clientes_preferenciales` (`id_cp`, `id_lider`, `dni`, `nombre`, `apellido`, `lugar`, `fecha_nac`, `email`, `tel`, `es_lider`, `fecha_registro`, `fecha_baja`, `active_row`) VALUES
(0, NULL, NULL, 'Test Name 0', 'Test Lastname 0', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(2, NULL, NULL, 'Test Name 2', 'Test Lastname 2', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(12, NULL, NULL, 'Test Name 12', 'Test Lastname 12', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(15, NULL, NULL, 'Test Name 15', 'Test Lastname 15', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(33, NULL, NULL, 'Test Name 33', 'Test Lastname 33', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(57, NULL, NULL, 'Test Name 57', 'Test Lastname 57', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(82, NULL, NULL, 'Test Name 82', 'Test Lastname 82', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(90, NULL, NULL, 'Test Name 90', 'Test Lastname 90', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(113, NULL, NULL, 'Test Name 113', 'Test Lastname 113', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(114, NULL, NULL, 'Test Name 114', 'Test Lastname 114', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(139, NULL, NULL, 'Test Name 139', 'Test Lastname 139', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(166, NULL, NULL, 'Test Name 166', 'Test Lastname 166', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(183, NULL, NULL, 'Test Name 183', 'Test Lastname 183', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(221, NULL, NULL, 'Test Name 221', 'Test Lastname 221', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(240, NULL, NULL, 'Test Name 240', 'Test Lastname 240', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(269, NULL, NULL, 'Test Name 269', 'Test Lastname 269', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(276, NULL, NULL, 'Test Name 276', 'Test Lastname 276', NULL, NULL, NULL, NULL, 1, '2020-09-30 07:26:59', NULL, 1),
(288, NULL, NULL, 'Test Name 288', 'Test Lastname 288', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(354, NULL, NULL, 'Test Name 354', 'Test Lastname 354', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(356, NULL, NULL, 'Test Name 356', 'Test Lastname 356', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(406, NULL, NULL, 'Test Name 406', 'Test Lastname 406', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(422, NULL, NULL, 'Test Name 422', 'Test Lastname 422', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(435, NULL, NULL, 'Test Name 435', 'Test Lastname 435', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(468, NULL, NULL, 'Test Name 468', 'Test Lastname 468', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(476, NULL, NULL, 'Test Name 476', 'Test Lastname 476', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(516, NULL, NULL, 'Test Name 516', 'Test Lastname 516', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(535, NULL, NULL, 'Test Name 535', 'Test Lastname 535', NULL, NULL, NULL, NULL, 1, '2020-09-30 07:26:59', NULL, 1),
(549, NULL, NULL, 'Test Name 549', 'Test Lastname 549', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(556, NULL, NULL, 'Test Name 556', 'Test Lastname 556', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(557, NULL, NULL, 'Test Name 557', 'Test Lastname 557', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(558, NULL, NULL, 'Test Name 558', 'Test Lastname 558', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(561, NULL, NULL, 'Test Name 561', 'Test Lastname 561', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(567, NULL, NULL, 'Test Name 567', 'Test Lastname 567', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(580, NULL, NULL, 'Test Name 580', 'Test Lastname 580', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(589, NULL, NULL, 'Test Name 589', 'Test Lastname 589', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(619, 276, NULL, 'Test Name 619', 'Test Lastname 619', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(639, NULL, NULL, 'Test Name 639', 'Test Lastname 639', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(647, 276, NULL, 'Test Name 647', 'Test Lastname 647', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(665, 276, NULL, 'Test Name 665', 'Test Lastname 665', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(686, NULL, NULL, 'Test Name 686', 'Test Lastname 686', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(694, NULL, NULL, 'Test Name 694', 'Test Lastname 694', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(703, NULL, NULL, 'Test Name 703', 'Test Lastname 703', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(704, NULL, NULL, 'Test Name 704', 'Test Lastname 704', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(706, 535, NULL, 'Test Name 706', 'Test Lastname 706', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(732, NULL, NULL, 'Test Name 732', 'Test Lastname 732', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(745, NULL, NULL, 'Test Name 745', 'Test Lastname 745', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(751, NULL, NULL, 'Test Name 751', 'Test Lastname 751', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(757, NULL, NULL, 'Test Name 757', 'Test Lastname 757', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(771, NULL, NULL, 'Test Name 771', 'Test Lastname 771', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(792, NULL, NULL, 'Test Name 792', 'Test Lastname 792', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(796, NULL, NULL, 'Test Name 796', 'Test Lastname 796', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(803, 535, NULL, 'Test Name 803', 'Test Lastname 803', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(804, 276, NULL, 'Test Name 804', 'Test Lastname 804', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(811, NULL, NULL, 'Test Name 811', 'Test Lastname 811', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(815, NULL, NULL, 'Test Name 815', 'Test Lastname 815', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(823, NULL, NULL, 'Test Name 823', 'Test Lastname 823', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(830, NULL, NULL, 'Test Name 830', 'Test Lastname 830', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(832, 276, NULL, 'Test Name 832', 'Test Lastname 832', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(835, 276, NULL, 'Test Name 835', 'Test Lastname 835', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(839, NULL, NULL, 'Test Name 839', 'Test Lastname 839', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(840, 276, NULL, 'Test Name 840', 'Test Lastname 840', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(844, NULL, NULL, 'Test Name 844', 'Test Lastname 844', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(845, 276, NULL, 'Test Name 845', 'Test Lastname 845', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(853, NULL, NULL, 'Test Name 853', 'Test Lastname 853', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(859, NULL, NULL, 'Test Name 859', 'Test Lastname 859', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(869, NULL, NULL, 'Test Name 869', 'Test Lastname 869', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(876, NULL, NULL, 'Test Name 876', 'Test Lastname 876', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(878, NULL, NULL, 'Test Name 878', 'Test Lastname 878', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(887, 276, NULL, 'Test Name 887', 'Test Lastname 887', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(894, 276, NULL, 'Test Name 894', 'Test Lastname 894', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(898, 535, NULL, 'Test Name 898', 'Test Lastname 898', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(901, NULL, NULL, 'Test Name 901', 'Test Lastname 901', NULL, NULL, NULL, NULL, 1, '2020-09-30 07:26:59', NULL, 1),
(902, NULL, NULL, 'Test Name 902', 'Test Lastname 902', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(904, NULL, NULL, 'Test Name 904', 'Test Lastname 904', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(907, NULL, NULL, 'Test Name 907', 'Test Lastname 907', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(909, NULL, NULL, 'Test Name 909', 'Test Lastname 909', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(914, NULL, NULL, 'Test Name 914', 'Test Lastname 914', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(915, 276, NULL, 'Test Name 915', 'Test Lastname 915', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(923, NULL, NULL, 'Test Name 923', 'Test Lastname 923', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(925, NULL, NULL, 'Test Name 925', 'Test Lastname 925', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(929, NULL, NULL, 'Test Name 929', 'Test Lastname 929', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(932, 901, NULL, 'Test Name 932', 'Test Lastname 932', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(933, 901, NULL, 'Test Name 933', 'Test Lastname 933', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(939, NULL, NULL, 'Test Name 939', 'Test Lastname 939', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(941, 535, NULL, 'Test Name 941', 'Test Lastname 941', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(943, NULL, NULL, 'Test Name 943', 'Test Lastname 943', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(944, NULL, NULL, 'Test Name 944', 'Test Lastname 944', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(948, NULL, NULL, 'Test Name 948', 'Test Lastname 948', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(949, 276, NULL, 'Test Name 949', 'Test Lastname 949', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(958, NULL, NULL, 'Test Name 958', 'Test Lastname 958', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(961, NULL, NULL, 'Test Name 961', 'Test Lastname 961', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(963, NULL, NULL, 'Test Name 963', 'Test Lastname 963', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(965, NULL, NULL, 'Test Name 965', 'Test Lastname 965', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(972, NULL, NULL, 'Test Name 972', 'Test Lastname 972', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(974, NULL, NULL, 'Test Name 974', 'Test Lastname 974', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(975, 276, NULL, 'Test Name 975', 'Test Lastname 975', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(981, 276, NULL, 'Test Name 981', 'Test Lastname 981', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(984, 276, NULL, 'Test Name 984', 'Test Lastname 984', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(985, 276, NULL, 'Test Name 985', 'Test Lastname 985', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(986, 276, NULL, 'Test Name 986', 'Test Lastname 986', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(987, NULL, NULL, 'Test Name 987', 'Test Lastname 987', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(989, NULL, NULL, 'Test Name 989', 'Test Lastname 989', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(991, NULL, NULL, 'Test Name 991', 'Test Lastname 991', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1005, NULL, NULL, 'Test Name 1005', 'Test Lastname 1005', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1006, 901, NULL, 'Test Name 1006', 'Test Lastname 1006', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1011, NULL, NULL, 'Test Name 1011', 'Test Lastname 1011', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1013, NULL, NULL, 'Test Name 1013', 'Test Lastname 1013', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1016, NULL, NULL, 'Test Name 1016', 'Test Lastname 1016', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1019, NULL, NULL, 'Test Name 1019', 'Test Lastname 1019', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1021, NULL, NULL, 'Test Name 1021', 'Test Lastname 1021', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1022, 276, NULL, 'Test Name 1022', 'Test Lastname 1022', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1),
(1023, NULL, NULL, 'Test Name 1023', 'Test Lastname 1023', NULL, NULL, NULL, NULL, 0, '2020-09-30 07:26:59', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comisiones`
--

DROP TABLE IF EXISTS `comisiones`;
CREATE TABLE IF NOT EXISTS `comisiones` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `cant_actual` int(10) UNSIGNED NOT NULL,
  `nivel_actual` smallint(5) UNSIGNED NOT NULL,
  `cant_min` int(10) UNSIGNED NOT NULL DEFAULT '51',
  `cant_1` int(10) UNSIGNED NOT NULL DEFAULT '101',
  `cant_2` int(10) UNSIGNED NOT NULL DEFAULT '201',
  `cant_3` int(10) UNSIGNED NOT NULL DEFAULT '301',
  `nivel_1` smallint(5) UNSIGNED NOT NULL DEFAULT '5',
  `nivel_2` smallint(5) UNSIGNED NOT NULL DEFAULT '8',
  `nivel_3` smallint(5) UNSIGNED NOT NULL DEFAULT '10',
  `nivel_4` smallint(5) UNSIGNED NOT NULL DEFAULT '13',
  `nivel_fp` int(10) UNSIGNED NOT NULL DEFAULT '5',
  `nivel_otros` int(10) UNSIGNED NOT NULL DEFAULT '5',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`,`nro_camp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comisiones`
--

INSERT INTO `comisiones` (`id_cp`, `nro_camp`, `cant_actual`, `nivel_actual`, `cant_min`, `cant_1`, `cant_2`, `cant_3`, `nivel_1`, `nivel_2`, `nivel_3`, `nivel_4`, `nivel_fp`, `nivel_otros`, `active_row`) VALUES
(276, 232, 129, 10, 51, 101, 201, 301, 8, 10, 12, 13, 5, 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `devoluciones`
--

DROP TABLE IF EXISTS `devoluciones`;
CREATE TABLE IF NOT EXISTS `devoluciones` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `ejemplar` int(10) UNSIGNED NOT NULL,
  `monto` decimal(16,6) UNSIGNED NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `ejemplar` (`ejemplar`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `devoluciones`
--

INSERT INTO `devoluciones` (`cod`, `id_cp`, `nro_camp`, `ejemplar`, `monto`, `fecha_registro`, `active_row`) VALUES
(63, 276, 232, 63, '2039.920000', '2021-03-15 08:12:03', 1);

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
  `precio` decimal(16,6) UNSIGNED NOT NULL,
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

--
-- Volcado de datos para la tabla `observaciones`
--

INSERT INTO `observaciones` (`id_cp`, `nro_camp`, `observacion`, `active_row`) VALUES
(276, 232, '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

DROP TABLE IF EXISTS `pagos`;
CREATE TABLE IF NOT EXISTS `pagos` (
  `cod` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `descriptor` varchar(60) DEFAULT NULL,
  `monto` decimal(16,6) NOT NULL,
  `item` tinyint(4) NOT NULL DEFAULT '1',
  `forma` tinyint(4) NOT NULL DEFAULT '1',
  `banco` tinyint(4) NOT NULL DEFAULT '1',
  `estado` tinyint(4) NOT NULL DEFAULT '1',
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
  `nro_envio` int(10) UNSIGNED NOT NULL,
  `cant` int(10) UNSIGNED NOT NULL,
  `cant_devueltos` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `monto` decimal(16,6) UNSIGNED NOT NULL,
  `fecha_retiro` timestamp NULL DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comisionable` tinyint(1) NOT NULL DEFAULT '1',
  `aumenta_comision` tinyint(1) NOT NULL DEFAULT '1',
  `tipo` tinyint(3) UNSIGNED NOT NULL,
  `precio_unitario` decimal(16,6) UNSIGNED NOT NULL,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `letra` (`letra`)
) ENGINE=InnoDB AUTO_INCREMENT=2827 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`cod`, `id_cp`, `nro_camp`, `letra`, `nro_envio`, `cant`, `cant_devueltos`, `monto`, `fecha_retiro`, `fecha_registro`, `comisionable`, `aumenta_comision`, `tipo`, `precio_unitario`, `active_row`) VALUES
(2701, 840, 232, 'BCFU', 7023799, 1, 0, '1719.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2149.900000', 1),
(2702, 840, 232, 'BCHF', 7023807, 1, 0, '1994.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1994.930000', 1),
(2703, 840, 232, 'AOUN', 7023799, 1, 0, '1359.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1699.900000', 1),
(2704, 840, 232, 'BBJP', 7023799, 1, 0, '919.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1149.900000', 1),
(2705, 840, 232, 'BBIU', 7023799, 1, 0, '1039.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1299.900000', 1),
(2706, 840, 232, 'BBKW', 7023799, 1, 0, '2399.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2999.900000', 1),
(2707, 840, 232, 'BBMF', 7023799, 1, 0, '1559.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2708, 840, 232, 'BBNX', 7023799, 1, 0, '2039.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2549.900000', 1),
(2709, 840, 232, 'BBKY', 7023828, 1, 0, '1574.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1574.950000', 1),
(2710, 840, 232, 'BCEW', 7023828, 1, 0, '974.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '974.950000', 1),
(2711, 840, 232, 'BAMB', 7029643, 1, 0, '999.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1249.900000', 1),
(2712, 840, 232, 'BAMC', 7029643, 1, 0, '999.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1249.900000', 1),
(2713, 840, 232, 'AYRZ', 7023845, 1, 0, '1159.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1449.900000', 1),
(2714, 840, 232, 'BCDS', 7023845, 1, 0, '1319.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1649.900000', 1),
(2715, 840, 232, 'BCMC', 7023857, 1, 0, '999.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '999.950000', 1),
(2716, 840, 232, 'AWLZ', 7023847, 1, 0, '2679.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '3349.900000', 1),
(2717, 840, 232, 'BCQK', 7023819, 1, 0, '1239.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1549.900000', 1),
(2718, 840, 232, 'BCPE', 7023856, 1, 0, '1239.920000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1549.900000', 1),
(2719, 840, 232, 'BCHH', 7023801, 1, 0, '1994.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1994.930000', 1),
(2720, 840, 232, 'BCGZ', 7023801, 1, 0, '1319.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1649.900000', 1),
(2721, 840, 232, 'BCHL', 7023822, 1, 0, '1319.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1649.900000', 1),
(2722, 840, 232, 'BCNA', 7023801, 1, 0, '2279.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2849.900000', 1),
(2723, 840, 232, 'BCRH', 7023801, 1, 0, '1479.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1849.900000', 1),
(2724, 840, 232, 'AXXV', 7023801, 1, 0, '1159.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1449.900000', 1),
(2725, 840, 232, 'BBPR', 7023852, 1, 0, '2279.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2849.900000', 1),
(2726, 840, 232, 'BBUS', 7029652, 1, 0, '2399.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2999.900000', 1),
(2727, 840, 232, 'BCDC', 7023852, 1, 0, '1479.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1849.900000', 1),
(2728, 840, 232, 'BCNS', 7023850, 1, 0, '499.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '499.950000', 1),
(2729, 840, 232, 'BBXA', 7023836, 1, 0, '1612.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2149.900000', 1),
(2730, 840, 232, 'BBRU', 7029641, 2, 0, '2474.850000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1649.900000', 1),
(2731, 840, 232, 'BBSB', 7023836, 1, 0, '1162.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1549.900000', 1),
(2732, 840, 232, 'BCJF', 7023836, 1, 0, '1169.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1559.900000', 1),
(2733, 840, 232, 'BABQ', 7023930, 1, 0, '1399.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1399.930000', 1),
(2734, 840, 232, 'BBIO', 7023836, 1, 0, '824.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1099.950000', 1),
(2735, 840, 232, 'BBJE', 7023836, 1, 0, '824.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1099.950000', 1),
(2736, 840, 232, 'APKM', 7023836, 1, 0, '1049.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1399.950000', 1),
(2737, 840, 232, 'APKP', 7023836, 1, 0, '1049.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1399.950000', 1),
(2738, 840, 232, 'AOVA', 7023836, 1, 0, '1162.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2739, 840, 232, 'AOVE', 7023836, 1, 0, '1162.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2740, 840, 232, 'AOUY', 7023836, 1, 0, '1162.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2741, 840, 232, 'APKT', 7023836, 1, 0, '1049.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1399.950000', 1),
(2742, 840, 232, 'APKW', 7023836, 1, 0, '1049.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1399.950000', 1),
(2743, 840, 232, 'AXJD', 7023836, 1, 0, '1162.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2744, 840, 232, 'BCEA', 7023930, 1, 0, '1364.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1364.930000', 1),
(2745, 840, 232, 'BCFU', 7023873, 1, 0, '1074.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1074.950000', 1),
(2746, 840, 232, 'BBXA', 7023873, 1, 0, '1074.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1074.950000', 1),
(2747, 840, 232, 'BCHW', 7029656, 1, 0, '1084.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1084.930000', 1),
(2748, 840, 232, 'BCLX', 7023848, 1, 0, '1912.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2549.900000', 1),
(2749, 840, 232, 'BCNA', 7023848, 1, 0, '1994.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1994.930000', 1),
(2750, 840, 232, 'BCDZ', 7023848, 1, 0, '1462.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2751, 840, 232, 'BCED', 7023848, 1, 0, '1462.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2752, 840, 232, 'BBWL', 7023848, 1, 0, '1462.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2753, 840, 232, 'BBWZ', 7023848, 1, 0, '1612.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2149.900000', 1),
(2754, 840, 232, 'BBTX', 7023848, 1, 0, '1012.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1349.950000', 1),
(2755, 840, 232, 'BBTO', 7023848, 1, 0, '1012.460000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1349.950000', 1),
(2756, 840, 232, 'BBTL', 7023848, 2, 0, '2024.930000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1349.950000', 1),
(2757, 840, 232, 'BBSR', 7023848, 1, 0, '1462.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2758, 840, 232, 'BBSZ', 7023848, 1, 0, '1019.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1359.900000', 1),
(2759, 840, 232, 'BBPT', 7023900, 1, 0, '2137.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2849.900000', 1),
(2760, 840, 232, 'AQQB', 7023848, 2, 0, '3524.850000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2349.900000', 1),
(2761, 840, 232, 'BBOV', 7023848, 1, 0, '1784.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1784.930000', 1),
(2762, 840, 232, 'BBOW', 7023848, 1, 0, '1912.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2549.900000', 1),
(2763, 840, 232, 'AYXC', 7023848, 1, 0, '1462.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2764, 840, 232, 'BATN', 7023810, 1, 0, '1774.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1774.950000', 1),
(2765, 840, 232, 'BBMV', 7023810, 1, 0, '1074.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1074.950000', 1),
(2766, 840, 232, 'BBKX', 7023810, 1, 0, '1499.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1499.950000', 1),
(2767, 840, 232, 'BBNK', 7023810, 1, 0, '974.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '974.950000', 1),
(2768, 840, 232, 'BCNB', 7029658, 1, 0, '1424.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1424.950000', 1),
(2769, 840, 232, 'BCGB', 7023810, 1, 0, '999.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '999.950000', 1),
(2770, 840, 232, 'BBJP', 7029663, 1, 0, '799.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '999.950000', 1),
(2771, 840, 232, 'BBJJ', 7029663, 1, 0, '799.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '999.950000', 1),
(2772, 840, 232, 'AUIM', 7023861, 1, 0, '1559.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2773, 840, 232, 'BCEB', 7023861, 1, 0, '1559.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1949.900000', 1),
(2774, 840, 232, 'BCCN', 7029663, 1, 0, '1719.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2149.900000', 1),
(2775, 840, 232, 'BECW', 7023861, 1, 0, '1599.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1999.900000', 1),
(2776, 840, 232, 'BECQ', 7023803, 1, 0, '1599.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1999.900000', 1),
(2777, 840, 232, 'BBPR', 7023803, 1, 0, '2279.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2849.900000', 1),
(2778, 840, 232, 'BBUS', 7023803, 1, 0, '2399.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '2999.900000', 1),
(2779, 840, 232, 'AWLY', 7023887, 1, 0, '1674.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1674.950000', 1),
(2780, 840, 232, 'AYRN', 7023823, 2, 0, '927.920000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '579.950000', 1),
(2781, 840, 232, 'AYRF', 7023823, 1, 0, '559.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '699.900000', 1),
(2782, 840, 232, 'BARW', 7029659, 1, 0, '1049.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1399.900000', 1),
(2783, 840, 232, 'AZMK', 7023834, 1, 0, '262.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '349.900000', 1),
(2784, 840, 232, 'BABO', 7023834, 1, 0, '1087.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1449.900000', 1),
(2785, 840, 232, 'BBJE', 7023834, 1, 0, '824.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1099.950000', 1),
(2786, 840, 232, 'BBIP', 7023834, 1, 0, '824.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1099.950000', 1),
(2787, 840, 232, 'BBIV', 7023834, 1, 0, '974.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1299.900000', 1),
(2788, 840, 232, 'BBLQ', 7023834, 1, 0, '1087.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1449.900000', 1),
(2789, 840, 232, 'AIYZ', 7023881, 1, 0, '1504.930000', NULL, '2021-03-15 06:22:06', 1, 1, 3, '1504.930000', 1),
(2790, 840, 232, 'BCRV', 7023881, 1, 0, '1049.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1399.900000', 1),
(2791, 840, 232, 'BCMO', 7023834, 1, 0, '262.430000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '349.900000', 1),
(2792, 840, 232, 'BBRT', 7023834, 1, 0, '1499.930000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1999.900000', 1),
(2793, 840, 232, 'BBXE', 7023804, 1, 0, '1074.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1074.950000', 1),
(2794, 840, 232, 'BEDJ', 7023804, 1, 0, '1574.950000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '1574.950000', 1),
(2795, 840, 232, 'BAKV', 7023826, 1, 0, '559.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '699.900000', 1),
(2796, 840, 232, 'BCOI', 7023826, 1, 0, '799.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '999.900000', 1),
(2797, 840, 232, 'AYUX', 7023827, 1, 0, '1079.920000', NULL, '2021-03-15 06:22:06', 1, 1, 1, '1349.900000', 1),
(2798, 276, 232, 'AOUO', 7023830, 1, 0, '1239.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2799, 276, 232, 'AOUR', 7023830, 1, 0, '1239.960000', NULL, '2021-03-15 06:22:06', 1, 1, 2, '1549.950000', 1),
(2800, 276, 232, 'ZCUI', 7023830, 1, 0, '299.900000', NULL, '2021-03-15 06:22:06', 0, 1, 1, '299.900000', 1),
(2801, 276, 232, 'BBYL', 7023843, 1, 0, '2039.920000', NULL, '2021-03-15 06:22:06', 1, 1, 4, '2549.900000', 1),
(2802, 840, 232, 'AOUQ', 7046131, 1, 0, '1359.920000', NULL, '2021-03-15 06:22:58', 1, 1, 1, '1699.900000', 1),
(2803, 840, 232, 'BCEK', 7046175, 1, 0, '1719.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '2149.900000', 1),
(2804, 840, 232, 'BBKS', 7046104, 1, 0, '999.950000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '999.950000', 1),
(2805, 840, 232, 'BBUZ', 7046106, 1, 0, '1719.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '2149.900000', 1),
(2806, 840, 232, 'BBJD', 7046106, 1, 0, '879.960000', NULL, '2021-03-15 06:22:59', 1, 1, 2, '1099.950000', 1),
(2807, 840, 232, 'AYFY', 7046106, 1, 0, '879.960000', NULL, '2021-03-15 06:22:59', 1, 1, 2, '1099.950000', 1),
(2808, 840, 232, 'AOVD', 7046106, 1, 0, '1359.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1699.900000', 1),
(2809, 840, 232, 'BCHC', 7046115, 1, 0, '924.950000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '924.950000', 1),
(2810, 840, 232, 'BBIT', 7046123, 1, 0, '824.960000', NULL, '2021-03-15 06:22:59', 1, 1, 2, '1099.950000', 1),
(2811, 840, 232, 'BBSH', 7046123, 1, 0, '1364.930000', NULL, '2021-03-15 06:22:59', 1, 1, 3, '1364.930000', 1),
(2812, 840, 232, 'BBJC', 7046123, 1, 0, '824.960000', NULL, '2021-03-15 06:22:59', 1, 1, 2, '1099.950000', 1),
(2813, 840, 232, 'AYSN', 7046123, 1, 0, '1087.430000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1449.900000', 1),
(2814, 840, 232, 'BBJN', 7046176, 1, 0, '779.900000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '779.900000', 1),
(2815, 840, 232, 'BCIF', 7046107, 1, 0, '1219.900000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '1219.900000', 1),
(2816, 840, 232, 'BCFX', 7046107, 1, 0, '1279.900000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '1279.900000', 1),
(2817, 840, 232, 'BCLP', 7046107, 1, 0, '1319.900000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '1319.900000', 1),
(2818, 840, 232, 'BCBM', 7046107, 1, 0, '1889.900000', NULL, '2021-03-15 06:22:59', 1, 1, 4, '1889.900000', 1),
(2819, 840, 232, 'BARU', 7046126, 1, 0, '1119.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1399.900000', 1),
(2820, 276, 232, 'BCEE', 7046110, 1, 0, '1504.930000', NULL, '2021-03-15 06:22:59', 1, 1, 3, '1504.930000', 1),
(2821, 276, 232, 'BBJI', 7046110, 1, 0, '919.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1149.900000', 1),
(2822, 276, 232, 'BEJW', 7046110, 1, 0, '1439.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1799.900000', 1),
(2823, 276, 232, 'BEDV', 7046110, 1, 0, '1719.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '2149.900000', 1),
(2824, 276, 232, 'BCJH', 7046110, 1, 0, '1247.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1559.900000', 1),
(2825, 276, 232, 'BBNY', 7046110, 1, 1, '2039.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '2549.900000', 1),
(2826, 276, 232, 'BCKE', 7046110, 1, 0, '1599.920000', NULL, '2021-03-15 06:22:59', 1, 1, 1, '1999.900000', 1);

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
  `precio_recompra` decimal(16,6) UNSIGNED NOT NULL,
  `aumenta_comision` tinyint(1) NOT NULL DEFAULT '0',
  `devuelta` tinyint(1) NOT NULL DEFAULT '0',
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod`),
  KEY `id_cp` (`id_cp`),
  KEY `nro_camp` (`nro_camp`),
  KEY `ejemplar` (`ejemplar`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `recompras`
--

INSERT INTO `recompras` (`cod`, `id_cp`, `nro_camp`, `ejemplar`, `precio_recompra`, `aumenta_comision`, `devuelta`, `fecha_registro`, `active_row`) VALUES
(1, 0, 232, 63, '2100.000000', 0, 0, '2021-03-15 08:12:33', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `saldos`
--

DROP TABLE IF EXISTS `saldos`;
CREATE TABLE IF NOT EXISTS `saldos` (
  `id_cp` int(10) UNSIGNED NOT NULL,
  `nro_camp` int(10) UNSIGNED NOT NULL,
  `balance` decimal(16,6) NOT NULL DEFAULT '0.000000',
  `pedidos` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `catalogos` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `recompras` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `pagos` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `devoluciones` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `comision` decimal(16,6) UNSIGNED NOT NULL DEFAULT '0.000000',
  `active_row` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_cp`,`nro_camp`),
  KEY `nro_camp` (`nro_camp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `saldos`
--

INSERT INTO `saldos` (`id_cp`, `nro_camp`, `balance`, `pedidos`, `catalogos`, `recompras`, `pagos`, `devoluciones`, `comision`, `active_row`) VALUES
(0, 232, '-2100.000000', '0.000000', '0.000000', '2100.000000', '0.000000', '0.000000', '0.000000', 1),
(2, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(12, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(15, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(33, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(57, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(82, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(90, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(113, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(114, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(139, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(166, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(183, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(221, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(240, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(269, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(276, 232, '-153891.891700', '170217.440700', '0.000000', '0.000000', '0.000000', '1835.928000', '14489.621000', 1),
(288, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(354, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(356, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(406, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(422, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(435, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(468, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(476, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(516, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(535, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(549, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(556, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(557, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(558, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(561, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(567, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(580, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(589, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(619, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(639, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(647, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(665, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(686, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(694, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(703, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(704, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(706, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(732, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(745, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(751, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(757, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(771, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(792, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(796, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(803, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(804, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(811, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(815, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(823, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(830, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(832, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(835, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(839, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(840, 232, '-154925.251000', '154925.251000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(844, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(845, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(853, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(859, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(869, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(876, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(878, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(887, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(894, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(898, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(901, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(902, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(904, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(907, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(909, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(914, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(915, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(923, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(925, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(929, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(932, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(933, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(939, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(941, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(943, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(944, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(948, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(949, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(958, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(961, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(963, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(965, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(972, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(974, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(975, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(981, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(984, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(985, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(986, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(987, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(989, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(991, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1005, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1006, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1011, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1013, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1016, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1019, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1021, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1022, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1),
(1023, 232, '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', '0.000000', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos_devueltos`
--
ALTER TABLE `articulos_devueltos`
  ADD CONSTRAINT `articulos_devueltos_ibfk_2` FOREIGN KEY (`cod_pedido`) REFERENCES `pedidos` (`cod`);

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
  ADD CONSTRAINT `devoluciones_ibfk_4` FOREIGN KEY (`ejemplar`) REFERENCES `articulos_devueltos` (`ejemplar`);

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
