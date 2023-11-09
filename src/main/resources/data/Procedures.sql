USE mydb;

# Task 2a
# Забезпечити параметризовану вставку нових значень у довільну таблицю.
DELIMITER //
DROP PROCEDURE IF EXISTS instructor_insertion;
CREATE PROCEDURE instructor_insertion(

    IN email VARCHAR(50),
    IN middlename VARCHAR(45),
    IN name VARCHAR(45),
    IN phone_number VARCHAR(13),
    IN surname VARCHAR(45),
    OUT instructor_id INT)
BEGIN
    INSERT INTO instructor(email, middlename, name, phone_number, surname) VALUES (email, middlename, name, phone_number, surname);
END //
DELIMITER ;

# Task 2b
# Забезпечити реалізацію зв’язку М:М між 2ма таблицями, тобто вставити в стикувальну таблицю відповідну стрічку
# за реально-існуючими значеннями (напр. surname, name) в цих основних таблицях.
DELIMITER //
CREATE PROCEDURE instructor_equipment_set(
    IN instructor_id_ INT,
    IN equipment_set_id_ INT,
    OUT instructor_equipment_set_id_ INT)
BEGIN
    DECLARE equipment_set_instructor_id INT;
    IF (instructor_id_ NOT IN (SELECT id FROM instructor)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such instructor';
    END IF;
    IF (equipment_set_id_ NOT IN (SELECT id FROM equipment_set)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such equipment_set';
    END IF;
    SET equipment_set_instructor_id = (SELECT instructor_id FROM equipment_set WHERE id = equipment_set_id_);
    IF instructor_id_ != equipment_set_instructor_id THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This equipment is not this instructor\'s responsibility';
    END IF;
    CREATE TABLE IF NOT EXISTS `instructor_equipment_set` (
                                                 `instructor_id` int NOT NULL,
                                                 `equipment_set_id` int NOT NULL,
                                                 PRIMARY KEY (`instructor_id`, `equipment_set_id`)
    );
    INSERT INTO instructor_equipment_set VALUES (instructor_id_, equipment_set_id_);
    SELECT LAST_INSERT_ID() INTO instructor_equipment_set_id_;
END //
DELIMITER ;

# Task 2c
# Створити пакет, який вставляє 10 стрічок у довільну таблицю БД у форматі <Noname+№>
# наприклад: Noname5, Noname6, Noname7 і т.д.
DELIMITER //
CREATE PROCEDURE instructor_add_nonames()
BEGIN
    DECLARE n INT;
    SET n = 1;
    label1: WHILE n < 11 DO
            INSERT INTO instructor VALUES (null, '', '', CONCAT('Noname', n), '', '');
            SET n = n + 1;
        END WHILE label1;
END //
DELIMITER ;

# Task 2d
# Написати користувацьку функцію, яка буде шукати Max, Min, Sum чи Avg для стовпця довільної таблиці у БД.
# Написати процедуру, яка буде у SELECT викликати цю функцію.
DELIMITER //
CREATE FUNCTION get_max_equipment_repair_cost()
    RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE max double;
    SET max = (SELECT MAX(repair_cost) FROM equipment);
    RETURN max;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE select_max_equipment_repair_cost(
    OUT max double)
BEGIN
    SET max = get_max_equipment_repair_cost();
END //
DELIMITER ;



# Task 2e
# Використовуючи курсор, забезпечити динамічне створення таблиць з назвами+штамп часу, взятими зі стовпця з довільної
# таблиці БД, з випадковою кількістю стовпців (від 1 до 9). Імена та тип стовпців довільні.
DELIMITER //
CREATE PROCEDURE equipment_cursor_procedure()
BEGIN
    DECLARE done int DEFAULT false;
    DECLARE equipment_type_ varchar(45);
    DECLARE n int;
    DECLARE name varchar(45);

    DECLARE equipment_type_cursor CURSOR
        FOR SELECT type_of_equipment FROM equipment;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    SET n = 1;
    OPEN equipment_type_cursor;
    myLoop: LOOP
        FETCH equipment_type_cursor INTO equipment_type_;
        IF done=true THEN LEAVE myLoop;
        END IF;
        SET name = CONCAT(equipment_type_, NOW());
        SET @first=CONCAT('DROP TABLE IF EXISTS `', name, '`;');
        SET @second=CONCAT('CREATE TABLE `', name, '` (`id` int NOT NULL AUTO_INCREMENT, `some_field` int NOT NULL, PRIMARY KEY (`id`));');
        SET @third=CONCAT('INSERT INTO `', name, '` VALUES (null, ', n, ');');
        PREPARE my_query1 FROM @first;
        EXECUTE my_query1;
        DEALLOCATE PREPARE my_query1;
        PREPARE my_query2 FROM @second;
        EXECUTE my_query2;
        DEALLOCATE PREPARE my_query2;
        PREPARE my_query3 FROM @third;
        EXECUTE my_query3;
        DEALLOCATE PREPARE my_query3;
        SET n = n + 1;
    END LOOP;
    CLOSE equipment_type_cursor;
END //
DELIMITER ;