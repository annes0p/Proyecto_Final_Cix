-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2026 a las 05:39:50
-- Versión del servidor: 9.3.0
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cixoil`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_use_type`
--

CREATE TABLE `vehicle_use_type` (
  `id_vehicle_use_type` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vehicle_use_type`
--

INSERT INTO `vehicle_use_type` (`id_vehicle_use_type`, `name`, `description`) VALUES
(1, 'Ciudad', 'Uso predominante en zonas urbanas y trayectos cortos.'),
(2, 'Carretera', 'Uso frecuente en viajes largos por carretera.'),
(3, 'Mixto', 'Combinación de conducción urbana y carretera.'),
(4, 'Carga Ligera', 'Transporte regular de carga ligera.'),
(5, 'Carga Pesada', 'Transporte frecuente de carga pesada.'),
(6, 'Transporte de Pasajeros', 'Uso intensivo para transporte de personas.'),
(7, 'Todoterreno', 'Conducción en terrenos difíciles o sin pavimentar.'),
(8, 'Construcción', 'Operación en obras y entornos de construcción.'),
(9, 'Minería', 'Operación en actividades mineras.'),
(10, 'Agrícola', 'Uso en labores agrícolas y ganaderas.'),
(11, 'Alto Kilometraje', 'Uso intensivo con recorridos frecuentes y prolongados.');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `vehicle_use_type`
--
ALTER TABLE `vehicle_use_type`
  ADD PRIMARY KEY (`id_vehicle_use_type`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vehicle_use_type`
--
ALTER TABLE `vehicle_use_type`
  MODIFY `id_vehicle_use_type` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
