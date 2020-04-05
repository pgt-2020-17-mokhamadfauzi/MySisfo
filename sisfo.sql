-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Apr 2020 pada 08.33
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `android`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `nim` varchar(50) NOT NULL,
  `kodemk` varchar(50) NOT NULL,
  `namamk` varchar(50) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `waktu` varchar(50) NOT NULL,
  `dosen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_jadwal`
--

INSERT INTO `tb_jadwal` (`nim`, `kodemk`, `namamk`, `hari`, `waktu`, `dosen`) VALUES
('1702038', 'MK001', 'Matematika Terapan', 'Senin', '07.00-09.00', 'Ihsan A.A'),
('1702038', 'MK002', 'Kimia', 'Selasa', '07.00-09.00', 'Devi Handaya'),
('1702038', 'MK002', 'Kimia', 'Selasa', '07.00-09.00', 'Devi Handaya');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_mahasiswa`
--

CREATE TABLE `tb_mahasiswa` (
  `nim` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `tempatlahir` varchar(100) NOT NULL,
  `programstudi` varchar(50) NOT NULL,
  `kelas` varchar(50) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_mahasiswa`
--

INSERT INTO `tb_mahasiswa` (`nim`, `nama`, `tempatlahir`, `programstudi`, `kelas`, `password`) VALUES
('1702038', 'Mokhamad Fauzi', 'Banyumas', 'Teknik Elektronika', '3 Elektronika B', 'a2b19baaf1800dfce7e49314a0d5031e'),
('1702039', 'Moni Della Putri S', 'Solo', 'Teknik Elektronika', '3 Elektronika B', '488f212b5d64a30d59d74fe98c30205e'),
('1702042', 'Muhammad Ilham Mahendra', 'Tangerang', 'Teknik Elektronika', '3 Elektronika B', 'aaa484909e33fde1c5c48eb5f304448b');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_matakuliah`
--

CREATE TABLE `tb_matakuliah` (
  `kodemk` varchar(50) NOT NULL,
  `namamk` varchar(50) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `waktu` varchar(50) NOT NULL,
  `dosen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_matakuliah`
--

INSERT INTO `tb_matakuliah` (`kodemk`, `namamk`, `hari`, `waktu`, `dosen`) VALUES
('MK001', 'Matematika Terapan', 'Senin', '07.00-09.00', 'Ihsan A.A'),
('MK002', 'Kimia', 'Selasa', '07.00-09.00', 'Devi Handaya');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nilai`
--

CREATE TABLE `tb_nilai` (
  `nim` varchar(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `akhir` varchar(100) NOT NULL,
  `huruf` varchar(100) NOT NULL,
  `angka` double NOT NULL,
  `kodemk` varchar(100) NOT NULL,
  `namamk` varchar(100) NOT NULL,
  `sks` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tb_nilai`
--

INSERT INTO `tb_nilai` (`nim`, `nama`, `akhir`, `huruf`, `angka`, `kodemk`, `namamk`, `sks`) VALUES
('1702038', 'Mokhamad Fauzi', '8', 'A', 1, 'MK001', 'Matematika Terapan', '2'),
('1702038', 'Mokhamad Fauzi', '8', 'A', 1, 'MK002', 'Kimia', '2'),
('1702038', 'Mokhamad Fauzi', '6', 'B', 2, 'MK001', 'Matematika Terapan', '2'),
('1702038', 'Mokhamad Fauzi', '8', 'A', 3, 'MK005', 'Android', '2'),
('1702038', 'Mokhamad Fauzi', '8', 'A', 4, 'MK006', 'PLC', '2'),
('1702038', 'Mokhamad Fauzi', '8', 'A', 5, 'MK007', 'IOT', '2'),
('1702038', 'Mokhamad Fauzi', '8', 'A', 6, 'MK000', 'PKL', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 1, 'MK001', 'Matematika Terapan', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 2, 'MK001', 'Matematika Terapan', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 3, 'MK003', 'Elektronika Digital', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 4, 'MK008', 'Mikrokontroller', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 5, 'MK007', 'IOT', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 6, 'MK000', 'PKL', '2'),
('1702039', 'Moni Della Putri S', '8', 'A', 5, 'MK009', 'Statistika', '2'),
('1702042', 'Muhammad Ilham Mahendra', '8', 'A', 1, 'MK001', 'Matematika Terapan', '2'),
('1702042', 'Muhammad Ilham Mahendra', '8', 'A', 2, 'MK001', 'Matematika Terapan', '2'),
('1702042', 'Muhammad Ilham Mahendra', '8', 'A', 3, 'MK003', 'Android', '2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
