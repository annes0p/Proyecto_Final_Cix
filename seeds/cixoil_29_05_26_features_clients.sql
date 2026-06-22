-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-05-2026 a las 21:08:59
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
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id_category` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id_category`, `description`, `name`, `status`) VALUES
(1, 'Lubricantes principales para el funcionamiento del motor. Reducen fricción, desgaste y ayudan a mantener la temperatura adecuada.', 'Aceites de motor', 1),
(2, 'Fluidos diseñados para cajas manuales, automáticas y diferenciales. Permiten cambios suaves y protegen engranajes bajo alta presión.', 'Aceites de transmisión', 1),
(3, 'Lubricantes semisólidos usados en rodamientos, chasis y piezas móviles expuestas a carga pesada o poca frecuencia de mantenimiento.', 'Grasas lubricantes', 1),
(4, 'Fluidos que regulan la temperatura del motor, evitando sobrecalentamiento y congelamiento, además de proteger el sistema de corrosión.', 'Refrigerantes y anticongelantes', 1),
(5, 'Fluidos hidráulicos para sistemas de frenado', 'Líquidos de freno', 1),
(6, 'Mejoradores de rendimiento del motor y combustible', 'Aditivos automotrices', 1),
(7, 'Productos de limpieza de motor y piezas', 'Limpieza y mantenimiento', 1),
(8, 'Sprays multiuso y penetrantes', 'Lubricantes en aerosol', 1),
(9, 'Aceites hidráulicos para maquinaria', 'Fluidos hidráulicos', 1),
(10, 'Category created and edited from backend enpoint', 'Category endpoint edited', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id_client` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `doc_number` varchar(255) DEFAULT NULL,
  `document_type` enum('DNI','RUC') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `f_last_name` varchar(255) DEFAULT NULL,
  `m_last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_location` bigint DEFAULT NULL,
  `trusted` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id_client`, `address`, `created_at`, `doc_number`, `document_type`, `email`, `f_last_name`, `m_last_name`, `name`, `phone_number`, `status`, `updated_at`, `id_location`, `trusted`) VALUES
(1, 'Calle falsa 123', '2026-05-29 13:45:55.000000', '11223344', 'DNI', 'clientbd@cliente.com', 'creado', 'database', 'cliente falso', '999888777', 1, '2026-05-29 13:45:55.000000', NULL, b'0'),
(2, 'Calle irreal 321', '2026-05-29 14:01:00.795388', '20112233445', 'RUC', 'cliente2@endpoint.com', 'Edited', 'Enpoint', 'Client', '111222333', 1, '2026-05-29 14:02:29.302774', 1, b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `document_series`
--

CREATE TABLE `document_series` (
  `id_document_series` bigint NOT NULL,
  `current_number` bigint DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `document_series`
--

INSERT INTO `document_series` (`id_document_series`, `current_number`, `series`, `status`) VALUES
(1, 0, 'B001', 1),
(2, 0, 'F001', 1),
(3, 0, 'N001', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventory`
--

CREATE TABLE `inventory` (
  `id_inventory` bigint NOT NULL,
  `min_stock` bigint DEFAULT NULL,
  `stock` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `location`
--

CREATE TABLE `location` (
  `id_location` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `location`
--

INSERT INTO `location` (`id_location`, `name`) VALUES
(1, 'JLO'),
(2, 'Chepén'),
(3, 'Ferreñafe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `module`
--

CREATE TABLE `module` (
  `id_module` bigint NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `module`
--

INSERT INTO `module` (`id_module`, `code`, `description`, `name`, `route`) VALUES
(1, NULL, 'Datos clave del negocio', 'Dashboard', '/admin/dashboard'),
(2, NULL, 'Gestión de usuarios y roles asociados', 'Usuarios', '/admin/usuarios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notification`
--

CREATE TABLE `notification` (
  `id_notification` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `notification_status` enum('READ','UNREAD') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `notification`
--

INSERT INTO `notification` (`id_notification`, `created_at`, `message`, `notification_status`, `title`, `id_user`, `updated_at`) VALUES
(1, '2026-05-24 23:05:50.000000', 'Este es un mensaje de prueba', 'UNREAD', 'Prueba 1', 1, '2026-05-24 23:05:50.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id_product` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `viscosity` varchar(255) DEFAULT NULL,
  `id_product_brand` bigint DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id_product`, `created_at`, `description`, `name`, `price`, `status`, `viscosity`, `id_product_brand`, `id_category`, `updated_at`) VALUES
(1, '2026-05-25 00:17:42.000000', 'Aceite semisintético para motores gasolina y diésel, alta protección bajo presión', 'EDGE 10W-40', 140.00, 1, '10W-40', 2, 1, '2026-05-25 00:17:42.000000'),
(34, '2026-05-25 00:17:42.000000', 'Fluido para transmisión automática de alto kilometraje que mejora suavidad de cambios', 'MaxLife ATF', 120.00, 1, 'ATF', 4, 2, '2026-05-25 00:17:42.000000'),
(35, '2026-05-25 00:17:42.000000', 'Fluido premium para transmisiones automáticas modernas y CVT', 'Top Tec ATF 1800', 155.00, 1, 'ATF', 6, 2, '2026-05-25 00:17:42.000000'),
(36, '2026-05-25 00:17:42.000000', 'Grasa de litio de alta resistencia para rodamientos y cargas pesadas', 'XHP 222', 45.00, 1, 'NLGI 2', 11, 3, '2026-05-25 00:17:42.000000'),
(37, '2026-05-25 00:17:42.000000', 'Grasa multipropósito para aplicaciones automotrices e industriales', 'Gadus S2 V220', 40.00, 1, 'NLGI 2', 12, 3, '2026-05-25 00:17:42.000000'),
(38, '2026-05-25 00:17:42.000000', 'Refrigerante de larga duración anti corrosión y sobrecalentamiento', 'Extended Life Coolant', 35.00, 1, NULL, 15, 4, '2026-05-25 00:17:42.000000'),
(39, '2026-05-25 00:17:42.000000', 'Anticongelante listo para uso en motores livianos y pesados', 'Long Life Antifreeze', 32.00, 1, NULL, 16, 4, '2026-05-25 00:17:42.000000'),
(40, '2026-05-25 00:17:42.000000', 'Fluido de freno de alto punto de ebullición para sistemas exigentes', 'DOT 4 Brake Fluid', 28.00, 1, 'DOT 4', 18, 5, '2026-05-25 00:17:42.000000'),
(41, '2026-05-25 00:17:42.000000', 'Líquido de freno estándar para vehículos livianos', 'DOT 3 Brake Fluid', 22.00, 1, 'DOT 3', 19, 5, '2026-05-25 00:17:42.000000'),
(42, '2026-05-25 00:17:42.000000', 'Aditivo antifricción que reduce desgaste del motor', 'Cera Tec', 85.00, 1, NULL, 6, 6, '2026-05-25 00:17:42.000000'),
(43, '2026-05-25 00:17:42.000000', 'Limpiador de inyectores que mejora rendimiento del combustible', 'Fuel Injector Cleaner', 35.00, 1, NULL, 22, 6, '2026-05-25 00:17:42.000000'),
(44, '2026-05-25 00:17:42.000000', 'Limpieza interna del motor antes del cambio de aceite', 'Engine Flush', 45.00, 1, NULL, 6, 7, '2026-05-25 00:17:42.000000'),
(45, '2026-05-25 00:17:42.000000', 'Desengrasante para limpieza de motores y piezas metálicas', 'Engine Degreaser', 38.00, 1, NULL, 25, 7, '2026-05-25 00:17:42.000000'),
(46, '2026-05-25 00:17:42.000000', 'Lubricante multiuso en spray contra humedad y corrosión', 'Multi-Use Spray', 25.00, 1, 'Spray', 14, 8, '2026-05-25 00:17:42.000000'),
(47, '2026-05-25 00:17:42.000000', 'Aceite penetrante para aflojar piezas oxidadas', 'Penetrating Oil', 30.00, 1, 'Spray', 25, 8, '2026-05-25 00:17:42.000000'),
(48, '2026-05-25 00:17:42.000000', 'Aceite hidráulico para sistemas de alta presión industrial', 'Tellus S2 M 46', 180.00, 1, 'ISO VG 46', 3, 9, '2026-05-25 00:17:42.000000'),
(49, '2026-05-25 00:17:42.000000', 'Fluido hidráulico industrial de alta estabilidad térmica', 'DTE 25 Hydraulic Oil', 175.00, 1, 'ISO VG 46', 1, 9, '2026-05-25 00:17:42.000000'),
(50, '2026-05-25 04:30:23.753435', 'Product created from endpint', 'Product endpoint edited', 333.33, 2, 'Random viscosity', 1, 1, '2026-05-25 04:32:00.490657');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_brand`
--

CREATE TABLE `product_brand` (
  `id_product_brand` bigint NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `product_brand`
--

INSERT INTO `product_brand` (`id_product_brand`, `name`) VALUES
(1, 'Mobil 1'),
(2, 'Castrol'),
(3, 'Shell Helix'),
(4, 'Valvoline'),
(5, 'TotalEnergies'),
(6, 'Liqui Moly'),
(7, 'Castrol Transmax'),
(8, 'Mobil ATF'),
(9, 'Valvoline MaxLife'),
(10, 'Liqui Moly Top Tec'),
(11, 'Mobilgrease'),
(12, 'Shell Gadus'),
(13, 'SKF'),
(14, 'Castrol Spheerol'),
(15, 'Prestone'),
(16, 'PEAK'),
(17, 'TotalEnergies Glacelf'),
(18, 'ATE'),
(19, 'Bosch'),
(20, 'TRW'),
(21, 'Castrol React SRF'),
(22, 'STP'),
(23, 'Wynn\'s'),
(24, 'Chevron Techron'),
(25, 'CRC'),
(26, '3M'),
(27, 'WD-40'),
(28, '3-IN-ONE'),
(29, 'Shell Tellus'),
(30, 'Mobil DTE'),
(31, 'Chevron Rando'),
(32, 'TotalEnergies Azolla'),
(33, 'Product brand endpoint edited');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_recommendation`
--

CREATE TABLE `product_recommendation` (
  `id_product_recommendation` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `priority` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_vehicle_model` bigint DEFAULT NULL,
  `id_vehicle_type` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promotion`
--

CREATE TABLE `promotion` (
  `id_promotion` bigint NOT NULL,
  `auto_activate` bit(1) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_day` int DEFAULT NULL,
  `end_month` int DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `start_day` int DEFAULT NULL,
  `start_month` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `trigger_quantity` bigint DEFAULT NULL,
  `id_bonus_product` bigint DEFAULT NULL,
  `id_promotion_type` bigint DEFAULT NULL,
  `id_trigger_product` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promotion_type`
--

CREATE TABLE `promotion_type` (
  `id_promotion_type` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `purchase` (
  `id_purchase` bigint NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `estimated_date` date DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `reception_status` enum('PARTIALLY_RECIEVED','PENDING','RECIEVED') DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `id_supplier` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase_detail`
--

CREATE TABLE `purchase_detail` (
  `id_purchase_detail` bigint NOT NULL,
  `line_total` decimal(38,2) DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_purchase` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id_role` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id_role`, `description`, `name`, `status`) VALUES
(1, 'Acceso completo a todos los módulos del sistema', 'Administrador', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role_module`
--

CREATE TABLE `role_module` (
  `id_role` bigint NOT NULL,
  `id_module` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `role_module`
--

INSERT INTO `role_module` (`id_role`, `id_module`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route`
--

CREATE TABLE `route` (
  `id_route` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `progress_status` enum('CANCELED','COMPLETED','IN_PROGRESS','PENDING') DEFAULT NULL,
  `route_date` date DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `route`
--

INSERT INTO `route` (`id_route`, `created_at`, `progress_status`, `route_date`, `updated_at`, `id_user`, `status`) VALUES
(1, '2026-05-27 19:46:45.000000', 'PENDING', '2026-05-28', '2026-05-27 19:46:45.000000', 1, 1),
(2, '2026-05-27 19:49:42.000000', 'PENDING', '2026-05-28', '2026-05-27 19:49:42.000000', 2, 1),
(3, '2026-05-28 12:04:16.113339', 'IN_PROGRESS', '2026-05-30', '2026-05-28 13:27:54.147574', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale`
--

CREATE TABLE `sale` (
  `id_sale` bigint NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `payment_method` enum('CARD','CASH','TRANSFER','YAPE') DEFAULT NULL,
  `sale_date` datetime(6) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `tax_amount` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `transaction_status` enum('CANCELED','COMPLETED','PENDING') DEFAULT NULL,
  `voucher_type` enum('INVOICE','RECEIPT','SALE_NOTE') DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale_detail`
--

CREATE TABLE `sale_detail` (
  `id_sale_detail` bigint NOT NULL,
  `quantity` bigint DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `tax_amount` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_sale` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_loan`
--

CREATE TABLE `stock_loan` (
  `id_stock_loan` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `loan_status` enum('PARTIALLY_RETURNED','PENDING','RETURNED') DEFAULT NULL,
  `quantity_loaned` bigint DEFAULT NULL,
  `quantity_remaining` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_movement`
--

CREATE TABLE `stock_movement` (
  `id_stock_movement` bigint NOT NULL,
  `final_stock` bigint DEFAULT NULL,
  `initial_stock` bigint DEFAULT NULL,
  `movement_date` datetime(6) DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `movement_type` enum('ADJUSTMENT','IN','OUT','RETURN') DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_prediction`
--

CREATE TABLE `stock_prediction` (
  `id_stock_prediction` bigint NOT NULL,
  `current_stock` bigint DEFAULT NULL,
  `daily_sales_avg` decimal(38,2) DEFAULT NULL,
  `days_period` int DEFAULT NULL,
  `prediction_date` datetime(6) DEFAULT NULL,
  `stock_out_date` datetime(6) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `doc_number` varchar(255) DEFAULT NULL,
  `document_type` enum('DNI','RUC') DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supplier_product`
--

CREATE TABLE `supplier_product` (
  `id_supplier_product` bigint NOT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_supplier` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trip`
--

CREATE TABLE `trip` (
  `id_trip` bigint NOT NULL,
  `end_time` time DEFAULT NULL,
  `progress_status` enum('CANCELED','COMPLETED','IN_PROGRESS','PENDING') DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `id_destination_location` bigint DEFAULT NULL,
  `id_origin_location` bigint DEFAULT NULL,
  `id_route` bigint DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `trip`
--

INSERT INTO `trip` (`id_trip`, `end_time`, `progress_status`, `start_time`, `id_destination_location`, `id_origin_location`, `id_route`, `status`) VALUES
(1, '17:47:21', 'PENDING', '23:47:21', 2, 3, 1, 1),
(2, NULL, 'IN_PROGRESS', '21:13:13', 3, 1, 1, 0),
(3, '20:49:47', 'COMPLETED', '20:49:14', 2, 1, 2, 1),
(4, '01:00:00', 'PENDING', '00:00:00', 3, 1, 1, 1),
(5, NULL, 'IN_PROGRESS', '13:27:54', 2, 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id_user` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_role` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_user`, `created_at`, `email`, `password`, `status`, `username`, `id_role`, `updated_at`) VALUES
(1, '2026-05-25 09:43:34.000000', 'admin@admin.com', '$2a$10$Mt2ltioT746kMKL0v0bprOAKGV2TdoDKSN79uMv/789.Ph0M/HyDO', 1, 'admin', 1, NULL),
(2, '2026-05-25 03:00:09.528799', 'test@point.com', '$2a$10$Pq.bIABonWTA4ftQbGy9WuSp1oKscMzaku82UF0WHhUVlBAyxobi2', 2, 'testpointactualizado', 1, '2026-05-25 03:02:57.356745');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_brand`
--

CREATE TABLE `vehicle_brand` (
  `id_vehicle_brand` bigint NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_model`
--

CREATE TABLE `vehicle_model` (
  `id_vehicle_model` bigint NOT NULL,
  `fuel_type` tinyint DEFAULT NULL,
  `horse_power` int DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `motor_cc` int DEFAULT NULL,
  `transmission_type` tinyint DEFAULT NULL,
  `year` int DEFAULT NULL,
  `id_vehicle_brand` bigint DEFAULT NULL,
  `id_vehicle_type` bigint DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_type`
--

CREATE TABLE `vehicle_type` (
  `id_vehicle_type` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_unit`
--

CREATE TABLE `vehicle_unit` (
  `id_vehicle_unit` bigint NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `plate` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_vehicle_model` bigint DEFAULT NULL,
  `id_vehicle_use_type` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_use_type`
--

CREATE TABLE `vehicle_use_type` (
  `id_vehicle_use_type` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`);

--
-- Indices de la tabla `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD KEY `FKgm99hbnlsngtjm2a2yd2pm6in` (`id_location`);

--
-- Indices de la tabla `document_series`
--
ALTER TABLE `document_series`
  ADD PRIMARY KEY (`id_document_series`);

--
-- Indices de la tabla `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id_inventory`),
  ADD KEY `FK2e5rg5cpoe7hy9fng6xk3a391` (`id_product`);

--
-- Indices de la tabla `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id_location`);

--
-- Indices de la tabla `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id_module`);

--
-- Indices de la tabla `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id_notification`),
  ADD KEY `FKjsqpq32j3cp7sbi81on7xo3jg` (`id_user`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `FKo5di8uw96cm2eiwbs21ug1u81` (`id_product_brand`),
  ADD KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`);

--
-- Indices de la tabla `product_brand`
--
ALTER TABLE `product_brand`
  ADD PRIMARY KEY (`id_product_brand`);

--
-- Indices de la tabla `product_recommendation`
--
ALTER TABLE `product_recommendation`
  ADD PRIMARY KEY (`id_product_recommendation`),
  ADD KEY `FK3frxb1admws2320lken96ubce` (`id_product`),
  ADD KEY `FKlqi7becvagcvflv8dbxoabrop` (`id_vehicle_model`),
  ADD KEY `FKf3nye0s6efh8t0siu4jyrfirp` (`id_vehicle_type`);

--
-- Indices de la tabla `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id_promotion`),
  ADD KEY `FK34juso0qoyywt96qfx1gkju98` (`id_bonus_product`),
  ADD KEY `FKarxqye4ia1v5uypipr0m54h83` (`id_promotion_type`),
  ADD KEY `FKjfsi6ydypxgmndx33vilj0c4s` (`id_trigger_product`);

--
-- Indices de la tabla `promotion_type`
--
ALTER TABLE `promotion_type`
  ADD PRIMARY KEY (`id_promotion_type`);

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id_purchase`),
  ADD KEY `FK1v1n2p3gww5umecnk1ikpejym` (`id_supplier`);

--
-- Indices de la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  ADD PRIMARY KEY (`id_purchase_detail`),
  ADD KEY `FKemu4vahnyggne2m2h2fvmq2f2` (`id_product`),
  ADD KEY `FKmg4ojcnpkt9u6m4oeomytadkk` (`id_purchase`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indices de la tabla `role_module`
--
ALTER TABLE `role_module`
  ADD KEY `FKhrkiisyx5gvfkm2t5hlw3q127` (`id_module`),
  ADD KEY `FKmlswe1pd1ikw1icjmuib1pt56` (`id_role`);

--
-- Indices de la tabla `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id_route`),
  ADD KEY `FKe681bhvusnepqjmyf1t6v2ejr` (`id_user`);

--
-- Indices de la tabla `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id_sale`),
  ADD KEY `FKa3snnn1kxdye45qhqb6pfv0jg` (`id_client`),
  ADD KEY `FKoummd5xb2xy9eoyvumt1nmwr9` (`id_user`);

--
-- Indices de la tabla `sale_detail`
--
ALTER TABLE `sale_detail`
  ADD PRIMARY KEY (`id_sale_detail`),
  ADD KEY `FKablh9gpkyh2ux3qvos3c8lxs4` (`id_product`),
  ADD KEY `FKdyagbsvpg2pdeb66xa8gvbg2p` (`id_sale`);

--
-- Indices de la tabla `stock_loan`
--
ALTER TABLE `stock_loan`
  ADD PRIMARY KEY (`id_stock_loan`),
  ADD KEY `FKpabw4rvi1h1y71j77lj3we2xc` (`id_client`),
  ADD KEY `FKkmf672qd467hkxs96xtm11dl8` (`id_product`);

--
-- Indices de la tabla `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD PRIMARY KEY (`id_stock_movement`),
  ADD KEY `FKm0v6q3atu4w8lawj90n8njtgf` (`id_product`);

--
-- Indices de la tabla `stock_prediction`
--
ALTER TABLE `stock_prediction`
  ADD PRIMARY KEY (`id_stock_prediction`),
  ADD KEY `FK9t764ir0eo8n7cmgoucv212q7` (`id_product`);

--
-- Indices de la tabla `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indices de la tabla `supplier_product`
--
ALTER TABLE `supplier_product`
  ADD PRIMARY KEY (`id_supplier_product`),
  ADD KEY `FK2tmbsv240p1yje9uihpp77yxk` (`id_product`),
  ADD KEY `FK938141p6oo3qowevev139j006` (`id_supplier`);

--
-- Indices de la tabla `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`id_trip`),
  ADD KEY `FKk5avrl2tkndt0r5tw75e4mjul` (`id_destination_location`),
  ADD KEY `FKsu9l0ql6iqmp7txhwapmi7256` (`id_origin_location`),
  ADD KEY `FKlbjpo227xfvvyg5umkr740csv` (`id_route`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FK6njoh3pti5jnlkowken3r8ttn` (`id_role`);

--
-- Indices de la tabla `vehicle_brand`
--
ALTER TABLE `vehicle_brand`
  ADD PRIMARY KEY (`id_vehicle_brand`);

--
-- Indices de la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  ADD PRIMARY KEY (`id_vehicle_model`),
  ADD KEY `FKin7ily1ads8u6ru46w9dvn07f` (`id_vehicle_brand`),
  ADD KEY `FK7t3kc8g8s8whberdxrc8vb0cy` (`id_vehicle_type`);

--
-- Indices de la tabla `vehicle_type`
--
ALTER TABLE `vehicle_type`
  ADD PRIMARY KEY (`id_vehicle_type`);

--
-- Indices de la tabla `vehicle_unit`
--
ALTER TABLE `vehicle_unit`
  ADD PRIMARY KEY (`id_vehicle_unit`),
  ADD KEY `FKlnbrcaavhct6nu8hvciaj9gv7` (`id_client`),
  ADD KEY `FK76saw0qq8knx7gqliqo2qlypi` (`id_vehicle_model`),
  ADD KEY `FKposaui017935q8e1mly0fjv3o` (`id_vehicle_use_type`);

--
-- Indices de la tabla `vehicle_use_type`
--
ALTER TABLE `vehicle_use_type`
  ADD PRIMARY KEY (`id_vehicle_use_type`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id_category` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `client`
--
ALTER TABLE `client`
  MODIFY `id_client` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `document_series`
--
ALTER TABLE `document_series`
  MODIFY `id_document_series` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id_inventory` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `location`
--
ALTER TABLE `location`
  MODIFY `id_location` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `module`
--
ALTER TABLE `module`
  MODIFY `id_module` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `notification`
--
ALTER TABLE `notification`
  MODIFY `id_notification` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `id_product` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `product_brand`
--
ALTER TABLE `product_brand`
  MODIFY `id_product_brand` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `product_recommendation`
--
ALTER TABLE `product_recommendation`
  MODIFY `id_product_recommendation` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id_promotion` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `promotion_type`
--
ALTER TABLE `promotion_type`
  MODIFY `id_promotion_type` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id_purchase` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  MODIFY `id_purchase_detail` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id_role` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `id_route` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sale`
--
ALTER TABLE `sale`
  MODIFY `id_sale` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sale_detail`
--
ALTER TABLE `sale_detail`
  MODIFY `id_sale_detail` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stock_loan`
--
ALTER TABLE `stock_loan`
  MODIFY `id_stock_loan` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stock_movement`
--
ALTER TABLE `stock_movement`
  MODIFY `id_stock_movement` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stock_prediction`
--
ALTER TABLE `stock_prediction`
  MODIFY `id_stock_prediction` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `supplier_product`
--
ALTER TABLE `supplier_product`
  MODIFY `id_supplier_product` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `trip`
--
ALTER TABLE `trip`
  MODIFY `id_trip` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id_user` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `vehicle_brand`
--
ALTER TABLE `vehicle_brand`
  MODIFY `id_vehicle_brand` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  MODIFY `id_vehicle_model` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehicle_type`
--
ALTER TABLE `vehicle_type`
  MODIFY `id_vehicle_type` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehicle_unit`
--
ALTER TABLE `vehicle_unit`
  MODIFY `id_vehicle_unit` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehicle_use_type`
--
ALTER TABLE `vehicle_use_type`
  MODIFY `id_vehicle_use_type` bigint NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKgm99hbnlsngtjm2a2yd2pm6in` FOREIGN KEY (`id_location`) REFERENCES `location` (`id_location`);

--
-- Filtros para la tabla `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `FK2e5rg5cpoe7hy9fng6xk3a391` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Filtros para la tabla `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FKjsqpq32j3cp7sbi81on7xo3jg` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK5cxv31vuhc7v32omftlxa8k3c` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  ADD CONSTRAINT `FKo5di8uw96cm2eiwbs21ug1u81` FOREIGN KEY (`id_product_brand`) REFERENCES `product_brand` (`id_product_brand`);

--
-- Filtros para la tabla `product_recommendation`
--
ALTER TABLE `product_recommendation`
  ADD CONSTRAINT `FK3frxb1admws2320lken96ubce` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKf3nye0s6efh8t0siu4jyrfirp` FOREIGN KEY (`id_vehicle_type`) REFERENCES `vehicle_use_type` (`id_vehicle_use_type`),
  ADD CONSTRAINT `FKlqi7becvagcvflv8dbxoabrop` FOREIGN KEY (`id_vehicle_model`) REFERENCES `vehicle_model` (`id_vehicle_model`);

--
-- Filtros para la tabla `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `FK34juso0qoyywt96qfx1gkju98` FOREIGN KEY (`id_bonus_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKarxqye4ia1v5uypipr0m54h83` FOREIGN KEY (`id_promotion_type`) REFERENCES `promotion_type` (`id_promotion_type`),
  ADD CONSTRAINT `FKjfsi6ydypxgmndx33vilj0c4s` FOREIGN KEY (`id_trigger_product`) REFERENCES `product` (`id_product`);

--
-- Filtros para la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `FK1v1n2p3gww5umecnk1ikpejym` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Filtros para la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  ADD CONSTRAINT `FKemu4vahnyggne2m2h2fvmq2f2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKmg4ojcnpkt9u6m4oeomytadkk` FOREIGN KEY (`id_purchase`) REFERENCES `purchase` (`id_purchase`);

--
-- Filtros para la tabla `role_module`
--
ALTER TABLE `role_module`
  ADD CONSTRAINT `FKhrkiisyx5gvfkm2t5hlw3q127` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`),
  ADD CONSTRAINT `FKmlswe1pd1ikw1icjmuib1pt56` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Filtros para la tabla `route`
--
ALTER TABLE `route`
  ADD CONSTRAINT `FKe681bhvusnepqjmyf1t6v2ejr` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Filtros para la tabla `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `FKa3snnn1kxdye45qhqb6pfv0jg` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `FKoummd5xb2xy9eoyvumt1nmwr9` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Filtros para la tabla `sale_detail`
--
ALTER TABLE `sale_detail`
  ADD CONSTRAINT `FKablh9gpkyh2ux3qvos3c8lxs4` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKdyagbsvpg2pdeb66xa8gvbg2p` FOREIGN KEY (`id_sale`) REFERENCES `sale` (`id_sale`);

--
-- Filtros para la tabla `stock_loan`
--
ALTER TABLE `stock_loan`
  ADD CONSTRAINT `FKkmf672qd467hkxs96xtm11dl8` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FKpabw4rvi1h1y71j77lj3we2xc` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Filtros para la tabla `stock_movement`
--
ALTER TABLE `stock_movement`
  ADD CONSTRAINT `FKm0v6q3atu4w8lawj90n8njtgf` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Filtros para la tabla `stock_prediction`
--
ALTER TABLE `stock_prediction`
  ADD CONSTRAINT `FK9t764ir0eo8n7cmgoucv212q7` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Filtros para la tabla `supplier_product`
--
ALTER TABLE `supplier_product`
  ADD CONSTRAINT `FK2tmbsv240p1yje9uihpp77yxk` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `FK938141p6oo3qowevev139j006` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Filtros para la tabla `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FKk5avrl2tkndt0r5tw75e4mjul` FOREIGN KEY (`id_destination_location`) REFERENCES `location` (`id_location`),
  ADD CONSTRAINT `FKlbjpo227xfvvyg5umkr740csv` FOREIGN KEY (`id_route`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `FKsu9l0ql6iqmp7txhwapmi7256` FOREIGN KEY (`id_origin_location`) REFERENCES `location` (`id_location`);

--
-- Filtros para la tabla `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK6njoh3pti5jnlkowken3r8ttn` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Filtros para la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  ADD CONSTRAINT `FK7t3kc8g8s8whberdxrc8vb0cy` FOREIGN KEY (`id_vehicle_type`) REFERENCES `vehicle_type` (`id_vehicle_type`),
  ADD CONSTRAINT `FKin7ily1ads8u6ru46w9dvn07f` FOREIGN KEY (`id_vehicle_brand`) REFERENCES `vehicle_brand` (`id_vehicle_brand`);

--
-- Filtros para la tabla `vehicle_unit`
--
ALTER TABLE `vehicle_unit`
  ADD CONSTRAINT `FK76saw0qq8knx7gqliqo2qlypi` FOREIGN KEY (`id_vehicle_model`) REFERENCES `vehicle_model` (`id_vehicle_model`),
  ADD CONSTRAINT `FKlnbrcaavhct6nu8hvciaj9gv7` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `FKposaui017935q8e1mly0fjv3o` FOREIGN KEY (`id_vehicle_use_type`) REFERENCES `vehicle_use_type` (`id_vehicle_use_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
