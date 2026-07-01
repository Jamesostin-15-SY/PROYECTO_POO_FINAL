-- =========================================================================
-- 1. TABLAS MAESTRAS (Para alimentar tus JComboBox)
-- =========================================================================

CREATE TABLE puestos (
	id_puesto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_puesto VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE turnos (
	id_turno INT AUTO_INCREMENT PRIMARY KEY,
	nombre_turno VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE roles (
	id_role INT AUTO_INCREMENT PRIMARY KEY,
    nombre_role VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE especies (
	id_especie INT AUTO_INCREMENT PRIMARY KEY,
    nombre_especie VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE servicios (
	id_servicio INT AUTO_INCREMENT PRIMARY KEY,
	nombre_servicio VARCHAR(100) NOT NULL UNIQUE,
	precio DECIMAL(10,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE estados_cita (
	id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(20) NOT NULL UNIQUE
);

-- ¡AQUÍ ESTÁ TU NUEVA TABLA MAESTRA!
CREATE TABLE estados_empleados (
	id_estado_emp INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(20) NOT NULL UNIQUE -- ACTIVO, INACTIVO
);

-- =========================================================================
-- 2. ENTIDADES PRINCIPALES
-- =========================================================================

CREATE TABLE empleados (
	dni_empleado VARCHAR(8) PRIMARY KEY,
	primer_nombre VARCHAR(50) NOT NULL,
	segundo_nombre VARCHAR(50) NULL,
	apellido_paterno VARCHAR(50) NOT NULL,
	apellido_materno VARCHAR(50) NULL,
	telefono VARCHAR(15),
	fk_id_puesto INT NOT NULL,
    fk_id_turno INT NOT NULL, 
	fk_id_estado INT NOT NULL, -- <--- NUEVO CAMPO PARA EL ELIMINADO LÓGICO
	FOREIGN KEY (fk_id_puesto) REFERENCES puestos(id_puesto),
	FOREIGN KEY (fk_id_turno) REFERENCES turnos(id_turno),
	FOREIGN KEY (fk_id_estado) REFERENCES estados_empleados(id_estado_emp) -- <--- NUEVA RELACIÓN
);

CREATE TABLE empleados_servicios (
	fk_dni_empleado VARCHAR(8) NOT NULL,
	fk_id_servicio INT NOT NULL,
	PRIMARY KEY (fk_dni_empleado, fk_id_servicio),
	FOREIGN KEY (fk_dni_empleado) REFERENCES empleados(dni_empleado) ON DELETE CASCADE,
	FOREIGN KEY (fk_id_servicio) REFERENCES servicios(id_servicio) ON DELETE CASCADE
);

CREATE TABLE usuarios_credenciales (
	id_usuario INT AUTO_INCREMENT PRIMARY KEY,
	fk_dni_empleado VARCHAR(8) NOT NULL UNIQUE,
	usuario VARCHAR(50) NOT NULL UNIQUE,
	contrasena VARCHAR(255) NOT NULL,
    fk_id_role INT NOT NULL,
	FOREIGN KEY (fk_dni_empleado) REFERENCES empleados(dni_empleado) ON DELETE CASCADE,
	FOREIGN KEY (fk_id_role) REFERENCES roles(id_role)
);

CREATE TABLE clientes (
	dni_cliente VARCHAR(8) PRIMARY KEY,
	primer_nombre VARCHAR(50) NOT NULL,
	segundo_nombre VARCHAR(50) NULL,
	apellido_paterno VARCHAR(50) NOT NULL,
	apellido_materno VARCHAR(50) NULL,
	direccion VARCHAR(255),
	telefono VARCHAR(15)
);

CREATE TABLE mascotas (
	id_mascota INT AUTO_INCREMENT PRIMARY KEY,
	fk_dni_cliente VARCHAR(8) NOT NULL,
	nombre_mascota VARCHAR(50) NOT NULL,
	fecha_nacimiento DATE NULL,
	fk_id_especie INT NOT NULL,
	raza VARCHAR(50),
	FOREIGN KEY (fk_dni_cliente) REFERENCES clientes(dni_cliente) ON DELETE CASCADE,
	FOREIGN KEY (fk_id_especie) REFERENCES especies(id_especie)
);

-- =========================================================================
-- 3. TABLA TRANSACCIONAL (Citas)
-- =========================================================================

CREATE TABLE citas (
	id_cita INT AUTO_INCREMENT PRIMARY KEY,
	fk_id_mascota INT NOT NULL,
	fk_dni_veterinario VARCHAR(8) NOT NULL,
	fecha_hora DATETIME NOT NULL,
	fk_id_servicio INT NOT NULL,
	motivo_cita TEXT,
	fk_id_estado INT NOT NULL,
	FOREIGN KEY (fk_id_mascota) REFERENCES mascotas(id_mascota) ON DELETE CASCADE,
	FOREIGN KEY (fk_dni_veterinario) REFERENCES empleados(dni_empleado),
	FOREIGN KEY (fk_id_servicio) REFERENCES servicios(id_servicio),
	FOREIGN KEY (fk_id_estado) REFERENCES estados_cita(id_estado)
);

-- =========================================================================
-- 4. ÍNDICES TODAVIA NO SE EJECUTRA (EN DUDA) 
-- =========================================================================
CREATE INDEX idx_citas_fecha ON citas(fecha_hora);
CREATE INDEX idx_clientes_apellido ON clientes(apellido_paterno);
CREATE INDEX idx_empleados_apellido ON empleados(apellido_paterno);
-- ESTO DE ARRIBA NO EJECUTAR --

/*EJECUTAR DESDE AQUÍ*/
-- 1. Insertar roles, puestos y turnos básicos
INSERT INTO roles (nombre_role) VALUES ('ADMINISTRADOR');
INSERT INTO puestos (nombre_puesto) VALUES ('ADMINISTRADOR');
INSERT INTO turnos (nombre_turno) VALUES ('MAÑANA');

-- 2. Insertar los estados de empleado obligatorios
INSERT INTO estados_empleados (nombre_estado) VALUES ('ACTIVO'), ('INACTIVO');

-- 3. Insertar empleado (El último '1' corresponde al id_estado_emp de 'ACTIVO')
INSERT INTO empleados (dni_empleado, primer_nombre, segundo_nombre, apellido_paterno, apellido_materno, telefono, fk_id_puesto, fk_id_turno, fk_id_estado) 
VALUES ('78129423', 'Raul', 'Andres', 'Chavez', 'Rivera', '987654321', 1, 1, 1);

-- 4. Vincular credenciales del administrador
INSERT INTO usuarios_credenciales (fk_dni_empleado, usuario, contrasena, fk_id_role) 
VALUES ('78129423', 'admin', 'admin123', 1);
/* HASTA AQUÍ*/
-- Verificación rápida
SELECT * FROM empleados;
SELECT * FROM usuarios_credenciales;

SELECT * FROM estados_empleados;

