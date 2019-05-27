-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2019 a las 01:04:26
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `forex`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `divisa`
--

CREATE TABLE `divisa` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `divisa`
--

INSERT INTO `divisa` (`id`, `name`, `value`) VALUES
(1, 'EUR/USD', 1.0002500000000005),
(2, 'GBP/USD', 1.0003800000000005);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(11) NOT NULL,
  `divisa_id` int(11) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`id`, `divisa_id`, `valor`) VALUES
(1, 1, 1.0002000000000009),
(2, 2, 1.0003300000000008),
(3, 1, 1.0002300000000008),
(4, 2, 1.0003600000000008),
(5, 1, 1.0002200000000008),
(6, 2, 1.0003500000000007),
(7, 1, 1.0002400000000007),
(8, 2, 1.0003700000000006),
(9, 1, 1.0002100000000007),
(10, 2, 1.0003400000000007),
(11, 1, 1.0002200000000008),
(12, 2, 1.0003500000000007),
(13, 1, 1.0002100000000007),
(14, 2, 1.0003400000000007),
(15, 1, 1.0001800000000007),
(16, 2, 1.0003100000000007),
(17, 1, 1.0002100000000007),
(18, 2, 1.0003400000000007),
(19, 1, 1.0002200000000008),
(20, 2, 1.0003500000000007),
(21, 1, 1.0002100000000007),
(22, 2, 1.0003400000000007),
(23, 1, 1.0001900000000008),
(24, 2, 1.0003200000000008),
(25, 1, 1.000170000000001),
(26, 2, 1.0003000000000009),
(27, 1, 1.0001600000000008),
(28, 2, 1.0002900000000008),
(29, 1, 1.0001500000000008),
(30, 2, 1.0002800000000007),
(31, 1, 1.0001400000000007),
(32, 2, 1.0002700000000007),
(33, 1, 1.0001300000000006),
(34, 2, 1.0002600000000006),
(35, 1, 1.0001300000000006),
(36, 2, 1.0002600000000006),
(37, 1, 1.0001200000000006),
(38, 2, 1.0002500000000005),
(39, 1, 1.0001500000000005),
(40, 2, 1.0002800000000005),
(41, 1, 1.0001800000000005),
(42, 2, 1.0003100000000005),
(43, 1, 1.0002000000000004),
(44, 2, 1.0003300000000004),
(45, 1, 1.0002200000000003),
(46, 2, 1.0003500000000003),
(47, 1, 1.0002000000000004),
(48, 2, 1.0003300000000004),
(49, 1, 1.0002100000000005),
(50, 2, 1.0003400000000005),
(51, 1, 1.0002200000000006),
(52, 2, 1.0003500000000005),
(53, 1, 1.0002500000000005),
(54, 2, 1.0003800000000005);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguridad`
--

CREATE TABLE `seguridad` (
  `id` int(11) NOT NULL,
  `token` varchar(400) DEFAULT NULL,
  `user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `seguridad`
--

INSERT INTO `seguridad` (`id`, `token`, `user`) VALUES
(1, 'prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `divisa_id` int(11) NOT NULL,
  `base` double NOT NULL,
  `actual` double NOT NULL,
  `state` tinyint(1) NOT NULL,
  `value_pip` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `transaccion`
--

INSERT INTO `transaccion` (`id`, `user_id`, `divisa_id`, `base`, `actual`, `state`, `value_pip`) VALUES
(8, 1, 1, 1.0002100000000007, 1.0002500000000005, 0, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `name_user` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `outlay` double DEFAULT NULL,
  `earnings` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `name`, `name_user`, `email`, `password`, `outlay`, `earnings`) VALUES
(1, 'cristian', 'cristian', 'gmail.com', '1234', 1000, 0),
(12, 'alejandro', 'alejo', 'alejo@gmail.com', '1234', NULL, NULL),
(77, 'tati', 'tati', 'tati@gmail.com', '1234', NULL, NULL),
(1234, 'cris', 'cris', 'cris@gmail.com', '1234', NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `divisa`
--
ALTER TABLE `divisa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `divisa_id` (`divisa_id`);

--
-- Indices de la tabla `seguridad`
--
ALTER TABLE `seguridad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `divisa_id` (`divisa_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `divisa`
--
ALTER TABLE `divisa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `seguridad`
--
ALTER TABLE `seguridad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `historial_ibfk_1` FOREIGN KEY (`divisa_id`) REFERENCES `divisa` (`id`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `transaccion_ibfk_2` FOREIGN KEY (`divisa_id`) REFERENCES `divisa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
