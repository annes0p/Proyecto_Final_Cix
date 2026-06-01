-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-06-2026 a las 09:10:20
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
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id_category`, `name`, `description`, `status`) VALUES
(1, 'Aceites de motor', 'Lubricantes principales para el funcionamiento del motor. Reducen fricción, desgaste y ayudan a mantener la temperatura adecuada.', 1),
(2, 'Aceites de transmisión', 'Fluidos diseñados para cajas manuales, automáticas y diferenciales. Permiten cambios suaves y protegen engranajes bajo alta presión.', 1),
(3, 'Grasas lubricantes', 'Lubricantes semisólidos usados en rodamientos, chasis y piezas móviles expuestas a carga pesada o poca frecuencia de mantenimiento.', 1),
(4, 'Refrigerantes y anticongelantes', 'Fluidos que regulan la temperatura del motor, evitando sobrecalentamiento y congelamiento, además de proteger el sistema de corrosión.', 1),
(5, 'Líquidos de freno', 'Fluidos hidráulicos para sistemas de frenado', 1),
(6, 'Aditivos automotrices', 'Mejoradores de rendimiento del motor y combustible', 1),
(7, 'Limpieza y mantenimiento', 'Productos de limpieza de motor y piezas', 1),
(8, 'Lubricantes en aerosol', 'Sprays multiuso y penetrantes', 1),
(9, 'Fluidos hidráulicos', 'Aceites hidráulicos para maquinaria', 1),
(10, 'Category endpoint edited', 'Category created and edited from backend enpoint', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client`
--

CREATE TABLE `client` (
  `id_client` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `f_last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `m_last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `document_type` enum('DNI','RUC') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `doc_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `id_location` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `trusted` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `client`
--

INSERT INTO `client` (`id_client`, `name`, `f_last_name`, `m_last_name`, `document_type`, `doc_number`, `email`, `phone_number`, `address`, `id_location`, `status`, `trusted`, `created_at`, `updated_at`) VALUES
(1, 'cliente falso', 'creado', 'database', 'DNI', '11223344', 'clientbd@cliente.com', '999888777', 'Calle falsa 123', NULL, 1, b'0', '2026-05-29 13:45:55.000000', '2026-05-29 13:45:55.000000'),
(2, 'Client', 'Edited', 'Enpoint', 'RUC', '20112233445', 'cliente2@endpoint.com', '111222333', 'Calle irreal 321', 1, 1, b'0', '2026-05-29 14:01:00.795388', '2026-05-29 14:02:29.302774');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `document_series`
--

CREATE TABLE `document_series` (
  `id_document_series` bigint NOT NULL,
  `series` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `current_number` bigint DEFAULT NULL,
  `status` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `document_series`
--

INSERT INTO `document_series` (`id_document_series`, `series`, `current_number`, `status`) VALUES
(1, 'B001', 1, 1),
(2, 'F001', 2, 1),
(3, 'N001', 0, 1);

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

--
-- Volcado de datos para la tabla `inventory`
--

INSERT INTO `inventory` (`id_inventory`, `min_stock`, `stock`, `updated_at`, `id_product`, `created_at`) VALUES
(1, 20, 39, '2026-06-01 00:48:31.975971', 1, '2026-05-31 23:09:18.000000'),
(2, 5, 50, '2026-05-31 23:16:09.376326', 40, '2026-05-31 23:16:09.376326'),
(3, 0, 10, '2026-06-01 02:07:43.353738', 41, '2026-06-01 02:07:43.334895'),
(4, 0, 1, '2026-06-01 02:07:43.395151', 42, '2026-06-01 02:07:43.378841'),
(5, 0, 5, '2026-06-01 02:07:43.415067', 43, '2026-06-01 02:07:43.402156'),
(6, 0, 9, '2026-06-01 02:07:43.445242', 44, '2026-06-01 02:07:43.420095');

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
(2, NULL, 'Gestión de usuarios y roles asociados', 'Usuarios', '/admin/usuarios'),
(3, NULL, 'Gestión de roles y permisos', 'Roles', '/admin/roles'),
(4, NULL, 'Gestión de lubricantes, aceites, etc', 'Productos', '/admin/productos'),
(5, NULL, 'Gestión de ventas realizadas', 'Ventas', '/admin/ventas'),
(6, NULL, 'Gestión de stock y stock mínimo', 'Inventario', '/admin/inventario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notification`
--

CREATE TABLE `notification` (
  `id_notification` bigint NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  `notification_status` enum('READ','UNREAD') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `notification`
--

INSERT INTO `notification` (`id_notification`, `title`, `message`, `id_user`, `notification_status`, `created_at`, `updated_at`) VALUES
(1, 'Prueba 1', 'Este es un mensaje de prueba', 1, 'UNREAD', '2026-05-24 23:05:50.000000', '2026-05-24 23:05:50.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id_product` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `viscosity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_product_brand` bigint DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id_product`, `name`, `description`, `price`, `viscosity`, `id_product_brand`, `id_category`, `status`, `created_at`, `updated_at`) VALUES
(1, 'EDGE 10W-40', 'Aceite semisintético para motores gasolina y diésel, alta protección bajo presión', 140.00, '10W-40', 2, 1, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(34, 'MaxLife ATF', 'Fluido para transmisión automática de alto kilometraje que mejora suavidad de cambios', 120.00, 'ATF', 4, 2, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(35, 'Top Tec ATF 1800', 'Fluido premium para transmisiones automáticas modernas y CVT', 155.00, 'ATF', 6, 2, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(36, 'XHP 222', 'Grasa de litio de alta resistencia para rodamientos y cargas pesadas', 45.00, 'NLGI 2', 11, 3, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(37, 'Gadus S2 V220', 'Grasa multipropósito para aplicaciones automotrices e industriales', 40.00, 'NLGI 2', 12, 3, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(38, 'Extended Life Coolant', 'Refrigerante de larga duración anti corrosión y sobrecalentamiento', 35.00, NULL, 15, 4, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(39, 'Long Life Antifreeze', 'Anticongelante listo para uso en motores livianos y pesados', 32.00, NULL, 16, 4, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(40, 'DOT 4 Brake Fluid', 'Fluido de freno de alto punto de ebullición para sistemas exigentes', 28.00, 'DOT 4', 18, 5, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(41, 'DOT 3 Brake Fluid', 'Líquido de freno estándar para vehículos livianos', 22.00, 'DOT 3', 19, 5, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(42, 'Cera Tec', 'Aditivo antifricción que reduce desgaste del motor', 85.00, NULL, 6, 6, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(43, 'Fuel Injector Cleaner', 'Limpiador de inyectores que mejora rendimiento del combustible', 35.00, NULL, 22, 6, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(44, 'Engine Flush', 'Limpieza interna del motor antes del cambio de aceite', 45.00, NULL, 6, 7, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(45, 'Engine Degreaser', 'Desengrasante para limpieza de motores y piezas metálicas', 38.00, NULL, 25, 7, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(46, 'Multi-Use Spray', 'Lubricante multiuso en spray contra humedad y corrosión', 25.00, 'Spray', 14, 8, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(47, 'Penetrating Oil', 'Aceite penetrante para aflojar piezas oxidadas', 30.00, 'Spray', 25, 8, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(48, 'Tellus S2 M 46', 'Aceite hidráulico para sistemas de alta presión industrial', 180.00, 'ISO VG 46', 3, 9, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(49, 'DTE 25 Hydraulic Oil', 'Fluido hidráulico industrial de alta estabilidad térmica', 175.00, 'ISO VG 46', 1, 9, 1, '2026-05-25 00:17:42.000000', '2026-05-25 00:17:42.000000'),
(50, 'Product endpoint edited', 'Product created from endpint', 333.33, 'Random viscosity', 1, 1, 2, '2026-05-25 04:30:23.753435', '2026-05-25 04:32:00.490657');

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
  `id_vehicle_model` bigint DEFAULT NULL,
  `id_vehicle_use_type` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `priority` enum('HIGH','LOW','MEDIUM') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
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
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `purchase` (
  `id_purchase` bigint NOT NULL,
  `id_supplier` bigint DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `estimated_date` date DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `reception_status` enum('PARTIALLY_RECIEVED','PENDING','RECIEVED') DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `purchase`
--

INSERT INTO `purchase` (`id_purchase`, `id_supplier`, `purchase_date`, `estimated_date`, `delivery_date`, `reception_status`, `total`) VALUES
(1, 1, '2026-05-31', '2026-06-02', NULL, 'PENDING', 548.00),
(2, 2, '2026-05-31', '2025-03-15', NULL, 'PENDING', 1390.00),
(5, 1, '2026-06-01', '2026-06-10', '2026-06-01', 'PENDING', 1120.00),
(6, 1, '2026-06-01', '2026-06-10', '2026-06-01', 'PENDING', 885.00);

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

--
-- Volcado de datos para la tabla `purchase_detail`
--

INSERT INTO `purchase_detail` (`id_purchase_detail`, `line_total`, `quantity`, `unit_price`, `id_product`, `id_purchase`) VALUES
(1, 420.00, 3, 140.00, 1, 1),
(2, 128.00, 4, 32.00, 39, 1),
(3, 380.00, 10, 38.00, 45, NULL),
(4, 980.00, 7, 140.00, 1, NULL),
(5, 30.00, 1, 30.00, 47, NULL),
(14, 450.00, 10, 45.00, 36, NULL),
(15, 155.00, 1, 155.00, 35, NULL),
(16, 200.00, 5, 40.00, 37, NULL),
(17, 315.00, 9, 35.00, 38, NULL),
(18, 220.00, 10, 22.00, 41, NULL),
(19, 85.00, 1, 85.00, 42, NULL),
(20, 175.00, 5, 35.00, 43, NULL),
(21, 405.00, 9, 45.00, 44, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id_role` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id_role`, `name`, `description`, `status`) VALUES
(1, 'Administrador', 'Acceso completo a todos los módulos del sistema', 1),
(2, 'Test role', 'Test role edited from endpoint', 2);

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
(1, 2),
(2, 1),
(2, 2);

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
  `sale_date` datetime(6) DEFAULT NULL,
  `voucher_type` enum('INVOICE','RECEIPT','SALE_NOTE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `series` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `tax_amount` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `payment_method` enum('CARD','CASH','TRANSFER','YAPE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `transaction_status` enum('CANCELED','COMPLETED','PENDING') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `sale`
--

INSERT INTO `sale` (`id_sale`, `sale_date`, `voucher_type`, `series`, `number`, `subtotal`, `tax_amount`, `total`, `payment_method`, `transaction_status`, `id_client`, `id_user`) VALUES
(1, '2026-05-31 21:56:10.000000', 'RECEIPT', 'B001', '00000001', 32.20, 5.80, 38.00, 'CASH', 'COMPLETED', 1, 1),
(2, '2026-05-31 22:03:39.896296', 'INVOICE', 'F001', '00000001', 3114.12, 560.53, 3674.65, 'CASH', 'COMPLETED', 2, 1),
(3, '2026-05-31 22:06:17.153028', 'INVOICE', 'F001', '00000002', 237.29, 42.71, 280.00, 'CASH', 'COMPLETED', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale_detail`
--

CREATE TABLE `sale_detail` (
  `id_sale_detail` bigint NOT NULL,
  `id_sale` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `tax_amount` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `unit_price` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `sale_detail`
--

INSERT INTO `sale_detail` (`id_sale_detail`, `id_sale`, `quantity`, `subtotal`, `tax_amount`, `total`, `id_product`, `unit_price`) VALUES
(1, 1, 1, 32.20, 5.80, 38.00, 45, 38.00),
(2, 2, 5, 1412.42, 254.23, 1666.65, 50, 333.33),
(3, 2, 1, 23.73, 4.27, 28.00, 40, 28.00),
(4, 2, 11, 1677.97, 302.03, 1980.00, 48, 180.00),
(5, NULL, 2, 237.29, 42.71, 280.00, 1, 140.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_loan`
--

CREATE TABLE `stock_loan` (
  `id_stock_loan` bigint NOT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `quantity_loaned` bigint DEFAULT NULL,
  `quantity_remaining` bigint DEFAULT NULL,
  `loan_status` enum('PARTIALLY_RETURNED','PENDING','RETURNED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `stock_loan`
--

INSERT INTO `stock_loan` (`id_stock_loan`, `id_product`, `id_client`, `quantity_loaned`, `quantity_remaining`, `loan_status`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 10, NULL, 'PENDING', '2026-05-31 20:22:05.000000', '2026-05-31 20:22:05.000000'),
(2, 35, 2, 10, 5, 'PARTIALLY_RETURNED', '2026-05-31 20:25:09.067177', '2026-05-31 20:28:15.958399');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock_movement`
--

CREATE TABLE `stock_movement` (
  `id_stock_movement` bigint NOT NULL,
  `initial_stock` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `final_stock` bigint DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `movement_type` enum('ADJUSTMENT_IN','ADJUSTMENT_OUT','IN','OUT','PURCHASE_RETURN','SALE_RETURN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `movement_date` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `stock_movement`
--

INSERT INTO `stock_movement` (`id_stock_movement`, `initial_stock`, `quantity`, `final_stock`, `id_product`, `movement_type`, `movement_date`, `created_at`, `updated_at`) VALUES
(1, 30, 10, 40, 1, 'ADJUSTMENT_IN', NULL, '2026-06-01 00:41:41.837185', '2026-06-01 00:41:41.837185'),
(2, 40, 1, 39, 1, 'ADJUSTMENT_OUT', NULL, '2026-06-01 00:48:31.967582', '2026-06-01 00:48:31.967582'),
(3, 0, 10, 10, 36, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:05:46.216278', '2026-06-01 02:05:46.216278'),
(4, 0, 1, 1, 35, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:05:46.237791', '2026-06-01 02:05:46.237791'),
(5, 0, 5, 5, 37, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:05:46.248352', '2026-06-01 02:05:46.248352'),
(6, 0, 9, 9, 38, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:05:46.257876', '2026-06-01 02:05:46.259346'),
(7, 0, 10, 10, 41, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:07:43.341728', '2026-06-01 02:07:43.341728'),
(8, 0, 1, 1, 42, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:07:43.382271', '2026-06-01 02:07:43.382271'),
(9, 0, 5, 5, 43, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:07:43.406235', '2026-06-01 02:07:43.406235'),
(10, 0, 9, 9, 44, 'IN', '2026-06-01 00:00:00.000000', '2026-06-01 02:07:43.423111', '2026-06-01 02:07:43.423111');

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
  `legal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doc_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `document_type` enum('DNI','RUC') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `legal_name`, `doc_number`, `document_type`, `email`, `phone_number`, `address`, `status`, `created_at`, `updated_at`) VALUES
(1, 'DB Test supplier S.A.C.', '20112233445', 'RUC', 'sup1@supplier.com', '999111222', 'Calle fantasía 123', 1, '2026-05-31 15:42:57.000000', '2026-05-31 15:42:57.000000'),
(2, 'EP Test supplier edited S.A.C.', '20332233445', 'RUC', 'sup2@supplier.com', '999333222', 'Calle inefable 123', 1, '2026-05-31 15:50:22.777250', '2026-05-31 15:51:00.887787');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supplier_product`
--

CREATE TABLE `supplier_product` (
  `id_supplier_product` bigint NOT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_supplier` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `supplier_product`
--

INSERT INTO `supplier_product` (`id_supplier_product`, `id_product`, `id_supplier`) VALUES
(1, 1, 1),
(2, 34, 1),
(3, 39, 1);

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
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_role` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_user`, `username`, `email`, `password`, `id_role`, `status`, `created_at`, `updated_at`) VALUES
(1, 'admin', 'admin@admin.com', '$2a$10$Mt2ltioT746kMKL0v0bprOAKGV2TdoDKSN79uMv/789.Ph0M/HyDO', 1, 1, '2026-05-25 09:43:34.000000', NULL),
(2, 'testpointactualizado', 'test@point.com', '$2a$10$Pq.bIABonWTA4ftQbGy9WuSp1oKscMzaku82UF0WHhUVlBAyxobi2', 1, 2, '2026-05-25 03:00:09.528799', '2026-05-25 03:02:57.356745');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_brand`
--

CREATE TABLE `vehicle_brand` (
  `id_vehicle_brand` bigint NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vehicle_brand`
--

INSERT INTO `vehicle_brand` (`id_vehicle_brand`, `name`) VALUES
(1, 'Toyota'),
(2, 'Hyundai'),
(3, 'Kia'),
(4, 'Chevrolet'),
(5, 'Nissan'),
(6, 'Suzuki'),
(7, 'Mazda'),
(8, 'Volkswagen'),
(9, 'Ford'),
(10, 'Honda'),
(11, 'Mitsubishi'),
(12, 'Renault'),
(13, 'Chery'),
(14, 'BYD'),
(15, 'Jetour'),
(16, 'JAC'),
(17, 'Great Wall'),
(18, 'BMW'),
(19, 'Mercedes-Benz'),
(20, 'Audi');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_type`
--

CREATE TABLE `vehicle_type` (
  `id_vehicle_type` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vehicle_type`
--

INSERT INTO `vehicle_type` (`id_vehicle_type`, `name`, `description`) VALUES
(1, 'Sedán', 'Automóvil de pasajeros.'),
(2, 'Hatchback', 'Automóvil compacto.'),
(3, 'SUV', 'Vehículo utilitario deportivo.'),
(4, 'Pickup', 'Vehículo con plataforma de carga.'),
(5, 'Coupé', 'Automóvil deportivo de dos puertas.'),
(6, 'Convertible', 'Automóvil con techo retráctil.'),
(7, 'Van', 'Vehículo para pasajeros o carga ligera.'),
(8, 'Furgón', 'Vehículo para transporte de mercancías.'),
(9, 'Microbús', 'Vehículo de transporte de pasajeros de capacidad media.'),
(10, 'Bus', 'Vehículo de transporte colectivo de pasajeros.'),
(11, 'Camión', 'Vehículo de transporte de carga pesada.'),
(12, 'Motocicleta', 'Vehículo motorizado de dos ruedas.'),
(13, 'Tractor', 'Vehículo para arrastre o trabajo agrícola.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehicle_unit`
--

CREATE TABLE `vehicle_unit` (
  `id_vehicle_unit` bigint NOT NULL,
  `plate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_vehicle_model` bigint DEFAULT NULL,
  `id_client` bigint DEFAULT NULL,
  `id_vehicle_use_type` bigint DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vehicle_unit`
--

INSERT INTO `vehicle_unit` (`id_vehicle_unit`, `plate`, `id_vehicle_model`, `id_client`, `id_vehicle_use_type`, `color`, `status`, `created_at`, `updated_at`) VALUES
(1, 'BCS-991', 1, 1, 1, 'Rojo', 1, '2026-05-31 01:33:27.000000', '2026-05-31 01:33:27.000000'),
(2, 'PEN-001', 9, 2, 6, 'Blanco', 1, '2026-05-31 01:48:02.000000', '2026-05-31 01:48:02.000000'),
(3, 'EPE-001', 6, 2, 3, 'Rosado', 2, '2026-05-31 02:10:54.302891', '2026-05-31 02:11:41.324749');

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
  ADD KEY `FK47mkgh7ihi56odl0bbchxmpkl` (`id_vehicle_use_type`);

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
  MODIFY `id_inventory` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `location`
--
ALTER TABLE `location`
  MODIFY `id_location` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `module`
--
ALTER TABLE `module`
  MODIFY `id_module` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `id_purchase` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  MODIFY `id_purchase_detail` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id_role` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `id_route` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sale`
--
ALTER TABLE `sale`
  MODIFY `id_sale` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sale_detail`
--
ALTER TABLE `sale_detail`
  MODIFY `id_sale_detail` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `stock_loan`
--
ALTER TABLE `stock_loan`
  MODIFY `id_stock_loan` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `stock_movement`
--
ALTER TABLE `stock_movement`
  MODIFY `id_stock_movement` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `stock_prediction`
--
ALTER TABLE `stock_prediction`
  MODIFY `id_stock_prediction` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `supplier_product`
--
ALTER TABLE `supplier_product`
  MODIFY `id_supplier_product` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `id_vehicle_brand` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `vehicle_model`
--
ALTER TABLE `vehicle_model`
  MODIFY `id_vehicle_model` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehicle_type`
--
ALTER TABLE `vehicle_type`
  MODIFY `id_vehicle_type` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `vehicle_unit`
--
ALTER TABLE `vehicle_unit`
  MODIFY `id_vehicle_unit` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `vehicle_use_type`
--
ALTER TABLE `vehicle_use_type`
  MODIFY `id_vehicle_use_type` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
  ADD CONSTRAINT `FK47mkgh7ihi56odl0bbchxmpkl` FOREIGN KEY (`id_vehicle_use_type`) REFERENCES `vehicle_use_type` (`id_vehicle_use_type`),
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
