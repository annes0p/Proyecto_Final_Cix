-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-06-2026 a las 05:39:42
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
-- Estructura de tabla para la tabla `vehicle_model`
--

CREATE TABLE `vehicle_model` (
  `id_vehicle_model` bigint NOT NULL,
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `year` int DEFAULT NULL,
  `fuel_type` enum('CNG','DIESEL','ELECTRIC','GASOLINE','HYBRID','HYDROGEN','LPG','PLUG_IN_HYBRID') DEFAULT NULL,
  `horse_power` int DEFAULT NULL,
  `motor_cc` int DEFAULT NULL,
  `transmission_type` enum('AUTOMATIC','CVT','DUAL_CLUTCH','MANUAL','SEMI_AUTOMATIC') DEFAULT NULL,
  `id_vehicle_brand` bigint DEFAULT NULL,
  `id_vehicle_type` bigint DEFAULT NULL,
  `status` int DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `vehicle_model`
--

INSERT INTO `vehicle_model` (`id_vehicle_model`, `model`, `year`, `fuel_type`, `horse_power`, `motor_cc`, `transmission_type`, `id_vehicle_brand`, `id_vehicle_type`, `status`) VALUES
(1, 'Corolla', 2024, 'GASOLINE', 138, 1800, 'CVT', 1, 1, 1),
(2, 'Corolla Cross', 2024, 'HYBRID', 121, 1800, 'CVT', 1, 3, 1),
(3, 'Hilux', 2024, 'DIESEL', 201, 2800, 'MANUAL', 1, 4, 1),
(4, 'Tucson', 2024, 'GASOLINE', 147, 2000, 'AUTOMATIC', 2, 3, 1),
(5, 'Rio', 2023, 'GASOLINE', 113, 1400, 'MANUAL', 3, 2, 1),
(6, 'Dolphin', 2025, 'ELECTRIC', 204, 0, 'AUTOMATIC', 14, 1, 1),
(7, 'CX-5', 2024, 'GASOLINE', 188, 2500, 'AUTOMATIC', 7, 3, 1),
(8, 'Crafter', 2024, 'DIESEL', 140, 2000, 'MANUAL', 8, 8, 1),
(9, 'Mustang', 2024, 'GASOLINE', 315, 2300, 'MANUAL', 9, 5, 1),
(10, 'Civic', 2024, 'GASOLINE', 180, 1500, 'CVT', 10, 1, 1),
(11, 'Prueba endpoint edit', 3001, 'DIESEL', 9999, 1, 'MANUAL', 7, 9, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  ADD PRIMARY KEY (`id_vehicle_model`),
  ADD KEY `FKin7ily1ads8u6ru46w9dvn07f` (`id_vehicle_brand`),
  ADD KEY `FK7t3kc8g8s8whberdxrc8vb0cy` (`id_vehicle_type`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  MODIFY `id_vehicle_model` bigint NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  ADD CONSTRAINT `FK7t3kc8g8s8whberdxrc8vb0cy` FOREIGN KEY (`id_vehicle_type`) REFERENCES `vehicle_type` (`id_vehicle_type`),
  ADD CONSTRAINT `FKin7ily1ads8u6ru46w9dvn07f` FOREIGN KEY (`id_vehicle_brand`) REFERENCES `vehicle_brand` (`id_vehicle_brand`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
