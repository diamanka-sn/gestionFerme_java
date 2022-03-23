-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 23 mars 2022 à 11:07
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ferme`
--

-- --------------------------------------------------------

--
-- Structure de la table `achatbovin`
--

DROP TABLE IF EXISTS `achatbovin`;
CREATE TABLE IF NOT EXISTS `achatbovin` (
  `idBovin` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `montantBovin` int(11) DEFAULT NULL,
  `dateAchatBovin` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idBovin`,`idUtilisateur`),
  UNIQUE KEY `idBovin` (`idBovin`),
  KEY `FK_Admministrateur` (`idUtilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `achatbovin`
--

INSERT INTO `achatbovin` (`idBovin`, `idUtilisateur`, `montantBovin`, `dateAchatBovin`) VALUES
(991340, 1, 250000, '10/03/2022'),
(705240, 1, 255000, '21/03/2022'),
(4903, 1, 255000, '21/03/2022'),
(143388, 1, 255000, '21/03/2022'),
(748153, 1, 255000, '21/03/2022'),
(52021, 1, 255000, '21/03/2022'),
(529041, 1, 255000, '21/03/2022'),
(395355, 1, 255000, '21/03/2022'),
(117144, 1, 255000, '21/03/2022'),
(509618, 1, 250000, '21/03/2022'),
(362950, 1, 800000, '2022-03-21'),
(903728, 1, 800000, '2022-03-21'),
(785875, 1, 900000, '2022-03-21'),
(16665, 1, 900000, '2022-03-21'),
(698439, 1, 900000, '2022-03-21'),
(505426, 1, 900000, '2022-03-21'),
(139609, 1, 250000, '2022-03-21'),
(356299, 1, 250000, '2022-03-21'),
(865153, 1, 500000, '2022-03-21');

-- --------------------------------------------------------

--
-- Structure de la table `alimentationdujour`
--

DROP TABLE IF EXISTS `alimentationdujour`;
CREATE TABLE IF NOT EXISTS `alimentationdujour` (
  `idAlimentation` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int(11) NOT NULL,
  `nomAliment` varchar(50) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idAlimentation`),
  KEY `FK_association22` (`idUtilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=12128 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `alimentationdujour`
--

INSERT INTO `alimentationdujour` (`idAlimentation`, `idUtilisateur`, `nomAliment`, `quantite`, `date`) VALUES
(8, 1, 'ripasse', 0, '2021-01-09'),
(1212, 1, 'paille', 100, '2021-01-09'),
(12122, 1, 'pain', 0, '2021-01-09'),
(12125, 424372, 'ripasse', 250, '2022-03-20'),
(12126, 424372, 'pain', 100, '2022-03-20'),
(12127, 424372, 'paille', 2455, '2022-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `bovin`
--

DROP TABLE IF EXISTS `bovin`;
CREATE TABLE IF NOT EXISTS `bovin` (
  `idBovin` int(11) NOT NULL,
  `idRace` int(11) NOT NULL,
  `codeBovin` varchar(254) DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `photo` varchar(254) DEFAULT NULL,
  `dateNaissance` varchar(50) DEFAULT NULL,
  `etatSante` varchar(254) DEFAULT NULL,
  `geniteur` varchar(254) DEFAULT NULL,
  `genitrice` varchar(254) DEFAULT NULL,
  `etat` varchar(254) DEFAULT NULL,
  `situation` varchar(254) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idBovin`),
  UNIQUE KEY `nom` (`nom`),
  UNIQUE KEY `codeBovin` (`codeBovin`),
  KEY `FK_association4` (`idRace`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `bovin`
--

INSERT INTO `bovin` (`idBovin`, `idRace`, `codeBovin`, `nom`, `photo`, `dateNaissance`, `etatSante`, `geniteur`, `genitrice`, `etat`, `situation`, `prix`, `description`) VALUES
(1, 1, 'VAC-40', 'Jegg bu jarr', '666029976.png', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'En vente', 300000, 'Vache'),
(2, 2, 'VAC-01', 'jegg bu nioul', '120657816.png', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'vendu', 1500000, 'Vache'),
(3, 1, 'VAC-02', 'Jeyla', '1861717227.jpg', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'commander', 4500000, 'Vache'),
(4, 1, 'VAC-03', 'Bell  Da', '536549651.jpg', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1520000, 'Vache'),
(5, 3, 'VAC-04', 'Joya', '1508897482.png', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'En vente', 900000, 'Vache'),
(6, 1, 'VAC-05', 'Dior', '1602459488.png', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1350000, 'Vache'),
(7, 1, 'VAC-06', 'Saye', '1251593966.png', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1200000, 'Vache'),
(8, 2, 'TAU-00', 'kaladi', '1148025032.webp', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1250000, 'Taureau'),
(9, 1, 'TAU-01', 'Oudjiurdi', '1187609805.webp', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1350000, 'Taureau'),
(10, 1, 'TAU-02', 'Tolliri', '1798303714.webp', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1000000, 'Taureau'),
(11, 1, 'TAU-03', 'Bayti', '1620434939.webp', '2022-01-10', 'Gueri', '-', '-', 'Vivant', 'Pas en vente', 1320000, 'Taureau'),
(12, 2, 'TAU-04', 'Peg', '305193059.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1250000, 'Taureau'),
(13, 1, 'TAU-05', 'Baylor', '1039469678.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 900000, 'Taureau'),
(14, 1, 'VEA-00', 'Sibor', '1159798212.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1500000, 'Veau'),
(15, 1, 'VEA-01', 'Seull bu ndaw', '1074949134.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1400000, 'Veau'),
(16, 1, 'VEA-02', 'Diambar', '753184738.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1250000, 'Veau'),
(17, 1, 'VEA-03', 'Doomu buur', '1167377842.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1000000, 'Veau'),
(18, 1, 'VEL-00', 'Linguere', '1015604503.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1000000, 'Velle'),
(19, 2, 'VEL-01', 'Reysa', '517302985.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 850000, 'Velle'),
(20, 1, 'VEL-02', 'thiockere', '509925472.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 950000, 'Velle'),
(21, 1, 'VEL-03', 'Dialika', '84856218.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 600000, 'Velle'),
(22, 1, 'VEL-04', 'Bella', '652899888.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1450000, 'Velle'),
(23, 1, 'GEN-00', 'Ndiaya', '882887349.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 999000, 'Genisse'),
(24, 1, 'GEN-01', 'Diboor', '1864174349.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 1250000, 'Genisse'),
(25, 1, 'GEN-02', 'Astelle', '2059730807.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 500000, 'Genisse'),
(26, 1, 'GEN-03', 'Joogue epice', '2006303573.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 985000, 'Genisse'),
(27, 1, 'VAC-07', 'Ngelem', '26165921.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 300000, 'Vache'),
(28, 3, 'VAC-08', 'Rané', '318471771.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 450000, 'Vache'),
(29, 1, 'VAC-09', 'Mballé', '1252958149.webp', '2022-01-10', 'Guéri', '-', '-', 'Vivant', 'Pas en vente', 250000, 'Vache'),
(30, 1, 'VAC-10', 'Ndama', '1798826485.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 350000, 'Vache'),
(31, 2, 'VAC-11', 'Golowé', '1354767810.webp', '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 800000, 'Vache'),
(726068, 546446, 'VEA-726068', 'Kouti ', NULL, '2022-01-10', 'Sain', 'kaladi', 'Jegg bu jarr', 'Vivant', 'Pas en vente', 250000, 'Veau'),
(705240, 546446, 'VAC-705240', 'Jegg bu khess', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Vache'),
(4903, 546446, 'VAC-4903', 'Jeggou keur', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Vache'),
(143388, 546446, 'TAU-143388', 'Buur', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Taureau'),
(748153, 546446, 'TAU-748153', 'Domu awo', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Taureau'),
(52021, 546446, 'TAU-52021', 'Domu niarel', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Taureau'),
(529041, 546446, 'TAU-529041', 'Domu niattel', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 255000, 'Taureau'),
(395355, 546446, 'VAC-395355', 'Borom keur bou nioul', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'commander', 255000, 'Vache'),
(117144, 546446, 'VAC-117144', 'Borom keur bou khess', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 255000, 'Vache'),
(509618, 50033, 'VAC-509618', 'Jeegu pousso', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 250000, 'Vache'),
(698439, 2, 'VAC-698439', 'Jegg bou sel', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'Pas en vente', 900000, 'Vache'),
(16665, 2, 'VAC-16665', 'Tyma', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 900000, 'Vache'),
(785875, 50033, 'TAU-785875', 'Khassim ', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'commander', 900000, 'Taureau'),
(903728, 50033, 'TAU-903728', 'Azou bebe', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 800000, 'Taureau'),
(63199, 546446, 'VEL-63199', 'Wassangue', NULL, '2022-01-10', 'Sain', 'kaladi', 'Bell  Da', 'Vivant', 'En vente', 2500000, 'Velle'),
(356299, 50033, 'GEN-356299', 'Jank bou khess', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 250000, 'Genisse'),
(139609, 50033, 'VEA-139609', 'Jogue dore', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 250000, 'Veau'),
(505426, 2, 'TAU-505426', 'K D', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 900000, 'Taureau'),
(865153, 343231, 'TAU-865153', 'Borom sikin', NULL, '2021-03-22', 'Sain', '-', '-', 'Vivant', 'commander', 500000, 'Taureau'),
(362950, 50033, 'VAC-362950', 'NL Niang', NULL, '2022-01-10', 'Sain', '-', '-', 'Vivant', 'En vente', 800000, 'Vache');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idCom` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int(11) NOT NULL,
  `dateCom` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idCom`),
  KEY `FK_association23` (`idUtilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idCom`, `idUtilisateur`, `dateCom`) VALUES
(45, 206100, '2022-03-23'),
(44, 206100, '2022-03-23'),
(43, 206100, '2022-03-23'),
(42, 206100, '2022-03-23'),
(41, 241895, '2022-03-22'),
(40, 241895, '2022-03-22'),
(39, 241895, '2022-03-22'),
(38, 241895, '2022-03-22'),
(37, 241895, '2022-03-22'),
(36, 206100, '2022-03-21'),
(34, 206100, '2022-03-21'),
(32, 206100, '2022-03-21'),
(31, 206100, '2022-03-21'),
(30, 206100, '2022-03-21'),
(28, 206100, '2022-03-21');

-- --------------------------------------------------------

--
-- Structure de la table `depenses`
--

DROP TABLE IF EXISTS `depenses`;
CREATE TABLE IF NOT EXISTS `depenses` (
  `idDepense` int(11) NOT NULL AUTO_INCREMENT,
  `idType` int(11) NOT NULL,
  `dateDepense` varchar(50) DEFAULT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  `montant` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDepense`),
  KEY `FK_association21` (`idType`)
) ENGINE=MyISAM AUTO_INCREMENT=132338 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `depenses`
--

INSERT INTO `depenses` (`idDepense`, `idType`, `dateDepense`, `libelle`, `montant`, `quantite`) VALUES
(13232, 5454, '2021/03/09', 'ripasse', 2000000, 250),
(2121, 5454, '2021-03-09', 'paille', 25500, 2555),
(132320, 5454, '2021/03/09', 'ripasse', 2000000, 250),
(12121, 5454, '2021/03/09', 'pain', 2000000, 250),
(132336, 152969, '2022-03-21', 'Paiement facture', 50000, 0),
(132337, 152969, '2022-03-23', 'paiement facture ', 250000, 0),
(132335, 363299, '2022-03-21', 'Paiment veterinaire', 80000, 0),
(132334, 219318, '2022-03-21', 'Achat medicament', 2500000, 0);

-- --------------------------------------------------------

--
-- Structure de la table `detailsfacture`
--

DROP TABLE IF EXISTS `detailsfacture`;
CREATE TABLE IF NOT EXISTS `detailsfacture` (
  `idFacture` int(11) NOT NULL,
  `idCom` int(11) NOT NULL,
  `montant` int(11) DEFAULT NULL,
  `datePaiement` datetime DEFAULT NULL,
  PRIMARY KEY (`idFacture`,`idCom`),
  KEY `FK_detailsFacture` (`idCom`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `diagnostic`
--

DROP TABLE IF EXISTS `diagnostic`;
CREATE TABLE IF NOT EXISTS `diagnostic` (
  `idMaladie` int(11) NOT NULL,
  `idBovin` int(11) NOT NULL,
  `idDiagnostic` int(11) NOT NULL AUTO_INCREMENT,
  `dateMaladie` varchar(50) DEFAULT NULL,
  `dateGuerison` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idDiagnostic`) USING BTREE,
  KEY `FK_association13` (`idBovin`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `diagnostic`
--

INSERT INTO `diagnostic` (`idMaladie`, `idBovin`, `idDiagnostic`, `dateMaladie`, `dateGuerison`) VALUES
(1, 11, 1, '17/03/2022', '17/03/2022'),
(4, 1, 2, '17/03/2022', '18/03/2022'),
(5, 2, 3, '17/03/2022', 'non'),
(7, 3, 4, '16/03/2022', '18/03/2022'),
(1, 4, 5, '17/03/2022', '17/03/2022'),
(4, 1, 6, '21/03/2022', '2022-03-23');

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `idFacture` int(11) NOT NULL,
  PRIMARY KEY (`idFacture`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fermier`
--

DROP TABLE IF EXISTS `fermier`;
CREATE TABLE IF NOT EXISTS `fermier` (
  `idUtilisateur` int(11) NOT NULL,
  `salaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fermier`
--

INSERT INTO `fermier` (`idUtilisateur`, `salaire`) VALUES
(732653, 250000),
(438552, 250000);

-- --------------------------------------------------------

--
-- Structure de la table `genisse`
--

DROP TABLE IF EXISTS `genisse`;
CREATE TABLE IF NOT EXISTS `genisse` (
  `idBovin` int(11) NOT NULL,
  `etat` varchar(254) DEFAULT NULL,
  `dateIA` datetime DEFAULT NULL,
  PRIMARY KEY (`idBovin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `maladie`
--

DROP TABLE IF EXISTS `maladie`;
CREATE TABLE IF NOT EXISTS `maladie` (
  `idMaladie` int(11) NOT NULL AUTO_INCREMENT,
  `nomMaladie` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idMaladie`),
  UNIQUE KEY `nomMaladie` (`nomMaladie`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `maladie`
--

INSERT INTO `maladie` (`idMaladie`, `nomMaladie`) VALUES
(4, 'Diarrhée'),
(5, 'encéphalopathie'),
(6, 'rhinotrachéite'),
(7, 'campylobactériose'),
(8, 'dermatite-nodulaire'),
(9, 'paragrippe-3'),
(10, 'peste'),
(11, 'gangrène');

-- --------------------------------------------------------

--
-- Structure de la table `pesage`
--

DROP TABLE IF EXISTS `pesage`;
CREATE TABLE IF NOT EXISTS `pesage` (
  `idPesee` int(11) NOT NULL AUTO_INCREMENT,
  `idBovin` int(11) NOT NULL,
  `datePese` varchar(50) NOT NULL,
  `poids` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPesee`),
  UNIQUE KEY `idBovin` (`idBovin`,`datePese`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pesage`
--

INSERT INTO `pesage` (`idPesee`, `idBovin`, `datePese`, `poids`) VALUES
(1, 1, '2022-02-13', 235),
(2, 1, '2022-01-01', 235),
(3, 2, '2022-02-01', 255),
(4, 1, '2022-03-18', 255),
(5, 2, '2022-03-18', 200),
(6, 3, '2022-03-11', 255),
(7, 3, '2022-02-11', 255),
(8, 3, '2022-01-11', 255),
(9, 2, '2022-01-01', 255),
(10, 6, '2022-01-15', 255),
(11, 6, '2022-02-12', 200),
(12, 4, '2022-01-21', 255),
(13, 4, '2022-02-14', 200),
(14, 4, '2022-03-21', 200),
(15, 6, '2022-03-22', 200),
(16, 5, '2022-03-23', 200);

-- --------------------------------------------------------

--
-- Structure de la table `race`
--

DROP TABLE IF EXISTS `race`;
CREATE TABLE IF NOT EXISTS `race` (
  `idRace` int(11) NOT NULL,
  `nomRace` varchar(254) NOT NULL,
  PRIMARY KEY (`idRace`),
  UNIQUE KEY `nomRace` (`nomRace`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `race`
--

INSERT INTO `race` (`idRace`, `nomRace`) VALUES
(1, 'Montbeliard'),
(2, 'Holstein'),
(3, 'Limousine'),
(93838, 'Charoloise'),
(360372, 'Salers'),
(948386, 'Normande'),
(965221, 'Bretone'),
(894996, 'Jersaise'),
(546446, 'Blanc bleu'),
(652287, 'Sarde'),
(801661, 'Devon'),
(343231, 'Kouri'),
(248358, 'Nguni'),
(50033, 'Abigar');

-- --------------------------------------------------------

--
-- Structure de la table `traitedujour`
--

DROP TABLE IF EXISTS `traitedujour`;
CREATE TABLE IF NOT EXISTS `traitedujour` (
  `idtraite` int(11) NOT NULL AUTO_INCREMENT,
  `idUtilisateur` int(11) NOT NULL,
  `idBovin` int(11) NOT NULL,
  `traiteMatin` int(11) DEFAULT NULL,
  `traiteSoir` int(11) DEFAULT NULL,
  `dateTraite` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idtraite`),
  UNIQUE KEY `idBovin` (`idBovin`,`dateTraite`),
  KEY `FK_association16` (`idUtilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=994221 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `traitedujour`
--

INSERT INTO `traitedujour` (`idtraite`, `idUtilisateur`, `idBovin`, `traiteMatin`, `traiteSoir`, `dateTraite`) VALUES
(596843, 424372, 27, 10, 20, '2022-03-21'),
(393956, 424372, 6, 15, 20, '2022-03-21'),
(489267, 424372, 1, 30, 20, '2022-03-21'),
(994220, 424372, 5, 15, 10, '2022-03-18'),
(148173, 424372, 5, 15, 10, '2022-02-03'),
(767603, 424372, 5, 10, 10, '2022-01-03'),
(332989, 424372, 28, 20, 15, '2022-03-21'),
(923818, 424372, 30, 15, 10, '2022-03-21'),
(23737, 424372, 29, 20, 15, '2022-03-21'),
(929436, 424372, 1, 20, 12, '2022-03-22'),
(253872, 424372, 6, 60, 60, '2022-03-22'),
(305406, 424372, 1, 10, 15, '2022-03-23');

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `idType` int(11) NOT NULL,
  `nomType` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idType`),
  UNIQUE KEY `nomType` (`nomType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`idType`, `nomType`) VALUES
(363299, 'Sante'),
(219318, 'Achat medicament'),
(152969, 'Facture eau'),
(5454, 'Achat aliment');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `prenom` varchar(254) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `adresse` varchar(254) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `password` varchar(11) DEFAULT NULL,
  `profile` varchar(254) DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUtilisateur`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `telephone`, `adresse`, `email`, `password`, `profile`, `isAdmin`) VALUES
(1, 'diamanka', 'mouhamed', '777888888', 'Kossam ferme', 'admin@kossam.sn', '123456', 'admin', 1),
(206100, 'Diamnka', 'Cheikh', '778795172', 'Nema 2', 'dia@gmail.com', '123456', 'client', 0),
(241895, 'NIANG', 'Ndeye Lobe', '776819396', 'Thies', 'ndeyelobe@gmail.com', '123456', 'client', 0),
(17730, 'BIAYE', 'Idrissa', '774636651', 'CASTOR/ZIGUINCHOR', 'idrissabiaye@gmail.com', '774636651', 'client', 0),
(424372, 'BA', 'Cheikh T.', '778795172', 'Diabir', 'fermier@kossam.sn', '123456789', 'fermier', 1),
(486880, 'BATHILY', 'amadou', '707001922', 'thies', 'bathilyamadou51@gmail.com', '123456', 'client', 0),
(153568, 'Diouma', 'Odile', '778951502', 'Diabir', 'diouma@diouma.com', '123456', 'client', 0),
(438552, 'Mamadou', 'Sow', '778795172', 'Diabir', 'sow@kossam.sn', '123456', 'fermier', 1),
(732653, 'Sarr', 'Issa', '778795172', 'Bambey', 'sarr@kossam.sn', '123456', 'fermier', 1);

-- --------------------------------------------------------

--
-- Structure de la table `vache`
--

DROP TABLE IF EXISTS `vache`;
CREATE TABLE IF NOT EXISTS `vache` (
  `idBovin` int(11) NOT NULL,
  `periode` varchar(254) DEFAULT NULL,
  `phase` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idBovin`),
  UNIQUE KEY `idBovin` (`idBovin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vache`
--

INSERT INTO `vache` (`idBovin`, `periode`, `phase`) VALUES
(1, 'tarissement', 'non gestant'),
(2, 'lactation', 'non gestant'),
(4, 'tarissement', 'gestant'),
(5, 'tarissement', 'gestant'),
(6, 'lactation', 'non gestant'),
(7, 'tarissement', 'gestant'),
(27, 'lactation', 'gestant'),
(28, 'lactation', 'gestant'),
(30, 'lactation', 'gestant'),
(29, 'lactation', 'gestant'),
(31, 'lactation', 'gestant'),
(391260, 'lactation', 'gestant'),
(705240, 'lactation', 'gestant'),
(4903, 'lactation', 'gestant'),
(395355, 'lactation', 'gestant'),
(117144, 'lactation', 'gestant'),
(509618, 'lactation', 'gestant'),
(362950, 'lactation', 'gestant'),
(16665, 'lactation', 'gestant'),
(698439, 'lactation', 'gestant'),
(3, 'lactation', 'gestant');

-- --------------------------------------------------------

--
-- Structure de la table `velage`
--

DROP TABLE IF EXISTS `velage`;
CREATE TABLE IF NOT EXISTS `velage` (
  `idVelage` int(11) NOT NULL,
  `idBovin` int(11) NOT NULL,
  `dateVelage` datetime DEFAULT NULL,
  `nbreVeau` int(11) DEFAULT NULL,
  PRIMARY KEY (`idVelage`),
  KEY `FK_association1` (`idBovin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ventebovin`
--

DROP TABLE IF EXISTS `ventebovin`;
CREATE TABLE IF NOT EXISTS `ventebovin` (
  `idCom` int(11) NOT NULL,
  `idBovin` int(11) NOT NULL,
  `prixBovin` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCom`,`idBovin`),
  UNIQUE KEY `idBovin` (`idBovin`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ventebovin`
--

INSERT INTO `ventebovin` (`idCom`, `idBovin`, `prixBovin`) VALUES
(41, 865153, 500000),
(44, 3, 4500000),
(37, 395355, 255000),
(42, 785875, 900000),
(28, 2, 1500000);

-- --------------------------------------------------------

--
-- Structure de la table `ventelait`
--

DROP TABLE IF EXISTS `ventelait`;
CREATE TABLE IF NOT EXISTS `ventelait` (
  `idCom` int(11) NOT NULL,
  `capacite` int(11) NOT NULL,
  `etat` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idCom`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ventelait`
--

INSERT INTO `ventelait` (`idCom`, `capacite`, `etat`) VALUES
(2, 20, 1),
(30, 20, 1),
(31, 30, 1),
(32, 50, 1),
(34, 20, 1),
(38, 40, 1),
(45, 200, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
