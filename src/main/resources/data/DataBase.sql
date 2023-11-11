IF DB_ID('spring-boot-app-db') IS NULL
    BEGIN
        CREATE DATABASE [spring-boot-app-db];
    END
USE [spring-boot-app-db];

DROP TABLE IF EXISTS student_has_equipment;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS equipment_set;
DROP TABLE IF EXISTS rent;
DROP TABLE IF EXISTS repair;
DROP TABLE IF EXISTS repair_transaction;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS student_card;
DROP TABLE IF EXISTS [transaction];

-- -----------------------------------------------------
-- Table spring-boot-app-db.equipment_set
-- -----------------------------------------------------
CREATE TABLE equipment_set (
                               id INT NOT NULL PRIMARY KEY,
                               is_full TINYINT NOT NULL,
                               available TINYINT NOT NULL,
                               name NVARCHAR(30) NOT NULL
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.equipment
-- -----------------------------------------------------
CREATE TABLE equipment (
                           id INT NOT NULL PRIMARY KEY,
                           isAccessible TINYINT NOT NULL,
                           name NVARCHAR(45) NOT NULL,
                           repair_cost INT NOT NULL,
                           type_of_equipment NVARCHAR(45) NOT NULL,
                           equipment_set_id INT NOT NULL,
                           FOREIGN KEY (equipment_set_id) REFERENCES equipment_set(id)
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.[transaction]
-- -----------------------------------------------------
CREATE TABLE [transaction] (
                               id INT NOT NULL PRIMARY KEY,
                               total_usd DECIMAL(10,0) NOT NULL
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.student_card
-- -----------------------------------------------------
CREATE TABLE student_card (
                              id INT NOT NULL PRIMARY KEY,
                              address NVARCHAR(75) NOT NULL,
                              birth_date DATE NOT NULL,
                              email NVARCHAR(45) NOT NULL,
                              name NVARCHAR(45) NOT NULL,
                              [password] NVARCHAR(45) NOT NULL,
                              phone_number NVARCHAR(45) NOT NULL,
                              surname NVARCHAR(45) NOT NULL,
                              CONSTRAINT email_unique UNIQUE (email),
                              CONSTRAINT phone_number_unique UNIQUE (phone_number)
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.student
-- -----------------------------------------------------
CREATE TABLE student (
                         id INT NOT NULL PRIMARY KEY,
                         first_name NVARCHAR(45) NOT NULL,
                         last_name NVARCHAR(45) NOT NULL,
                         student_card_id INT NOT NULL,
                         FOREIGN KEY (student_card_id) REFERENCES student_card(id)
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.rent
-- -----------------------------------------------------
CREATE TABLE rent (
                      id INT NOT NULL PRIMARY KEY,
                      booking_time DATETIME NOT NULL,
                      return_time DATETIME NOT NULL,
                      transaction_id INT NULL,
                      student_id INT NOT NULL,
                      FOREIGN KEY (transaction_id) REFERENCES [transaction](id),
                      FOREIGN KEY (student_id) REFERENCES student(id)
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.repair_transaction
-- -----------------------------------------------------
CREATE TABLE repair_transaction (
                                    id INT NOT NULL PRIMARY KEY,
                                    repair_total_usd DECIMAL(10,0) NULL
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.repair
-- -----------------------------------------------------
CREATE TABLE repair (
                        id INT NOT NULL PRIMARY KEY,
                        repair_cost DECIMAL(10,0) NULL,
                        repair_done_date DATETIME NULL,
                        return_state TINYINT NOT NULL,
                        damage_type NVARCHAR(45) NULL,
                        student_id INT NOT NULL,
                        repair_transaction_id INT NULL,
                        FOREIGN KEY (student_id) REFERENCES student(id),
                        FOREIGN KEY (repair_transaction_id) REFERENCES repair_transaction(id)
);

-- -----------------------------------------------------
-- Table spring-boot-app-db.student_has_equipment
-- -----------------------------------------------------
CREATE TABLE student_has_equipment (
                                       student_id INT NOT NULL,
                                       equipment_id INT NOT NULL,
                                       PRIMARY KEY (student_id, equipment_id),
                                       FOREIGN KEY (student_id) REFERENCES student(id),
                                       FOREIGN KEY (equipment_id) REFERENCES equipment(id)
);
