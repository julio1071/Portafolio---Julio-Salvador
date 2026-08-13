CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;
 
CREATE TABLE IF NOT EXISTS usuarios (
    id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);
 
CREATE TABLE IF NOT EXISTS libros (
    isbn VARCHAR(20) PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);
 
CREATE TABLE IF NOT EXISTS prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL,
    usuario_id VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (isbn) REFERENCES libros(isbn),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
 
INSERT INTO usuarios (id, nombre) VALUES
    ('U-01', 'Carlos Perez'),
    ('U-02', 'Maria Gomez');
 
INSERT INTO libros (isbn, titulo, autor, disponible) VALUES
    ('978-1', 'El Principito', 'Antoine de Saint-Exupery', TRUE),
    ('978-2', 'Cien Anos de Soledad', 'Gabriel Garcia Marquez', TRUE),
    ('978-3', 'Don Quijote de la Mancha', 'Miguel de Cervantes', TRUE);









