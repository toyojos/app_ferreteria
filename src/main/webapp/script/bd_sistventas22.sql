-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-06-2022 a las 08:24:59
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_sistventas22`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `ID_CATEGORIA` int(11) UNSIGNED NOT NULL,
  `NOMBRE` varchar(255) NOT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`ID_CATEGORIA`, `NOMBRE`, `DESCRIPCION`) VALUES
(1, 'Construcción', 'Herramienta de Contrucción'),
(2, 'Pintura', 'Pintura en General');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `ID_CLIENTE` int(11) UNSIGNED NOT NULL,
  `CI` varchar(10) DEFAULT NULL,
  `NOMBRES` varchar(255) DEFAULT NULL,
  `APELLIDOS` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `CELULAR` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`ID_CLIENTE`, `CI`, `NOMBRES`, `APELLIDOS`, `DIRECCION`, `EMAIL`, `CELULAR`) VALUES
(1, '123', 'Jhosel', 'Estrada Cruz', 'Alto Lima', 'jhosel@gmail.com', '77566248');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `ID_DETALLE_VENTAS` int(11) UNSIGNED NOT NULL,
  `ID_VENTAS` int(11) UNSIGNED NOT NULL,
  `ID_PRODUCTO` int(11) UNSIGNED NOT NULL,
  `CANTIDAD` int(11) UNSIGNED DEFAULT NULL,
  `PRECIO_VENTA` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`ID_DETALLE_VENTAS`, `ID_VENTAS`, `ID_PRODUCTO`, `CANTIDAD`, `PRECIO_VENTA`) VALUES
(11, 9, 2, 2, 85),
(12, 9, 1, 1, 333),
(13, 10, 2, 4, 85),
(14, 10, 1, 3, 500),
(15, 12, 2, 2, 85),
(16, 12, 1, 2, 25.5),
(17, 13, 2, 12, 1000),
(18, 14, 1, 1, 25.5),
(19, 15, 1, 3, 25.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `ID_PRODUCTO` int(11) UNSIGNED NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `MARCA` varchar(255) DEFAULT NULL,
  `STOCK` int(11) UNSIGNED DEFAULT NULL,
  `PRECIO` double(10,2) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  `ID_PROVEEDOR` int(11) UNSIGNED NOT NULL,
  `ID_CATEGORIA` int(11) UNSIGNED NOT NULL,
  `IMAGEN` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`ID_PRODUCTO`, `NOMBRE`, `MARCA`, `STOCK`, `PRECIO`, `ESTADO`, `ID_PROVEEDOR`, `ID_CATEGORIA`, `IMAGEN`) VALUES
(1, 'Plancha Metálica', 'Asatex', 100, 25.50, '1', 1, 1, ''),
(2, 'Latex', 'Monopol', 50, 85.00, '1', 1, 2, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `ID_PROVEEDOR` int(11) UNSIGNED NOT NULL,
  `CI` varchar(10) DEFAULT NULL,
  `NOMBRES` varchar(255) DEFAULT NULL,
  `APELLIDOS` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `CELULAR` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`ID_PROVEEDOR`, `CI`, `NOMBRES`, `APELLIDOS`, `DIRECCION`, `EMAIL`, `CELULAR`) VALUES
(1, '9932471', 'Juan', 'Mamani', 'Garita de Lima', 'juan@gmail.com', '77453216');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) UNSIGNED NOT NULL,
  `CI` varchar(10) NOT NULL,
  `NOMBRES` varchar(255) DEFAULT NULL,
  `APELLIDOS` varchar(255) DEFAULT NULL,
  `DIRECCION` varchar(255) DEFAULT NULL,
  `CELULAR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `CONTRASENA` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID_USUARIO`, `CI`, `NOMBRES`, `APELLIDOS`, `DIRECCION`, `CELULAR`, `EMAIL`, `CONTRASENA`) VALUES
(1, '9324756', 'Janeth', 'Choque Gomez', 'Sopocahi', '77776523', 'admin@mail.com', 'admin'),
(3, '9965428', 'Choque Choque cccc', 'Carlos', 'Alto Lima', '76588494', 'carlos@mail.com', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `ID_VENTAS` int(11) UNSIGNED NOT NULL,
  `ID_CLIENTE` int(11) UNSIGNED NOT NULL,
  `ID_USUARIO` int(11) UNSIGNED NOT NULL,
  `NUMERO_VENTAS` varchar(20) DEFAULT NULL,
  `FECHA_VENTAS` date DEFAULT NULL,
  `PRECIO_TOTAL` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`ID_VENTAS`, `ID_CLIENTE`, `ID_USUARIO`, `NUMERO_VENTAS`, `FECHA_VENTAS`, `PRECIO_TOTAL`) VALUES
(9, 1, 0, '0000000001', '2022-06-26', 503),
(10, 1, 0, '0000000002', '2022-06-26', 1840),
(12, 1, 0, '0000000003', '2022-06-26', 221),
(13, 1, 0, '0000000004', '2022-06-26', 12000),
(14, 1, 0, '0000000005', '2022-06-26', 25.5),
(15, 1, 0, '0000000006', '2022-06-26', 76.5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`ID_CATEGORIA`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID_CLIENTE`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`ID_DETALLE_VENTAS`),
  ADD KEY `ID_VENTAS` (`ID_VENTAS`),
  ADD KEY `ID_PRODUCTO` (`ID_PRODUCTO`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`ID_PRODUCTO`),
  ADD KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`),
  ADD KEY `ID_CATEGORIA` (`ID_CATEGORIA`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`ID_PROVEEDOR`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_USUARIO`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`ID_VENTAS`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `ID_CLIENTE` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `ID_DETALLE_VENTAS` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `ID_PRODUCTO` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `ID_VENTAS` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
