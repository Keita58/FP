DROP DATABASE ProtectoraAnimals;

CREATE DATABASE ProtectoraAnimals;

USE ProtectoraAnimals;

CREATE TABLE Vaccines (
	Id		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Name	VARCHAR(60) NOT NULL UNIQUE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Diseases (
	Id		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Name	VARCHAR(100) NOT NULL UNIQUE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Addresses (
	AddressId		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	AddressLine1	VARCHAR(50) NOT NULL,
	AddressLine2	VARCHAR(50) NOT NULL DEFAULT '',
	City			VARCHAR(50) NOT NULL,
	ZipCode			CHAR(5) NOT NULL, -> /*Posem char perquè ho tractem com a un codi i en sevitem que els zeros de l'esquerra desapareguin*/
	Country			VARCHAR(40) NOT NULL,
	Province		VARCHAR(40) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE TimePeriods (
	TimePeriodId	INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`DayOfWeek`		ENUM('Dilluns','Dimarts','Dimecres','Dijous','Divendres','Dissabte','Diumenge'), /*TINYINT UNSIGNED CHECK (DayOfWeek>= 1 AND DayOfWeek<= 7)*/
	StartTime		TIME NOT NULL,
	EndTime			TIME NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Collaborators (
	DNI			CHAR(9) NOT NULL /*No cal posar-ho, però no està malament*/ PRIMARY KEY,
	Firstname	VARCHAR(20) NOT NULL,
	LastName	VARCHAR(30) NOT NULL,
	Email		VARCHAR(50) NOT NULL,
	PhoneNumber	VARCHAR(15) NOT NULL DEFAULT '', /*Hem de posar-ho com char o varchar pel mateix que el ZipCode*/
	AddressId	INT UNSIGNED NOT NULL,
	CONSTRAINT FOREIGN KEY (AddressId) REFERENCES Addresses(AddressId)
		ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE FosterHouses (
	HouseId		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Name		VARCHAR(30) NOT NULL UNIQUE,
	`Type`		ENUM('Acollida', 'Colònia') NOT NULL,
	ContactDNI	CHAR(9) NOT NULL,
	AddressId	INT UNSIGNED NOT NULL,
	CONSTRAINT FOREIGN KEY (ContactDNI) REFERENCES Collaborators(DNI)
		ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (AddressId) REFERENCES Addresses(AddressId)
		ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Animals (
	AnimalId		INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Race			VARCHAR(50) NOT NULL DEFAULT '',
	Name			VARCHAR(20) NOT NULL UNIQUE,
	Sex				ENUM('F','M') NOT NULL,
	`Size`			ENUM('Petit','Mitjà','Gran') NOT NULL DEFAULT '',
	Species			VARCHAR(40) NOT NULL,
	Birthday		DATE DEFAULT NULL,
	Description		TEXT DEFAULT NULL,
	ArrivalDate		DATE NOT NULL DEFAULT DATE(NOW()),
	Sterilized		BOOLEAN NOT NULL DEFAULT 0,
	Chip			BOOLEAN NOT NULL DEFAULT 0,
	AdopterDNI		CHAR(9) DEFAULT NULL,
	AdoptionDate	DATE DEFAULT NULL,
	FosterId		INT UNSIGNED DEFAULT NULL,
	CONSTRAINT FOREIGN KEY (AdopterDNI) REFERENCES Collaborators(DNI)
		ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (FosterId) REFERENCES FosterHouses(HouseId)
		ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE AnimalVaccines (
	AnimalId	INT UNSIGNED NOT NULL,
	VaccineId	INT UNSIGNED NOT NULL,
	`Date`		DATE NOT NULL DEFAULT DATE(NOW()),
	CONSTRAINT PRIMARY KEY (AnimalId, VaccineId),
	CONSTRAINT FOREIGN KEY (AnimalId) REFERENCES Animals(AnimalId)
		ON DELETE CASCADE ON UPDATE CASCADE, /*Aquí sí interessa esborrar l'historial de vacunes si s'elimina l'animal de la base de dades*/
	CONSTRAINT FOREIGN KEY (VaccineId) REFERENCES Vaccines(Id)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Interventions (
	AnimalId	INT UNSIGNED NOT NULL,
	`Date`		DATE NOT NULL DEFAULT DATE((NOW)),
	Description	TEXT NOT NULL DEFAULT '',
	Type		VARCHAR(50) NOT NULL,
	CONSTRAINT PRIMARY KEY (AnimalId, `Date`),
	CONSTRAINT FOREIGN KEY (AnimalId) REFERENCES Animals(AnimalId)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Patrons (
	DNI				CHAR(9) NOT NULL PRIMARY KEY,
	IBAN			CHAR(27) NOT NULL,
	Owner			VARCHAR(20) NOT NULL,
	Bank			VARCHAR(20) NOT NULL,
	AccountNumber	CHAR(20) NOT NULL,
	CONSTRAINT FOREIGN KEY (DNI) REFERENCES Collaborators(DNI)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Patronize (
	DNI			CHAR(9) NOT NULL,
	AnimalId	INT UNSIGNED NOT NULL,
	StartDate	DATE NOT NULL DEFAULT DATE(NOW()),
	Quantity	DECIMAL(10,2) UNSIGNED NOT NULL,
	Periodicity	VARCHAR(10) NOT NULL,
	EndDate		DATE DEFAULT NULL,
	CONSTRAINT PRIMARY KEY (DNI, AnimalId, StartDate),
	CONSTRAINT FOREIGN KEY (AnimalId) REFERENCES Animals(AnimalId)
		ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (DNI) REFERENCES Patrons(DNI)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Donations (
	DNI			CHAR(9) NOT NULL,
	`DateTime`	DATETIME NOT NULL,
	Quantity	DECIMAL(10,2) UNSIGNED NOT NULL,
	Recurring	BOOLEAN NOT NULL DEFAULT 0,
	CONSTRAINT PRIMARY KEY (DNI, `DateTime`),
	CONSTRAINT FOREIGN KEY (DNI) REFERENCES Collaborators(DNI)
		ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE Disponibility (
	TimePeriodId	INT UNSIGNED NOT NULL,
	CollaboratorDNI CHAR(9) NOT NULL,
	CONSTRAINT PRIMARY KEY (TimePeriodId, CollaboratorDNI),
	CONSTRAINT FOREIGN KEY (TimePeriodId) REFERENCES TimePeriods(TimePeriodId)
		ON DELETE NO ACTION ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (CollaboratorDNI) REFERENCES Collaborators(DNI)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE AnimalDisease (
	AnimalId	INT UNSIGNED NOT NULL,
	DiseaseId	INT UNSIGNED NOT NULL,
	CONSTRAINT PRIMARY KEY (AnimalId, DiseaseId),
	CONSTRAINT FOREIGN KEY (AnimalId) REFERENCES Animals(AnimalId)
		ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (DiseaseId) REFERENCES Diseases(Id)
		ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;