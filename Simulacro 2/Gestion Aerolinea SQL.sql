
CREATE TABLE airplanes (
    id_Airplanes INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(30) NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE flights (
    id_Flights INT PRIMARY KEY AUTO_INCREMENT,
    destination VARCHAR(100) NOT NULL,
    departure_Date DATE NOT NULL,
    departure_Time TIME NOT NULL,
    id_Airplane INT NOT NULL,
    CONSTRAINT fk_id_Airplane FOREIGN KEY (id_Airplane)
        REFERENCES airplanes (id_Airplanes)
        ON DELETE CASCADE
);

CREATE TABLE passengers (
    id_Passengers INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    last_Name VARCHAR(50) NOT NULL,
    identification INT UNIQUE NOT NULL
);

CREATE TABLE reservations (
    id_Reservations INT PRIMARY KEY AUTO_INCREMENT,
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

INSERT INTO airplanes (model, capacity) VALUES 
('Boeing 737', 150),
('Airbus A320', 180),
('Boeing 787', 250),
('Airbus A350', 300),
('Embraer E190', 100);

INSERT INTO flights (destination, departure_Date, departure_Time, id_Airplane) VALUES 
('New York', '2024-04-10', '08:00:00', 1),
('Los Angeles', '2024-04-11', '10:30:00', 2),
('London', '2024-04-12', '12:45:00', 3),
('Paris', '2024-04-13', '14:00:00', 4),
('Tokyo', '2024-04-14', '16:15:00', 5);

INSERT INTO passengers (first_Name, last_Name, identification) VALUES 
('John', 'Smith', 123456789),
('Emily', 'Johnson', 987654321),
('Michael', 'Williams', 456789123),
('Sarah', 'Brown', 789123456),
('David', 'Jones', 321654987);

INSERT INTO reservations (reservation_Date, seat, id_Passenger, id_Flight) VALUES 
('2024-04-10', 'A1', 1, 1),
('2024-04-11', 'B2', 2, 2),
('2024-04-12', 'C3', 3, 3),
('2024-04-13', 'D4', 4, 4),
('2024-04-14', 'E5', 5, 5);
