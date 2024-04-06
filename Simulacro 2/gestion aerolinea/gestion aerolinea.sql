
CREATE TABLE airplanes (
id INT PRIMARY KEY AUTO_INCREMENT,
model VARCHAR(30) NOT NULL,
capacity INT NOT NULL
);

CREATE TABLE flights (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination VARCHAR(100) NOT NULL,
    departure_Date DATE NOT NULL,
    departure_Time TIME NOT NULL,
    id_Airplane INT NOT NULL,
    CONSTRAINT fk_id_Airplane FOREIGN KEY (id_Airplane)
        REFERENCES airplanes (id)
        ON DELETE CASCADE
);

CREATE TABLE passengers (
id INT PRIMARY KEY AUTO_INCREMENT,
firts_Name VARCHAR(50) NOT NULL,
last_Name VARCHAR(50) NOT NULL,
identification INT NOT NULL
);

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    departure_Date DATE NOT NULL,
    seat VARCHAR(10) NOT NULL,
    id_Passenger INT NOT NULL,
    CONSTRAINT fk_id_Passenger FOREIGN KEY (id_Passenger)
        REFERENCES passengers (id)
        ON DELETE CASCADE,
    id_Flight INT NOT NULL,
    CONSTRAINT fk_id_Flight FOREIGN KEY (id_Flight)
        REFERENCES flights (id)
        ON DELETE CASCADE
);