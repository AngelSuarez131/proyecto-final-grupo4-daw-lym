-- Cualquier cambio como nuevas tablas, inserts  o alter tables van debajo 

CREATE DATABASE IF NOT EXISTS minimarket;

USE minimarket;

-- usuarios
CREATE TABLE usuario (
                         id INT auto_increment NOT NULL,
                         username varchar(100) NOT NULL,
                         password varchar(100) NULL,
                         email varchar(100) NOT NULL,
                         CONSTRAINT usuario_pk PRIMARY KEY (id),
                         CONSTRAINT usuario_unique UNIQUE KEY (email),
                         CONSTRAINT usuario_unique_1 UNIQUE KEY (username)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- Distritos
CREATE TABLE distrito (
                          idDis INT auto_increment NOT NULL,
                          nombre varchar(100) NOT NULL,
                          CONSTRAINT distritos_pk PRIMARY KEY (idDis)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- clientes

CREATE TABLE cliente (
                         id int NOT NULL AUTO_INCREMENT,
                         nombre varchar(100) NOT NULL,
                         email varchar(100) NOT NULL,
                         username varchar(100) NOT NULL,
                         password varchar(100) NOT NULL,
                         direccion varchar(100) DEFAULT NULL,
                         idDis int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `cliente_unique` (`email`),
                         UNIQUE KEY `cliente_unique_1` (`username`,`email`),
                         KEY `clientes_distritos_fk` (`idDis`),
                         CONSTRAINT `cliente_distrito_fk` FOREIGN KEY (`idDis`) REFERENCES `distrito` (`idDis`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Produits
CREATE TABLE producto (
                          id INT auto_increment NOT NULL,
                          nombre varchar(100) NOT NULL,
                          precioU DECIMAL(10,2) NULL,
                          uniMedida varchar(100) NOT NULL,
                          stock INT NULL,
                          marca varchar(100) NOT NULL,
                          CONSTRAINT producto_pk PRIMARY KEY (id)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO distrito
(idDis, nombre)
VALUES(1, 'San Miguel'),
      (2, 'San Borja'),
      (3, 'Comas'),
      (4, 'Miraflores'),
      (5, 'Magdalena'),
      (6, 'Jesus Maria'),
      (7, 'La Victoria');

INSERT INTO usuario
(id, username, password, email)
VALUES(1, 'admin1', 'contrasegura95473', 'eladmin@gmail.com'),
      (2, 'mcabrejos', '938fjsidfa', 'monicac@gmail.com'),
      (3, 'sysadmin', '0d8g0as9d0f', 'sergioe@gmail.com');

INSERT INTO producto
(id, nombre, precioU, uniMedida, stock, marca)
values
    (1, 'Yogurt ', 3.50, 'unidad', 50, 'Gloria'),
    (2, 'Arroz ', 2.80, 'kg', 100, 'Costeño'),
    (3, 'Aceite ', 9.90, 'litro', 30, 'Primor'),
    (4, 'Azúcar Blanca', 2.50, 'kg', 80, 'Paramonga'),
    (5, 'Harina Blanca', 3.00, 'kg', 40, 'Nicolini'),
    (6, 'Fideos ', 5.50, '500g', 60, 'Don Vittorio'),
    (7, 'Leche Evaporada', 4.20, 'unidad', 70, 'Gloria'),
    (8, 'Galletas', 1.50, 'unidad', 100, 'Field'),
    (9, 'Café', 15.00, '250g', 25, 'Altomayo'),
    (10, 'Avena', 4.50, '500g', 50, '3 Ositos'),
    (11, 'Panetón ', 25.00, 'unidad', 20, 'D’Onofrio'),
    (12, 'Mantequilla ', 6.50, '250g', 30, 'Laive'),
    (13, 'Chocolate ', 2.00, 'unidad', 100, 'Nestlé'),
    (14, 'Salsa de Tomate ', 4.20, '200g', 40, 'Alacena'),
    (15, 'Atún ', 8.50, '170g', 60, 'Primor');


INSERT INTO cliente
(id, nombre, email, username, password, direccion, idDis)
VALUES(1, 'Juan Pérez', 'juan.perez@gmail.com', 'jperez', 'contrasena', 'Av. Del Ejemplo 674', 2),
      (2, 'María Quispe', 'maria.lopez@gmail.com', 'marial', '9s7g8v', 'Calle Carrion 456', 3),
      (3, 'Carlos Cano', 'carlos.cano@gmail.com', 'carcano', '0d8g90', 'Calle Santa Maria 789', 6),
      (4, 'Lucía Fernández', 'lucia.fernandez@example.com', 'lfernan', '0f9f0df', 'Av Garzon', 6),
      (5, 'Pedro Sánchez', 'pedro.sanchez@gmail.com', 'pedros', 'b97d9v', 'Av San Borja Norte 654', 2),
      (6, 'Ana Martínez', 'ana.martinez@gmail.com', 'anam', 'sid8g89dgs', 'Av La Mar 987', 1);



