-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 18-12-2025 a las 17:26:11
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `videojuegosex`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `idVideojuego` int(11) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Plataforma` varchar(255) NOT NULL,
  `Imagen` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`idVideojuego`, `Nombre`, `Plataforma`, `Imagen`) VALUES
(1, 'Contra', 'SNES', 'https://upload.wikimedia.org/wikipedia/en/6/65/Contra_cover.jpg'),
(2, 'Videojuego 2', 'Plataforma 2', 'https://upload.wikimedia.org/wikipedia/en/6/65/Contra_cover.jpg'),
(3, 'Nombre 3', 'Plataforma 3', 'https://upload.wikimedia.org/wikipedia/en/6/65/Contra_cover.jpg'),
(4, 'Nombre 4', 'Plataforma 4', 'https://upload.wikimedia.org/wikipedia/en/6/65/Contra_cover.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`idVideojuego`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  MODIFY `idVideojuego` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
