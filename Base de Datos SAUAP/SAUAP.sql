CREATE DATABASE SAUAP;

USE SAUAP;

CREATE TABLE Usuarios (
	ID_Usuario INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Psswd VARCHAR(20) NOT NULL,
    rol ENUM('administrador', 'coordinador') NOT NULL 
);

CREATE TABLE Profesor (
    ID_Profesor INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    Ap_Primero VARCHAR(50) NOT NULL,
    Ap_Segundo VARCHAR(50),
    RFC VARCHAR(13) NOT NULL UNIQUE,
    CONSTRAINT chk_rfc_length CHECK (CHAR_LENGTH(RFC) = 13)
);

CREATE TABLE Unidad_Aprendizaje (
    ID_UA INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(50) NOT NULL,
    hrs_clase INT NOT NULL DEFAULT 0,
    hrs_taller INT NOT NULL DEFAULT 0,
    hrs_lab INT NOT NULL DEFAULT 0,
    CONSTRAINT chk_hrs_non_negative CHECK (hrs_clase >= 0 AND hrs_taller >= 0 AND hrs_lab >= 0),
	CONSTRAINT chk_hrs_limit CHECK (hrs_clase <= 4 AND hrs_taller <= 4 AND hrs_lab <= 4)
);

CREATE TABLE Asignacion (
    ID_Asignacion INT PRIMARY KEY AUTO_INCREMENT,
    ID_Profesor INT NOT NULL,
    ID_UA INT NOT NULL,
    tipo_asignacion ENUM('clase','taller','lab') NOT NULL,

    CONSTRAINT uq_asignacion UNIQUE (ID_Profesor, ID_UA, tipo_asignacion),

    CONSTRAINT fk_asignacion_profesor FOREIGN KEY (ID_Profesor) REFERENCES Profesor(ID_Profesor)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_asignacion_ua FOREIGN KEY (ID_UA) REFERENCES Unidad_Aprendizaje(ID_UA)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Horario (
    ID_Horario INT PRIMARY KEY AUTO_INCREMENT,
    ID_Asignacion INT NOT NULL,
    dia ENUM('Lun','Mar','Mie','Jue','Vie','Sab') NOT NULL,
    hr_in TIME NOT NULL,
    hr_fin TIME NOT NULL,

    CONSTRAINT uq_horario UNIQUE (ID_Asignacion, dia, hr_in, hr_fin),

    CONSTRAINT fk_horario_asignacion FOREIGN KEY (ID_Asignacion) REFERENCES Asignacion(ID_Asignacion)
        ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT chk_horas_validas CHECK (hr_in < hr_fin)
);