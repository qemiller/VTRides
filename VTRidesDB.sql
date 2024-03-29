# ----------------------------------------
# SQL script to create the tables for the 
# Virginia Tech Rides Database (VTRidesDB)
# Created by Quinton Miller on November 26, 2019
# ----------------------------------------


/*
Tables to be dropped must be listed in a logical order based on dependency.
UserQuestionnaire and UserPhoto depend on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS DefaultCar,UserRides, AllRides, UserPhoto, User;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    address1 VARCHAR(128) NOT NULL,
    address2 VARCHAR(128),
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    emergency_contact_first_name VARCHAR(128) NOT NULL,      
    emergency_contact_last_name VARCHAR(128) NOT NULL,       
    emergency_contact_email VARCHAR(128) NOT NULL,      
    emergency_contact_phone_number VARCHAR(128) NOT NULL,      
    emergency_contact_phone_carrier VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's photo. */
CREATE TABLE UserPhoto
(
   id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
   extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
   user_id INT UNSIGNED,
   FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);


/* The UserRides table contains attributes of all rides. */
CREATE TABLE AllRides
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
        driver_id INT UNSIGNED,
    passanger_1_id INT NOT NULL,
    passanger_2_id INT NOT NULL,
    passanger_3_id INT NOT NULL,
    passanger_4_id INT NOT NULL,
    passanger_5_id INT NOT NULL,
    passanger_6_id INT NOT NULL,
    seats_available INT NOT NULL,
    startingAddress1 VARCHAR(128) NOT NULL,
    startingAddress2 VARCHAR(128),
    startingCity VARCHAR(64) NOT NULL,
    startingState VARCHAR(2) NOT NULL,
    startingZipcode VARCHAR(10) NOT NULL,
    endingAddress1 VARCHAR(128) NOT NULL,
    endingAddress2 VARCHAR(128),
    endingCity VARCHAR(64) NOT NULL,
    endingState VARCHAR(2) NOT NULL,
    endingZipcode VARCHAR(10) NOT NULL,
    trip_time INT NOT NULL,
    trip_distance INT NOT NULL,
    trip_cost INT NOT NULL,
    trip_date DATE NOT NULL,
    carMake VARCHAR(128) NOT NULL,
    carModel VARCHAR(128) NOT NULL, 
    carColor VARCHAR(128) NOT NULL, 
    carLicensePlate VARCHAR(32) NOT NULL,
    carMpg INT NOT NULL,
    number_of_passangers INT NOT NULL,
    FOREIGN KEY (driver_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE UserRides
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT UNSIGNED,
    allRides_id INT NOT NULL,
    driver_username VARCHAR(32) NOT NULL,
    passanger_1_id INT NOT NULL,
    passanger_2_id INT NOT NULL,
    passanger_3_id INT NOT NULL,
    passanger_4_id INT NOT NULL,
    passanger_5_id INT NOT NULL,
    passanger_6_id INT NOT NULL,
    seats_available INT NOT NULL,
    startingAddress1 VARCHAR(128) NOT NULL,
    startingAddress2 VARCHAR(128),
    startingCity VARCHAR(64) NOT NULL,
    startingState VARCHAR(2) NOT NULL,
    startingZipcode VARCHAR(10) NOT NULL,
    endingAddress1 VARCHAR(128) NOT NULL,
    endingAddress2 VARCHAR(128),
    endingCity VARCHAR(64) NOT NULL,
    endingState VARCHAR(2) NOT NULL,
    endingZipcode VARCHAR(10) NOT NULL,
    trip_time INT NOT NULL,
    trip_distance INT NOT NULL,
    trip_cost INT NOT NULL,
    trip_date DATE NOT NULL,
    number_of_passangers INT NOT NULL,
    carMake VARCHAR(128) NOT NULL,
    carModel VARCHAR(128) NOT NULL, 
    carColor VARCHAR(128) NOT NULL, 
    carLicensePlate VARCHAR(32) NOT NULL,
    carMpg INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

CREATE TABLE DefaultCar
(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
	user_id INT UNSIGNED,
    make VARCHAR(128) NOT NULL,
    model VARCHAR(128) NOT NULL, 
    color VARCHAR(128) NOT NULL, 
    licensePlate VARCHAR(32) NOT NULL,
    mpg INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);