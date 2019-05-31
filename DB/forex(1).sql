-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2019 a las 02:59:14
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
(1, 'EUR/USD', 1.0003700000000015),
(2, 'GBP/USD', 1.0005200000000014);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(11) NOT NULL,
  `divisa_id` int(11) NOT NULL,
  `valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(14, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjcmlzdGlhbiIsImV4cCI6NjE1MjEzMTA4MDAsImlzcyI6Im5hdEBnbWFpbC5jb20iLCJncm91cHMiOlsidXNlciIsIm5hdCJdfQ.ueu63pge2FjJNoXvDPnWOHjheyc81v4aGi8q-4bHxL7Gzx2dKq5mJ2ADv2wPFXtSdTqtn9mnhbl9oDPJfh_9CA', 1);

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
(20, 1, 1, 1.00038, 1.0003400000000011, 1, 20),
(21, 1, 1, 1.0004400000000002, 1.0003400000000011, 1, 10),
(22, 1, 1, 1.0004099999999998, 1.0003400000000011, 1, 50),
(23, 1, 1, 1.0006400000000004, 1.0003400000000011, 1, 10),
(24, 1, 1, 1.0005200000000007, 1.0003400000000011, 1, 2),
(25, 1, 1, 1.0003100000000011, 1.0003100000000011, 1, 50),
(26, 1, 1, 1.0003100000000011, 1.0002400000000016, 1, 21),
(27, 1, 1, 1.0003200000000012, 1.0002400000000016, 1, 50),
(28, 1, 1, 1.0002800000000014, 1.0002400000000016, 1, 200),
(29, 1, 1, 1.0002200000000017, 1.0003500000000016, 1, 50),
(30, 1, 1, 1.0001700000000016, 1.0003500000000016, 1, 100),
(31, 1, 1, 1.0002600000000015, 1.0003500000000016, 1, 200),
(32, 1, 1, 1.0003700000000015, 1.0003500000000016, 1, 500),
(33, 1, 1, 1.0003600000000017, 1.0003700000000015, 1, 4000);

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
(1, 'cristian', 'cristian', 'cristian@gmail.com', '1234', 8249.999999945356, 0),
(1233, 'tatiana', 'tatiana', 'tati@gmail.com', '1234', 1000, 0);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2319;

--
-- AUTO_INCREMENT de la tabla `seguridad`
--
ALTER TABLE `seguridad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

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
