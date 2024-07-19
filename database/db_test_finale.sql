DROP SCHEMA weather;

CREATE SCHEMA IF NOT EXISTS weather;

USE weather;


CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS weather_daily (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    day DATE NOT NULL,
    temperature_max FLOAT,
    temperature_min FLOAT,
    precipitation_tot FLOAT,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS weather_hourly (
    id INT AUTO_INCREMENT PRIMARY KEY,
    day_id INT,
    hour TIME NOT NULL,
    temperature FLOAT,
    humidity FLOAT,
    precipitation FLOAT,
    FOREIGN KEY (day_id) REFERENCES weather_daily(id) ON DELETE CASCADE
);



SELECT * FROM user;
SELECT * FROM weather_daily;
SELECT * FROM weather_hourly;

