
DROP DATABASE IF EXISTS tramites_tecnm;

CREATE DATABASE tramites_tecnm;

USE tramites_tecnm;

CREATE TABLE carreras (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    carrera VARCHAR(255) NOT NULL
);
CREATE TABLE alumnos (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    carrera_id INT UNSIGNED NOT NULL,
    nombres VARCHAR(255) NOT NULL,
    numero_control VARCHAR(255) NOT NULL UNIQUE,
    correo VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    FOREIGN KEY (carrera_id) REFERENCES carreras(id)
);

CREATE TABLE tipos_archivo (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre_archivo VARCHAR(255)  NOT NULL
);


CREATE TABLE archivos (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    alumno_id INT UNSIGNED NOT NULL,
    tipos_archivo_id INT UNSIGNED NOT NULL,
    ruta_archivo VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    aprovado BOOLEAN NOT NULL DEFAULT false,

    FOREIGN KEY (alumno_id) REFERENCES alumnos(id),
    FOREIGN KEY (tipos_archivo_id) REFERENCES tipos_archivo(id)
);

INSERT INTO carreras (carrera) VALUES ("Ing. Sistemas Computacionales"), ("Ing. Mecatronica"), ("Contador Publico"), ("Ing Industrial"), ("Ing. Electronica");

INSERT INTO alumnos (carrera_id, nombres, numero_control, correo, password) VALUES 
    (
        1,  
        "Jonathan Osvaldo",
        "21680045", 
        "21680045@cuautla.tecnm.mx", 
        "lolxx4321" 
    ),
    (
        1,
        "admin",
        "admin",
        "admin@correo.com", 
        "admin" 
    ),
    (
        2,
        "Mayra Abigail", 
        "21680175",
        "21680175@cuautla.tecnm.mx", 
        "aby4321" 
    ),
    (
        3,
        "Said Alberto", 
        "20680183", 
        "20680183@cuautla.tecnm.mx", 
        "zaidzaid123"
    );

INSERT INTO tipos_archivo (nombre_archivo) VALUES 
    (
        "solicitud"
    ),
    (
        "carta compromiso"
    ),
    (
        "plan de trabajo"
    ),
    (
        "carta de asignacion"
    ),
    (
        "carta aceptacion"
    ),
    (
        "formato de evaluacion"
     ),
    (
        "carta de terminacion"
    ),
    (
        "reporte final de actividad"
    );
