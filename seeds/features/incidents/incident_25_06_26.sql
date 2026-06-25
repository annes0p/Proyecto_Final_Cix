-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-06-2026 a las 07:08:22
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
-- Estructura de tabla para la tabla `incident`
--

CREATE TABLE `incident` (
  `id_incident` bigint NOT NULL,
  `id_incident_type` bigint DEFAULT NULL,
  `id_incident_category` bigint DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reference` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `full_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `priority` enum('HIGH','LOW','MEDIUM') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reported_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `incident_status` enum('CANCELED','CLOSED','OPEN','RESOLVED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `incident`
--

INSERT INTO `incident` (`id_incident`, `id_incident_type`, `id_incident_category`, `title`, `reference`, `full_title`, `description`, `priority`, `reported_by`, `incident_status`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 'Edited EP', 'VEN-101', '[VEN-101] Edited EP', 'Test', 'HIGH', NULL, 'CANCELED', '2026-06-24 22:31:05.068010', '2026-06-24 22:34:06.174419');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `incident`
--
ALTER TABLE `incident`
  ADD PRIMARY KEY (`id_incident`),
  ADD KEY `FKetcfxacgd1n5yqeb9gq2kfvob` (`id_incident_type`),
  ADD KEY `FK7s88mooitkfyqekyw1yr2fu4d` (`id_incident_category`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `incident`
--
ALTER TABLE `incident`
  MODIFY `id_incident` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `incident`
--
ALTER TABLE `incident`
  ADD CONSTRAINT `FK7s88mooitkfyqekyw1yr2fu4d` FOREIGN KEY (`id_incident_category`) REFERENCES `incident_category` (`id_incident_category`),
  ADD CONSTRAINT `FKetcfxacgd1n5yqeb9gq2kfvob` FOREIGN KEY (`id_incident_type`) REFERENCES `incident_type` (`id_incident_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
