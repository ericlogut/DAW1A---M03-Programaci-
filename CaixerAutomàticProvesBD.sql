-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-05-2023 a las 08:52:17
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `caixerautomatic2`
--
CREATE DATABASE IF NOT EXISTS `caixerautomatic2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `caixerautomatic2`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `tipusDeCompte` enum('Corrent','Estalvi') NOT NULL,
  `saldo` decimal(10,2) DEFAULT NULL,
  `usuari_id` int(11) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compte`
--

INSERT INTO `compte` (`id`, `tipusDeCompte`, `saldo`, `usuari_id`) VALUES
(1, 'Corrent', '1012.00', 1),
(2, 'Estalvi', '2102.00', 2),
(3, 'Corrent', '1500.00', 3),
(4, 'Estalvi', '3500.00', 4),
(5, 'Corrent', '2500.00', 5),
(6, 'Estalvi', '4500.00', 6),
(7, 'Corrent', '1800.00', 7),
(8, 'Corrent', '1001.00', 1),
(9, 'Corrent', '850.00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factures`
--

CREATE TABLE `factures` (
  `id` int(11) NOT NULL,
  `usuari_id` int(11) UNSIGNED DEFAULT NULL,
  `descripcio` varchar(255) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factures`
--

INSERT INTO `factures` (`id`, `usuari_id`, `descripcio`, `total`) VALUES
(1, 1, 'Compra en supermercado', '150.00'),
(2, 2, 'Compra en tienda de ropa', '250.00'),
(3, 2, 'Pago de suscripción mensual', '30.00'),
(4, 5, 'Compra en supermercado', '120.00'),
(5, 5, 'Gastos de transporte', '50.00'),
(6, 7, 'Gastos de gimnasio', '60.00'),
(7, 7, 'Cena en restaurante', '80.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventari_billets`
--

CREATE TABLE `inventari_billets` (
  `id` int(11) NOT NULL,
  `valor_bitllet` int(11) NOT NULL,
  `quantitat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventari_billets`
--

INSERT INTO `inventari_billets` (`id`, `valor_bitllet`, `quantitat`) VALUES
(1, 5, 101),
(2, 10, 209),
(3, 20, 291),
(4, 50, 400),
(5, 100, 502),
(6, 200, 1000),
(7, 500, 1200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moviment`
--

CREATE TABLE `moviment` (
  `id` int(11) NOT NULL,
  `tipusDeMoviment` enum('Retirar','Transferencia','Ingres','Bizum','Factura') NOT NULL,
  `data` date DEFAULT NULL,
  `quantitat` decimal(10,2) DEFAULT NULL,
  `compteOrigen_id` int(11) DEFAULT NULL,
  `compteDesti_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `moviment`
--

INSERT INTO `moviment` (`id`, `tipusDeMoviment`, `data`, `quantitat`, `compteOrigen_id`, `compteDesti_id`) VALUES
(1, 'Ingres', '2023-05-05', '500.00', 1, NULL),
(2, 'Ingres', '2023-05-06', '1000.00', 3, NULL),
(3, 'Retirar', '2023-05-07', '500.00', 3, NULL),
(4, 'Ingres', '2023-05-08', '2000.00', 4, NULL),
(5, 'Retirar', '2023-05-09', '1000.00', 4, NULL),
(6, 'Ingres', '2023-05-10', '3000.00', 6, NULL),
(7, 'Transferencia', '2023-05-11', '500.00', 6, 1),
(14, 'Retirar', '2023-05-04', '220.00', 1, NULL),
(15, 'Ingres', '2023-05-04', '110.00', NULL, 1),
(16, 'Ingres', '2023-05-05', '135.00', NULL, 1),
(17, 'Retirar', '2023-05-05', '35.00', 1, NULL),
(18, 'Transferencia', '2023-05-05', '100.00', 1, 8),
(19, 'Bizum', '2023-05-05', '100.00', 8, 2),
(20, 'Ingres', '2023-05-05', '35.00', NULL, 1),
(21, 'Retirar', '2023-05-05', '35.00', 1, NULL),
(22, 'Transferencia', '2023-05-05', '1.00', 1, 8),
(23, 'Bizum', '2023-05-05', '1.00', 1, 2),
(24, 'Ingres', '2023-05-05', '215.00', NULL, 1),
(25, 'Ingres', '2023-05-05', '2885.00', NULL, 1),
(26, 'Retirar', '2023-05-05', '3085.00', 1, NULL),
(27, 'Transferencia', '2023-05-05', '13.00', 1, 8),
(28, 'Transferencia', '2023-05-05', '13.00', 8, 1),
(29, 'Bizum', '2023-05-05', '1.00', 1, 2),
(30, 'Factura', '2023-05-05', '150.00', 9, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `targeta`
--

CREATE TABLE `targeta` (
  `id` int(11) NOT NULL,
  `num_targeta` varchar(16) NOT NULL,
  `data_caducitat` date NOT NULL,
  `codi_seguretat` int(11) NOT NULL,
  `compte_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `targeta`
--

INSERT INTO `targeta` (`id`, `num_targeta`, `data_caducitat`, `codi_seguretat`, `compte_id`) VALUES
(1, '2345678923456789', '2026-01-31', 234, 2),
(2, '3456789034567890', '2027-06-30', 345, 4),
(3, '4567890123456789', '2028-03-31', 654, 6);

--
-- Disparadores `targeta`
--
DELIMITER $$
CREATE TRIGGER `check_compte_estalvi_before_insert` BEFORE INSERT ON `targeta` FOR EACH ROW BEGIN
  DECLARE compte_tipus ENUM('Corrent', 'Estalvi');

  SELECT tipusDeCompte INTO compte_tipus
  FROM compte
  WHERE id = NEW.compte_id;

  IF compte_tipus != 'Estalvi' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Només els comptes que son de tipus Estalvi poden tenir targetes associades';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuaris`
--

CREATE TABLE `usuaris` (
  `id` int(11) UNSIGNED NOT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `contrasenya` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuaris`
--

INSERT INTO `usuaris` (`id`, `nom`, `contrasenya`) VALUES
(1, 'Juan Perez', '1234'),
(2, 'Maria Garcia', '12345678'),
(3, 'David Lopez', '12345678'),
(4, 'Sofia Martinez', '12345678'),
(5, 'Carlos Perez', '12345678'),
(6, 'Laura Ramirez', '12345678'),
(7, 'David Morales', '12345678');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuari_id` (`usuari_id`);

--
-- Indices de la tabla `factures`
--
ALTER TABLE `factures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuari_id` (`usuari_id`);

--
-- Indices de la tabla `inventari_billets`
--
ALTER TABLE `inventari_billets`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `moviment`
--
ALTER TABLE `moviment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compteOrigen_id` (`compteOrigen_id`),
  ADD KEY `compteDesti_id` (`compteDesti_id`);

--
-- Indices de la tabla `targeta`
--
ALTER TABLE `targeta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `compte_id` (`compte_id`);

--
-- Indices de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `factures`
--
ALTER TABLE `factures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `inventari_billets`
--
ALTER TABLE `inventari_billets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `moviment`
--
ALTER TABLE `moviment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `targeta`
--
ALTER TABLE `targeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuaris`
--
ALTER TABLE `usuaris`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`usuari_id`) REFERENCES `usuaris` (`id`);

--
-- Filtros para la tabla `factures`
--
ALTER TABLE `factures`
  ADD CONSTRAINT `factures_ibfk_1` FOREIGN KEY (`usuari_id`) REFERENCES `usuaris` (`id`);

--
-- Filtros para la tabla `moviment`
--
ALTER TABLE `moviment`
  ADD CONSTRAINT `moviment_ibfk_1` FOREIGN KEY (`compteOrigen_id`) REFERENCES `compte` (`id`),
  ADD CONSTRAINT `moviment_ibfk_2` FOREIGN KEY (`compteDesti_id`) REFERENCES `compte` (`id`);

--
-- Filtros para la tabla `targeta`
--
ALTER TABLE `targeta`
  ADD CONSTRAINT `targeta_ibfk_1` FOREIGN KEY (`compte_id`) REFERENCES `compte` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
