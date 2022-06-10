-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2022 a las 21:34:39
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cobros`
--

CREATE TABLE `cobros` (
  `idCobro` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `idFCobro` int(11) NOT NULL,
  `cobrado` decimal(18,2) NOT NULL,
  `imputado` decimal(18,2) NOT NULL,
  `idServicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `cobros`
--

INSERT INTO `cobros` (`idCobro`, `fecha`, `idPaciente`, `idFCobro`, `cobrado`, `imputado`, `idServicio`) VALUES
(1, '2022-05-24', 1, 1, '250.00', '250.00', 1),
(2, '2022-05-24', 3, 2, '400.00', '400.00', 3),
(3, '2022-05-24', 4, 3, '450.00', '450.00', 4),
(4, '2022-05-24', 5, 1, '250.00', '250.00', 5),
(5, '2022-05-24', 6, 2, '350.00', '350.00', 6),
(6, '2022-05-24', 7, 3, '250.00', '250.00', 7),
(7, '2022-05-24', 8, 1, '80.00', '80.00', 8),
(8, '2022-05-24', 9, 3, '60.00', '60.00', 9),
(9, '2022-05-24', 10, 1, '40.00', '40.00', 10),
(10, '2022-05-24', 11, 2, '20.00', '20.00', 11),
(11, '2022-05-24', 12, 2, '50.00', '50.00', 12),
(12, '2022-05-24', 13, 2, '100.00', '100.00', 13),
(13, '2022-05-24', 14, 1, '120.00', '120.00', 14),
(14, '2022-05-24', 15, 3, '150.00', '150.00', 15),
(15, '2022-05-24', 16, 1, '60.00', '60.00', 16),
(16, '2022-05-24', 17, 3, '180.00', '180.00', 17),
(17, '2022-05-24', 18, 3, '75.00', '75.00', 18),
(18, '2022-05-24', 19, 1, '40.00', '40.00', 19),
(19, '2022-05-24', 20, 3, '20.00', '20.00', 20),
(20, '2022-05-24', 21, 3, '50.00', '50.00', 21),
(21, '2022-05-24', 22, 2, '350.00', '350.00', 22),
(22, '2022-05-24', 23, 1, '50.00', '50.00', 23),
(23, '2022-05-24', 24, 1, '45.00', '45.00', 24),
(24, '2022-05-24', 25, 3, '400.00', '400.00', 25),
(25, '2022-05-24', 27, 3, '80.00', '80.00', 27),
(26, '2022-05-24', 29, 2, '30.00', '30.00', 29),
(27, '2022-05-24', 30, 2, '600.00', '600.00', 30),
(28, '2022-05-24', 31, 1, '250.00', '250.00', 31),
(29, '2022-05-24', 32, 3, '150.00', '150.00', 32),
(30, '2022-05-24', 33, 1, '300.00', '300.00', 33),
(31, '2022-05-24', 34, 2, '50.00', '50.00', 34),
(32, '2022-05-24', 35, 1, '65.00', '65.00', 35),
(33, '2022-05-24', 37, 3, '1000.00', '1000.00', 37),
(34, '2022-05-24', 37, 3, '1000.00', '2000.00', 37),
(35, '2022-05-24', 37, 3, '1000.00', '3000.00', 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familias`
--

CREATE TABLE `familias` (
  `idFamilia` int(11) NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `familias`
--

INSERT INTO `familias` (`idFamilia`, `nombre`) VALUES
(1, 'CIRUGIA'),
(2, 'CORONA'),
(3, 'EMPASTES'),
(4, 'ENDODONCIA'),
(5, 'EXTRACCIONES'),
(6, 'GENERAL ODONTOLOGIA'),
(7, 'IMPLANTES'),
(8, 'ORTODONCIA'),
(9, 'PUENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fcobro`
--

CREATE TABLE `fcobro` (
  `idFCobro` int(11) NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL,
  `idGrupoCaja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `fcobro`
--

INSERT INTO `fcobro` (`idFCobro`, `nombre`, `idGrupoCaja`) VALUES
(1, 'EFECTIVO', 1),
(2, 'TARJETA', 2),
(3, 'TRANSFERENCIA', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupocaja`
--

CREATE TABLE `grupocaja` (
  `idGrupoCaja` int(11) NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `grupocaja`
--

INSERT INTO `grupocaja` (`idGrupoCaja`, `nombre`) VALUES
(1, 'BANCO 1'),
(2, 'BANCO 2'),
(3, 'BANCO 3'),
(4, 'BANCO 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liquidaciones`
--

CREATE TABLE `liquidaciones` (
  `idLiquidacion` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `comision` decimal(18,2) NOT NULL,
  `idProfesional` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `idPaciente` int(11) NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL,
  `apellidos` varchar(250) CHARACTER SET utf8 NOT NULL,
  `nacimiento` date NOT NULL,
  `genero` varchar(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `dni` varchar(9) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `direccion` varchar(500) COLLATE utf8mb4_spanish_ci NOT NULL,
  `telefono` int(11) NOT NULL,
  `codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`idPaciente`, `nombre`, `apellidos`, `nacimiento`, `genero`, `dni`, `direccion`, `telefono`, `codigo`) VALUES
(1, 'CRISTIAN IONUT', 'ESCOURIDO AGUINACO', '1985-10-08', 'M', '60545919Y', 'Calle 19', 698171147, 1),
(2, 'MHAMED', 'AMBRES CERCADO', '2004-03-17', 'M', '91089260Z', 'Calle 24', 667457393, 2),
(3, 'KEITH EDWARD', 'GARCIA DE PAREDES PENIS', '1969-12-01', 'M', '35998033C', 'Calle 45', 780000588, 3),
(4, 'MARIA NILSA', 'LOUGEDO ZIAN', '1987-05-12', 'F', '91523079F', 'Calle 23', 690778752, 4),
(5, 'JIANJUN', 'DE AVILA TARRIAS', '1973-04-10', 'M', '94369029Y', 'Calle 11', 722694698, 5),
(6, 'ANGELA MERCEDES', 'LEPE CROS', '2013-12-26', 'F', NULL, 'Calle 62', 626546922, 6),
(7, 'TONY', 'DUEÃ‘A HOJAS', '1993-03-08', 'M', '85008970G', 'Calle 54', 685449471, 7),
(8, 'LEOPOLDINA', 'LEZANA DE ANGELIS', '1930-03-18', 'F', '54095948V', 'Calle 73', 616769131, 8),
(9, 'DAVID LEANDRO', 'BELLES MARRACO', '1957-08-28', 'M', '72997070S', 'Calle 93', 740790337, 9),
(10, 'TEODOMIRA', 'GUNTIÃ‘AS TERRADILLOS', '1937-05-05', 'F', '69224417Y', 'Calle 43', 616050713, 10),
(11, 'RUBEN NICOLAS', 'CABRILLO GARNIER', '2001-05-17', 'M', '72610895X', 'Calle 70', 622097048, 11),
(12, 'DARWIN JAVIER', 'SAENZ DE SANTA MARIA BOUJAADA', '2007-07-11', 'M', '77083165E', 'Calle 81', 715538992, 12),
(13, 'JEANETH', 'MERIZALDE BADAOUI', '1926-07-06', 'F', '66876227R', 'Calle 34', 714235383, 13),
(14, 'LEONARDA MARIA', 'OSBORNE LULL', '1988-02-03', 'F', '76677183J', 'Calle 96', 674505723, 14),
(15, 'JESUS FLORENTINO', 'CALUGAR EL YAAKOUBI', '1943-11-20', 'M', '49853942Q', 'Calle 91', 695938978, 15),
(16, 'LUISA OLGA', 'GARCIA IZQUIERDO PEIROTEN', '1994-05-29', 'F', '96151085E', 'Calle 2', 675938599, 16),
(17, 'SARABJIT', 'ALMENDRO DONAIRE', '1991-07-14', 'F', '57803783E', 'Calle 36', 600763602, 17),
(18, 'ALEXANDRU COSMIN', 'MARTAGON VEGUE', '1991-05-15', 'M', '76804678L', 'Calle 50', 763463390, 18),
(19, 'AURORA ASCENSION', 'VALLEDOR OMS', '2002-01-17', 'F', '55361910J', 'Calle 91', 623592280, 19),
(20, 'IOANA DANIELA', 'SEQUEIRO MANZANA', '1957-01-02', 'F', '53863231Z', 'Calle 7', 687616987, 20),
(21, 'ALVARO ISIDRO', 'BARROCAL CURA', '1950-12-01', 'M', '59400736Q', 'Calle 36', 616368771, 21),
(22, 'ANDREEA CORINA', 'LACABA CALLARISA', '1983-05-18', 'F', '92793756A', 'Calle 86', 710644522, 22),
(23, 'FLORIN STEFAN', 'ESCARABAJAL ESCARPA', '1953-01-19', 'M', '40929389S', 'Calle 61', 697798684, 23),
(24, 'LOURDES CRISTINA', 'CASTANEDO ITURRASPE', '1979-09-13', 'F', '65940501F', 'Calle 21', 792284580, 24),
(25, 'BARBARA JANE', 'ABIZANDA CAÃ‘ES', '1964-08-15', 'F', '20553223D', 'Calle 62', 710392632, 25),
(26, 'STEVEN ANTHONY', 'POYATO VILLASEÃ‘OR', '1961-03-01', 'M', '51986009Y', 'Calle 42', 739368948, 26),
(27, 'LUZ TERESA', 'MAINZ OZCOIDI', '1978-03-24', 'F', '75401341G', 'Calle 13', 764123495, 27),
(28, 'IGNACIO EDUARDO', 'ANDREI HIJON', '1924-08-13', 'M', '47131420G', 'Calle 81', 686235710, 28),
(29, 'REYAD', 'ORON ASTUDILLO', '1983-07-06', 'M', '63951666M', 'Calle 20', 755940841, 29),
(30, 'MARTA NATIVIDAD', 'FERRADANS GHULAM', '2000-12-16', 'F', '98147105X', 'Calle 92', 683035725, 30),
(31, 'CESAR ARMANDO', 'COZAR CHILLON', '1957-04-12', 'M', '15529942C', 'Calle 47', 787221559, 31),
(32, 'ISABEL ANGUSTIAS', 'GIGIREY XARGAY', '1955-03-12', 'F', '40703054T', 'Calle 77', 628074832, 32),
(33, 'ANA VICENTA', 'SASHEVA BURGUI', '1954-11-25', 'F', '48173251G', 'Calle 80', 626253490, 33),
(34, 'BRYAN STALIN', 'MAGRIÃ‘A ALAGARDA', '1943-04-22', 'M', '89239705G', 'Calle 50', 726394828, 34),
(35, 'DIVINA PASTORA', 'GOROSTIZA GASSAMA', '1936-05-23', 'F', '87155299L', 'Calle 87', 687251198, 35),
(36, 'ANA MODESTA', 'LONGARES ROLLIZO', '1928-11-04', 'F', '23006570S', 'Calle 19', 725136089, 36),
(37, 'LAURA MARGARITA', 'ARRECHE MONAR', '1954-01-26', 'F', '70914150K', 'Calle 68', 706409352, 37),
(38, 'TATIANA PAOLA', 'OBAMA GHEORGHE', '1923-04-08', 'F', '25397892G', 'Calle 58', 681381040, 38),
(39, 'MIHAI MARCEL', 'GARCIA CALVILLO PARROT', '2004-10-09', 'M', '97024941Q', 'Calle 31', 678101295, 39),
(40, 'ANA ROSA', 'FLOTATS SABRI', '1942-07-11', 'F', '64450654P', 'Calle 2', 623584402, 40),
(41, 'IOANA RALUCA', 'SANCHEZ PANIAGUA CERQUEDA', '1964-12-10', 'F', '12512725N', 'Calle 35', 623569109, 41),
(42, 'FIOR ALIZA', 'ANSO ACEITON', '1983-06-03', 'F', '76487882W', 'Calle 88', 676248763, 42),
(43, 'ELVIRA LUISA', 'SANHAJI GABARRE', '1962-06-21', 'F', '71484962L', 'Calle 46', 626079141, 43),
(44, 'KATHERINE MISHELL', 'CONSTANZO GARCIA DEL REAL', '2001-01-22', 'F', '73685361P', 'Calle 62', 746949044, 44),
(45, 'TOURE', 'EMMANUEL BUQUERAS', '1953-03-06', 'M', '18914665V', 'Calle 23', 655642163, 45),
(46, 'RUSUDAN', 'ARZUA BERNARDI', '1931-11-11', 'F', '14096647Q', 'Calle 26', 715202643, 46),
(47, 'MARTA PINO', 'MACHIO MASSO', '1953-04-26', 'F', '76305931G', 'Calle 96', 679697654, 47),
(48, 'MELODIA', 'MIHU GUILLAMET', '1926-01-10', 'F', '52928934T', 'Calle 21', 642035293, 48),
(49, 'MONICA BEGOÃƒâ€˜A', 'MATIES FERREZUELO', '1964-06-07', 'F', '57380907R', 'Calle 26', 648694217, 49),
(50, 'EMILIO FELIX', 'LAIGLESIA ARAUZ', '1929-12-06', 'M', '45686087Z', 'Calle 15', 715407527, 50),
(51, 'ANTONIA CATALINA', 'JORRETO IZQUIER', '1923-08-01', 'F', '32920108R', 'Calle 22', 652597220, 51),
(52, 'PABLO SALVADOR', 'CAZA MUÃ‘ANA', '1999-12-07', 'M', '64110854X', 'Calle 90', 728765967, 52),
(53, 'BIDANE', 'VINCES PARRILLA', '1933-11-25', 'F', '10073496W', 'Calle 80', 790767670, 53),
(54, 'EKAI', 'CAPACETE EDJANG', '1999-07-01', 'M', '33527013M', 'Calle 79', 634788229, 54),
(55, 'SMAHAN', 'JOVANOVIC VAL', '1928-08-15', 'F', '73379262Q', 'Calle 1', 662800175, 55),
(56, 'IMEN', 'LAISECA SANCHEZ CID', '1994-05-22', 'F', '32988741W', 'Calle 67', 641620694, 56),
(57, 'FREDY ANDRES', 'GODON TORRESANO', '1967-08-15', 'M', '94217797E', 'Calle 41', 634001666, 57),
(58, 'CRISTIAN VASILE', 'DE TORRE PEDRIZA', '1991-05-06', 'M', '99604669H', 'Calle 87', 711748635, 58),
(59, 'YOSEBA', 'MAHRACH ARTAL', '1998-10-11', 'M', '36959381Z', 'Calle 43', 652880434, 59),
(60, 'DAVID IGNACIO', 'ESCOBAL PERUCHO', '1978-02-10', 'M', '55529785B', 'Calle 100', 644328829, 60),
(61, 'WENDY', 'LECHIGUERO TAVARES', '1933-01-08', 'F', '62017515Q', 'Calle 9', 697706254, 61),
(62, 'RAUL NICOLAS', 'SOLAUN GUMUCIO', '1984-07-27', 'M', '56634117K', 'Calle 65', 607501727, 62),
(63, 'AGUEDA TERESA', 'BELLET ARANIBAR', '2002-08-15', 'F', '62107215Q', 'Calle 9', 616982545, 63),
(64, 'COZMIN', 'DEHESA ARA', '1995-08-12', 'M', '62001129Y', 'Calle 97', 748591744, 64),
(65, 'CARLA ESTEFANIA', 'AJBAR ELU', '1954-01-31', 'F', '42808870M', 'Calle 49', 727755495, 65),
(66, 'AIXA', 'ERASUN VALLEJOS', '1928-06-30', 'F', '19648833W', 'Calle 30', 792747599, 66),
(67, 'GERMAN LUIS', 'CHAZARRA AMEZAGA', '2002-02-24', 'M', '75992960V', 'Calle 21', 729450258, 67),
(68, 'MAEVE', 'MONEVA ATENCIA', '1996-01-20', 'F', '23494427L', 'Calle 63', 684208376, 68),
(69, 'NURIA CONCEPCION', 'CIURANA FROLOVA', '1976-02-08', 'F', '77803867L', 'Calle 64', 793881354, 69),
(70, 'YANIS', 'VILLEDA GUIÃ‘ALES', '1971-11-29', 'F', '70226496K', 'Calle 25', 766112740, 70),
(71, 'IXONE', 'COBAS QUITIAN', '1979-04-13', 'F', '22198951H', 'Calle 59', 710451521, 71),
(72, 'FRANCISCA RAMONA', 'TAVIO COLIO', '1944-01-03', 'F', '49048950R', 'Calle 51', 679977230, 72),
(73, 'GURMUKH', 'LAUT RADEVA', '1983-02-05', 'M', '81770820R', 'Calle 99', 701983428, 73),
(74, 'ANGELES TRINIDAD', 'ENTRAMBASAGUAS VENTAJA', '1980-08-20', 'F', '99562288A', 'Calle 72', 790017492, 74),
(75, 'MATEO DANIEL', 'ZAROUAL PERAIRE', '1997-10-19', 'M', '83850780W', 'Calle 6', 751990911, 75),
(76, 'KAROL VANESSA', 'IRUN DE LA CUADRA', '1997-03-24', 'F', '33358976Y', 'Calle 81', 770763426, 76),
(77, 'QUAN', 'CASTRO TERESA', '1979-08-25', 'M', '35697433F', 'Calle 88', 685613627, 77),
(78, 'CLAUDIA MARGARITA', 'PETEIRA JATO', '1932-09-30', 'F', '33463175S', 'Calle 6', 682509583, 78),
(79, 'TIBURCIO', 'SUDUPE MIRET', '1957-04-04', 'M', '46411417Q', 'Calle 56', 725027255, 79),
(80, 'FRANCISCA YOLANDA', 'HERAS FADRIQUE', '1930-09-17', 'F', '34966061N', 'Calle 53', 661251573, 80),
(81, 'XIAOLE', 'LONGA CARDENES', '2002-07-01', 'F', '54962237B', 'Calle 89', 755428890, 81),
(82, 'AIJU', 'BENAGES EL AYOUBI', '1998-04-30', 'F', '62234759W', 'Calle 13', 647192722, 82),
(83, 'STEPHANIE MARIA', 'CORBELLA APARISI', '1935-06-19', 'F', '45134926W', 'Calle 76', 671095634, 83),
(84, 'MARIA TRINIDAD', 'PAREJA CANENCIA', '1979-03-30', 'F', '60576162G', 'Calle 55', 715664354, 84),
(85, 'JULENE', 'ANGHELUTA BILBATUA', '1945-05-22', 'F', '82798528T', 'Calle 89', 604892755, 85),
(86, 'NIEVES ROCIO', 'VIVARACHO CASO', '1989-03-09', 'F', '49250598P', 'Calle 53', 715337029, 86),
(87, 'LEONA', 'PERZA HADRI', '1932-09-18', 'F', '49873613E', 'Calle 9', 604405428, 87),
(88, 'YUNAISY', 'RUIZ DE LA TORRE MIELGO', '1976-03-18', 'F', '89961095E', 'Calle 45', 625299084, 88),
(89, 'GALYA', 'BAKHAT PETROVA', '1936-10-21', 'F', '33548273J', 'Calle 50', 611443576, 89),
(90, 'ALDEMAR', 'GAVILANEZ MACON', '2000-12-22', 'M', '63998886Y', 'Calle 21', 724244773, 90),
(91, 'ROSELI', 'POLLEDO FERRE', '1943-03-22', 'F', '76584249E', 'Calle 75', 614315753, 91),
(92, 'FARHAN', 'RILOVA PELAZ', '1999-01-16', 'M', '89944395C', 'Calle 72', 771134996, 92),
(93, 'REYMUNDO', 'PICART REGAÃ‘O', '1934-07-07', 'M', '96489228L', 'Calle 82', 782397734, 93),
(94, 'LINDA MARIE', 'GIMENO CHOCARRO', '2002-02-09', 'F', '93800854T', 'Calle 52', 629153369, 94),
(95, 'ISABEL PATRICIA', 'MUDASSAR CALETRIO', '1994-12-07', 'F', '40206302W', 'Calle 61', 654728607, 95),
(96, 'ISHTAR', 'GALLARDO PACIO', '2000-06-04', 'F', '69950851P', 'Calle 9', 618070871, 96),
(97, 'SUKHDEV', 'BUENACASA OTEGI', '1971-04-28', 'M', '71550480X', 'Calle 59', 633553566, 97),
(98, 'VIDALINA', 'CIORDIA JUANPERE', '1967-06-13', 'F', '25095176Z', 'Calle 99', 665179449, 98),
(99, 'JESUS HIPOLITO', 'PALANCA FORMIGO', '2003-08-25', 'M', '47550728E', 'Calle 1', 792575577, 99),
(100, 'JAVIER JULIO', 'FUENTEFRIA CORAL', '2006-09-30', 'M', '35604525L', 'Calle 67', 610757819, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesionales`
--

CREATE TABLE `profesionales` (
  `idProfesional` int(11) NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL,
  `apellidos` varchar(250) CHARACTER SET utf8 NOT NULL,
  `nacimiento` date NOT NULL,
  `dni` varchar(9) COLLATE utf8mb4_spanish_ci NOT NULL,
  `direccion` varchar(250) COLLATE utf8mb4_spanish_ci NOT NULL,
  `telefono` int(11) NOT NULL,
  `comision` decimal(18,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `profesionales`
--

INSERT INTO `profesionales` (`idProfesional`, `nombre`, `apellidos`, `nacimiento`, `dni`, `direccion`, `telefono`, `comision`) VALUES
(1, 'KEVIN ROBERTO', 'ARGOS BARRUECO', '1975-03-29', '55291706M', 'Calle 80', 776464240, '15.00'),
(2, 'GALILEO', 'CEDRES GARCIA DE QUIROS', '1931-06-29', '28099288G', 'Calle 42', 715328311, '20.00'),
(3, 'MANUELA LUCIA', 'ECHEVESTE ROSCA', '1980-10-11', '80677496M', 'Calle 94', 636757983, '15.00'),
(4, 'RITA VANESSA', 'VIEIRO WOODS', '1926-08-06', '56683235B', 'Calle 57', 723961475, '20.00'),
(5, 'EDGAR RAFAEL', 'HILAL AREAN', '1970-11-16', '27972895L', 'Calle 56', 775143541, '15.00'),
(6, 'JUAN TOMAS', 'AVIA GRANADO', '1992-04-01', '70342289D', 'Calle 75', 642552877, '20.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `serviciosrealizados`
--

CREATE TABLE `serviciosrealizados` (
  `idServicio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `idProfesional` int(11) NOT NULL,
  `idTratamiento` int(11) NOT NULL,
  `precio` decimal(18,2) NOT NULL,
  `cobrado` decimal(18,2) DEFAULT NULL,
  `idLiquidacion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `serviciosrealizados`
--

INSERT INTO `serviciosrealizados` (`idServicio`, `fecha`, `idPaciente`, `idProfesional`, `idTratamiento`, `precio`, `cobrado`, `idLiquidacion`) VALUES
(1, '2022-05-18', 1, 1, 1, '250.00', '250.00', NULL),
(2, '2022-05-18', 2, 2, 2, '0.00', NULL, NULL),
(3, '2022-05-18', 3, 3, 3, '400.00', '400.00', NULL),
(4, '2022-05-18', 4, 4, 4, '450.00', '450.00', NULL),
(5, '2022-05-18', 5, 5, 5, '250.00', '250.00', NULL),
(6, '2022-05-18', 6, 6, 6, '350.00', '350.00', NULL),
(7, '2022-05-18', 7, 1, 7, '250.00', '250.00', NULL),
(8, '2022-05-18', 8, 2, 8, '80.00', '80.00', NULL),
(9, '2022-05-18', 9, 3, 9, '60.00', '60.00', NULL),
(10, '2022-05-18', 10, 4, 10, '40.00', '40.00', NULL),
(11, '2022-05-19', 11, 5, 11, '20.00', '20.00', NULL),
(12, '2022-05-19', 12, 6, 12, '50.00', '50.00', NULL),
(13, '2022-05-19', 13, 1, 13, '100.00', '100.00', NULL),
(14, '2022-05-19', 14, 2, 14, '120.00', '120.00', NULL),
(15, '2022-05-19', 15, 3, 15, '150.00', '150.00', NULL),
(16, '2022-05-19', 16, 4, 16, '60.00', '60.00', NULL),
(17, '2022-05-19', 17, 5, 17, '180.00', '180.00', NULL),
(18, '2022-05-19', 18, 6, 18, '75.00', '75.00', NULL),
(19, '2022-05-19', 19, 1, 19, '40.00', '40.00', NULL),
(20, '2022-05-19', 20, 2, 20, '20.00', '20.00', NULL),
(21, '2022-05-20', 21, 3, 21, '50.00', '50.00', NULL),
(22, '2022-05-20', 22, 4, 22, '350.00', '350.00', NULL),
(23, '2022-05-20', 23, 5, 23, '50.00', '50.00', NULL),
(24, '2022-05-20', 24, 6, 24, '45.00', '45.00', NULL),
(25, '2022-05-20', 25, 1, 25, '400.00', '400.00', NULL),
(26, '2022-05-20', 26, 2, 26, '0.00', NULL, NULL),
(27, '2022-05-20', 27, 3, 27, '80.00', '80.00', NULL),
(28, '2022-05-20', 28, 4, 28, '0.00', NULL, NULL),
(29, '2022-05-20', 29, 5, 29, '30.00', '30.00', NULL),
(30, '2022-05-20', 30, 6, 30, '600.00', '600.00', NULL),
(31, '2022-05-21', 31, 1, 31, '250.00', '250.00', NULL),
(32, '2022-05-21', 32, 2, 32, '150.00', '150.00', NULL),
(33, '2022-05-21', 33, 3, 33, '300.00', '300.00', NULL),
(34, '2022-05-21', 34, 4, 34, '50.00', '50.00', NULL),
(35, '2022-05-21', 35, 5, 35, '65.00', '65.00', NULL),
(36, '2022-05-21', 36, 6, 36, '0.00', NULL, NULL),
(37, '2022-05-21', 37, 1, 37, '3000.00', '3000.00', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamientos`
--

CREATE TABLE `tratamientos` (
  `idTratamiento` int(11) NOT NULL,
  `codTto` varchar(10) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(250) CHARACTER SET utf8 NOT NULL,
  `precio` decimal(18,2) NOT NULL,
  `idFamilia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tratamientos`
--

INSERT INTO `tratamientos` (`idTratamiento`, `codTto`, `nombre`, `precio`, `idFamilia`) VALUES
(1, 'INJ', 'INJERTO DE TEJIDO CONECTIVO', '250.00', 1),
(2, 'QP', 'QUITAR PUNTOS', '0.00', 1),
(3, 'CIMP', 'CORONA SOBRE IMPLANTE METAL PROCELANA', '400.00', 2),
(4, 'CIZ', 'CORONA SOBREIMPLANTE DE ZIRCONIO', '450.00', 2),
(5, 'CMP', 'CORONA METAL PORCELANA', '250.00', 2),
(6, 'COC', 'CORONA CIRCONIA', '350.00', 2),
(7, 'COP', 'CORONA PROVISIONAL', '250.00', 2),
(8, 'GRCP', 'GRAN RECONSTRUCCION CON PERNO', '80.00', 3),
(9, 'GREC', 'GRAN RECONSTRUCCION', '60.00', 3),
(10, 'OBT', 'OBTURACION', '40.00', 3),
(11, 'PFV', 'PERNO DE FIBRA DE VIDRIO', '20.00', 3),
(12, 'REC', 'RECONSTRUCCIÃ“N', '50.00', 3),
(13, 'END1', 'ENDODONCIA 1 CONDUCTO', '100.00', 4),
(14, 'END2', 'ENDODONCIA 2 CONDUCTOS', '120.00', 4),
(15, 'END3', 'ENDODONCIA 3 CONDUCTOS', '150.00', 4),
(16, 'EXCOR', 'EXTRACCION CORDAL SIMPLE', '60.00', 5),
(17, 'EXIN', 'EXTRACCION DIENTE INCLUIDO', '180.00', 5),
(18, 'EXOD', 'EXTRACCION CON ODONTOSECCION', '75.00', 5),
(19, 'EXT', 'EXTRACCION SIMPLE', '40.00', 5),
(20, 'ADA', 'AÃ‘ADIR DIENTES ADICIONALES (UNIDAD)', '20.00', 6),
(21, 'ADI', 'AÃ‘ADIR 1 DIENTE', '50.00', 6),
(22, 'BL', 'BLANQUEAMIENTO COMPLETO', '350.00', 6),
(23, 'CPU', 'CORTAR PUENTE', '50.00', 6),
(24, 'HG', 'HIGIENE', '45.00', 6),
(25, 'PA6+', 'PARCIAL DE 6 O MAS PIEZAS', '400.00', 6),
(26, 'PV', 'PRIMERA VISITA', '0.00', 6),
(27, 'REB', 'REBASE', '80.00', 6),
(28, 'REV', 'REVISION', '0.00', 6),
(29, 'VIS', 'VISITA SIMPLE', '30.00', 6),
(30, 'IMP', 'IMPLANTE', '600.00', 7),
(31, 'ORCB', 'CEMENTADO BRACKET POR ARCADA', '250.00', 8),
(32, 'ORE', 'ESTUDIO DE ORTODONCIA', '150.00', 8),
(33, 'ORRB', 'RETIRADA BRACKET', '300.00', 8),
(34, 'RVAP', 'REVISION APARATO ORTODONCIA', '50.00', 8),
(35, 'RVBK', 'REVISION DE ORTODONCIA BRACKET', '65.00', 8),
(36, 'RVO', 'REVISION SEGUIMIENTO ORTODONCIA', '0.00', 8),
(37, 'SD4', 'SOBREDENTADURA 4 IMPLANTES', '3000.00', 9);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cobros`
--
ALTER TABLE `cobros`
  ADD PRIMARY KEY (`idCobro`),
  ADD KEY `fkPacienteCobro` (`idPaciente`),
  ADD KEY `fkFCobroCobro` (`idFCobro`),
  ADD KEY `fkServicioCobro` (`idServicio`);

--
-- Indices de la tabla `familias`
--
ALTER TABLE `familias`
  ADD PRIMARY KEY (`idFamilia`);

--
-- Indices de la tabla `fcobro`
--
ALTER TABLE `fcobro`
  ADD PRIMARY KEY (`idFCobro`),
  ADD KEY `fkGrupoCajaFCobro` (`idGrupoCaja`);

--
-- Indices de la tabla `grupocaja`
--
ALTER TABLE `grupocaja`
  ADD PRIMARY KEY (`idGrupoCaja`);

--
-- Indices de la tabla `liquidaciones`
--
ALTER TABLE `liquidaciones`
  ADD PRIMARY KEY (`idLiquidacion`),
  ADD KEY `fkProfesionalLiquidacion` (`idProfesional`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Indices de la tabla `profesionales`
--
ALTER TABLE `profesionales`
  ADD PRIMARY KEY (`idProfesional`);

--
-- Indices de la tabla `serviciosrealizados`
--
ALTER TABLE `serviciosrealizados`
  ADD PRIMARY KEY (`idServicio`),
  ADD KEY `fkPacienteServicio` (`idPaciente`),
  ADD KEY `fkProfesionalServicio` (`idProfesional`),
  ADD KEY `fkTratamientoServicio` (`idTratamiento`),
  ADD KEY `idLiquidacion` (`idLiquidacion`);

--
-- Indices de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD PRIMARY KEY (`idTratamiento`),
  ADD KEY `fkFamiliaTratamiento` (`idFamilia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cobros`
--
ALTER TABLE `cobros`
  MODIFY `idCobro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `familias`
--
ALTER TABLE `familias`
  MODIFY `idFamilia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `fcobro`
--
ALTER TABLE `fcobro`
  MODIFY `idFCobro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `grupocaja`
--
ALTER TABLE `grupocaja`
  MODIFY `idGrupoCaja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `liquidaciones`
--
ALTER TABLE `liquidaciones`
  MODIFY `idLiquidacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `profesionales`
--
ALTER TABLE `profesionales`
  MODIFY `idProfesional` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `serviciosrealizados`
--
ALTER TABLE `serviciosrealizados`
  MODIFY `idServicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  MODIFY `idTratamiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cobros`
--
ALTER TABLE `cobros`
  ADD CONSTRAINT `fkFCobroCobro` FOREIGN KEY (`idFCobro`) REFERENCES `fcobro` (`idFCobro`),
  ADD CONSTRAINT `fkPacienteCobro` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`),
  ADD CONSTRAINT `fkServicioCobro` FOREIGN KEY (`idServicio`) REFERENCES `serviciosrealizados` (`idServicio`);

--
-- Filtros para la tabla `fcobro`
--
ALTER TABLE `fcobro`
  ADD CONSTRAINT `fkGrupoCajaFCobro` FOREIGN KEY (`idGrupoCaja`) REFERENCES `grupocaja` (`idGrupoCaja`);

--
-- Filtros para la tabla `liquidaciones`
--
ALTER TABLE `liquidaciones`
  ADD CONSTRAINT `fkProfesionalLiquidacion` FOREIGN KEY (`idProfesional`) REFERENCES `profesionales` (`idProfesional`);

--
-- Filtros para la tabla `serviciosrealizados`
--
ALTER TABLE `serviciosrealizados`
  ADD CONSTRAINT `fkPacienteServicio` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`),
  ADD CONSTRAINT `fkProfesionalServicio` FOREIGN KEY (`idProfesional`) REFERENCES `profesionales` (`idProfesional`),
  ADD CONSTRAINT `fkTratamientoServicio` FOREIGN KEY (`idTratamiento`) REFERENCES `tratamientos` (`idTratamiento`),
  ADD CONSTRAINT `serviciosrealizados_ibfk_1` FOREIGN KEY (`idLiquidacion`) REFERENCES `liquidaciones` (`idLiquidacion`);

--
-- Filtros para la tabla `tratamientos`
--
ALTER TABLE `tratamientos`
  ADD CONSTRAINT `fkFamiliaTratamiento` FOREIGN KEY (`idFamilia`) REFERENCES `familias` (`idFamilia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
