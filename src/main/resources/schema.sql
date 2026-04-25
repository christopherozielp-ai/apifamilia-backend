
CREATE DATABASE IF NOT EXISTS apifamilia
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE apifamilia;

CREATE TABLE IF NOT EXISTS miembros (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(100)    NOT NULL,
    apellido            VARCHAR(100)    NOT NULL,
    email               VARCHAR(150)    NOT NULL UNIQUE,
    telefono            VARCHAR(20),
    relacion            VARCHAR(50)     NOT NULL,
    fecha_nacimiento    DATE,
    direccion           VARCHAR(255),
    ciudad              VARCHAR(100),
    pais                VARCHAR(100),
    fecha_creacion      DATETIME        DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Datos de ejemplo
INSERT INTO miembros (nombre, apellido, email, telefono, relacion, fecha_nacimiento, direccion, ciudad, pais)
VALUES
    ('Juan', 'García', 'juan.garcia@email.com', '+52 81 1234 5678', 'Padre', '1970-05-15', 'Calle Principal 123', 'Monterrey', 'México'),
    ('María', 'García', 'maria.garcia@email.com', '+52 81 9876 5432', 'Madre', '1973-08-22', 'Calle Principal 123', 'Monterrey', 'México'),
    ('Carlos', 'García', 'carlos.garcia@email.com', '+52 81 5555 1234', 'Hijo', '1998-03-10', 'Av. Independencia 456', 'Monterrey', 'México');
