-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 31 déc. 2021 à 12:45
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gtouristique`
--

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `full_name` varchar(50) NOT NULL,
  `id_reservation` int(12) NOT NULL,
  `id_chambre` int(12) NOT NULL,
  `id_client` int(12) NOT NULL,
  `type` varchar(12) NOT NULL,
  `nb_jours` int(12) NOT NULL,
  `date_res` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`full_name`, `id_reservation`, `id_chambre`, `id_client`, `type`, `nb_jours`, `date_res`) VALUES
('oumaima JAALOUDI', 3, 2, 1, 'T1', 18, '2021-12-26'),
('amine', 11, 5, 6, 'T3', 19, '2021-12-26'),
('hamid', 12, 16, 12, 'T6', 12, '2021-12-26');

-- --------------------------------------------------------

--
-- Structure de la table `r_payees`
--

CREATE TABLE `r_payees` (
  `id_reservation` int(11) NOT NULL,
  `Full_name` varchar(55) NOT NULL,
  `ID_Client` int(11) NOT NULL,
  `ID_chambre` int(11) NOT NULL,
  `Nb_Jours` int(11) NOT NULL,
  `Type` varchar(55) NOT NULL,
  `Prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `r_payees`
--

INSERT INTO `r_payees` (`id_reservation`, `Full_name`, `ID_Client`, `ID_chambre`, `Nb_Jours`, `Type`, `Prix`) VALUES
(3, 'oumaima JAALOUDI', 1, 16, 18, 'T1', 7200),
(8, 'marwa', 8, 16, 20, 'T1', 8000),
(9, 'KABAB', 15, 12, 15, 'T1', 6000),
(10, 'KABAB Yassine', 13, 15, 14, 'T1', 5600),
(11, 'amine', 6, 5, 19, 'T3', 1900),
(12, 'hamid', 12, 16, 12, 'T6', 0);

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE `type` (
  `Type` varchar(55) NOT NULL,
  `Prix_Type` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`Type`, `Prix_Type`) VALUES
('T1', 400),
('T2', 300),
('T3', 100);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id_user` int(11) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `profile` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id_user`, `username`, `password`, `profile`) VALUES
(1, 'yassine', 'yassine123', 'Gerant'),
(2, 'oumaima', 'oumaima123', 'receptionniste');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`);

--
-- Index pour la table `r_payees`
--
ALTER TABLE `r_payees`
  ADD PRIMARY KEY (`id_reservation`);

--
-- Index pour la table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`Type`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
