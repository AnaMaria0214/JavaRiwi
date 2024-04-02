CREATE TABLE especialidades (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(40) not null,
    descripcion varchar(500) not null
);

CREATE TABLE medicos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_especialidad INT NOT NULL,
    CONSTRAINT fk_id_especialidad FOREIGN KEY (id_especialidad)
        REFERENCES especialidades (id)
        ON DELETE CASCADE,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(40) NOT NULL
);

CREATE TABLE pacientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(40) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    documento_identidad INT NOT NULL
);

CREATE TABLE citas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_pacientes INT NOT NULL,
    id_medicos INT NOT NULL,
    CONSTRAINT fk_id_pacientes FOREIGN KEY (id_pacientes)
        REFERENCES pacientes (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_id_medicos FOREIGN KEY (id_medicos)
        REFERENCES medicos (id),
    fecha_cita DATE,
    hora_cita TIME,
    motivo VARCHAR(500)
);

