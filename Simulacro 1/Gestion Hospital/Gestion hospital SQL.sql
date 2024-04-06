CREATE TABLE specialties (
    id_Specialties INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE TABLE physicians (
    id_Physicians INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    last_Name VARCHAR(40) NOT NULL,
    id_Specialty INT,
    CONSTRAINT fk_Id_Specialty FOREIGN KEY (id_Specialty)
        REFERENCES specialties (id_Specialties)
        ON DELETE CASCADE
);

CREATE TABLE patients (
    id_Patients INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    last_Name VARCHAR(40) NOT NULL,
    date_Birth DATE NOT NULL,
    identity_Document INT UNIQUE NOT NULL
);

CREATE TABLE appointments (
    id_Appointments INT PRIMARY KEY AUTO_INCREMENT,
    date_Appointment DATE,
    time_Appointment TIME,
    reason VARCHAR(500),
    id_Patient INT NOT NULL,
    id_Physician INT NOT NULL,
    CONSTRAINT fk_Id_Patient FOREIGN KEY (id_Patient)
        REFERENCES patients (id_Patients)
        ON DELETE CASCADE,
    CONSTRAINT fk_Id_Physician FOREIGN KEY (id_Physician)
        REFERENCES physicians (id_Physicians)ON DELETE CASCADE
);