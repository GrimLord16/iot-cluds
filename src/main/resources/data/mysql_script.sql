CREATE SCHEMA IF NOT EXISTS mydb;
USE mydb ;

DROP TABLE IF EXISTS `student_has_equipment`;
DROP TABLE IF EXISTS `equipment`;
DROP TABLE IF EXISTS `equipment_set`;
DROP TABLE IF EXISTS `rent`;
DROP TABLE IF EXISTS `repair`;
DROP TABLE IF EXISTS `repair_transaction`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `student_card`;
DROP TABLE IF EXISTS `transaction`;



-- -----------------------------------------------------
-- Table mydb.equipment_set
-- -----------------------------------------------------
CREATE TABLE `mydb`.`equipment_set` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `is_full` TINYINT NOT NULL,
                                        `available` TINYINT NOT NULL,
                                        `name` VARCHAR(30) NOT NULL,
                                        PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.equipment
-- -----------------------------------------------------
CREATE TABLE`mydb`.`equipment` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `isAccessible` TINYINT NOT NULL,
                                   `name` VARCHAR(45) NOT NULL,
                                   `repair_cost` INT NOT NULL,
                                   `type_of_equipment` VARCHAR(45) NOT NULL,
                                   `equipment_set_id` INT NOT NULL,

                                   PRIMARY KEY (`id`),
                                   KEY `fk_equipment_equipment_set_idx` (`equipment_set_id` ASC) VISIBLE
                                       #                                    CONSTRAINT `fk_equipment_equipment_set`
                                       #                                        FOREIGN KEY (`equipment_set_id`)
                                       #                                            REFERENCES  `mydb`.`equipment_set` (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.transaction
-- -----------------------------------------------------
CREATE TABLE `mydb`.`transaction` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `total_usd` DECIMAL(10,0) NOT NULL,
                                      PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.student_card
-- -----------------------------------------------------
CREATE TABLE`mydb`.`student_card` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `address` VARCHAR(75) NOT NULL,
                                      `birth_date` DATE NOT NULL,
                                      `email` VARCHAR(45) NOT NULL,
                                      `name` VARCHAR(45) NOT NULL,
                                      `password` VARCHAR(45) NOT NULL,
                                      `phone_number` VARCHAR(45) NOT NULL,
                                      `surname` VARCHAR(45) NOT NULL,

                                      PRIMARY KEY (`id`),
                                      UNIQUE INDEX `e-mail_UNIQUE` (`email` ASC) VISIBLE,
                                      UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.student
-- -----------------------------------------------------
CREATE TABLE `mydb`.`student` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `first_name` VARCHAR(45) NOT NULL,
                                  `last_name` VARCHAR(45) NOT NULL,
                                  `student_card_id` INT NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `fk_student_student_card1_idx1` (`student_card_id` ASC) VISIBLE)
    #                                   CONSTRAINT `fk_student_student_card1`
#                                       FOREIGN KEY (`student_card_id`)
#                                           REFERENCES `mydb`.`student_card` (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.rent
-- -----------------------------------------------------
CREATE TABLE `mydb`.`rent` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `booking_time` DATETIME NOT NULL,
                               `return_time` DATETIME NOT NULL,
                               `transaction_id` INT NULL,
                               `student_id` INT NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `fk_rent_transaction1_idx1` (`transaction_id` ASC) VISIBLE,
                               KEY `fk_rent_student1_idx` (`student_id` ASC) VISIBLE,
                               CONSTRAINT `fk_rent_transaction1`
                                   FOREIGN KEY (`transaction_id`)
                                       REFERENCES `mydb`.`transaction` (`id`),
                               CONSTRAINT `fk_rent_student1`
                                   FOREIGN KEY (`student_id`)
                                       REFERENCES `mydb`.`student` (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.repair_transaction
-- -----------------------------------------------------
CREATE TABLE `mydb`.`repair_transaction` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `repair_total_usd` DECIMAL(10,0) NULL DEFAULT NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.repair
-- -----------------------------------------------------
CREATE TABLE `mydb`.`repair` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `repair_cost` DECIMAL(10,0) NULL DEFAULT NULL,
                                 `repair_done_date` DATETIME NULL DEFAULT NULL,
                                 `return_state` TINYINT NOT NULL,
                                 `damage_type` VARCHAR(45) NULL DEFAULT NULL,
                                 `student_id` INT NOT NULL,
                                 `repair_transaction_id` INT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `fk_repair_student1_idx1` (`student_id` ASC) VISIBLE,
                                 KEY `fk_repair_repair_transaction1_idx1` (`repair_transaction_id` ASC) VISIBLE,
                                 CONSTRAINT `fk_repair_student1`
                                     FOREIGN KEY (`student_id`)
                                         REFERENCES `mydb`.`student` (`id`),
                                 CONSTRAINT `fk_repair_repair_transaction1`
                                     FOREIGN KEY (`repair_transaction_id`)
                                         REFERENCES `mydb`.`repair_transaction`(`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.student_has_equipment
-- -----------------------------------------------------
CREATE TABLE `mydb`.`student_has_equipment`
(
    `student_id`   INT not NULL,
    `equipment_id` INT not NULL,
    PRIMARY KEY (`student_id`, `equipment_id`),
    KEY `fk_student_has_equipment_equipment1_idx` (`equipment_id` ASC) VISIBLE,
    KEY `fk_student_has_equipment_student1_idx` (`student_id` ASC) VISIBLE
        #                                                 CONSTRAINT `fk_student_has_equipment_student1`
        #                                                     FOREIGN KEY (`student_id`)
        #                                                         REFERENCES `mydb`.`student` (`id`),
    #                                                 CONSTRAINT `fk_student_has_equipment_equipment1`
        #                                                     FOREIGN KEY (`equipment_id`)
        #                                                         REFERENCES `mydb`.`equipment` (`id`))
    )
    ENGINE = InnoDB;
