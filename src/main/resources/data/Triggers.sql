USE mydb;


DROP TRIGGER IF EXISTS after_insert_equipment_set;
DROP TRIGGER IF EXISTS after_update_equipment_set;
DROP TRIGGER IF EXISTS after_update_instructor;
DROP TRIGGER IF EXISTS after_delete_instructor;



# Task 1
# Додати до БД 1 додаткову довільну таблицю і зв’язати з іншою існуючою таблицею зв’язком  1:M.
# Однак для забезпечення цілісності значень використати тригери замість фізичного зовнішнього ключа.
DELIMITER //
CREATE TRIGGER after_insert_equipment_set
    AFTER INSERT
    ON equipment_set
    FOR EACH ROW
BEGIN
    IF (NEW.instructor_id NOT IN (SELECT id FROM instructor)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such instructor for this equipment_set';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_update_equipment_set
    AFTER UPDATE
    ON equipment_set
    FOR EACH ROW
BEGIN
    IF (NEW.instructor_id NOT IN (SELECT id FROM instructor)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such instructor for this equipment_set';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_update_instructor
    AFTER UPDATE
    ON instructor
    FOR EACH ROW
BEGIN
    IF (OLD.id != NEW.id AND OLD.id IN (SELECT instructor_id FROM equipment_set)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s still an equipment_set taken charge of by this instructor';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_delete_instructor
    AFTER DELETE
    ON instructor
    FOR EACH ROW
BEGIN
    IF (OLD.id IN (SELECT instructor_id FROM equipment_set)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s still an equipment_set taken charge of by this instructor';
    END IF;
END //
DELIMITER ;

# Task 3

# Для певного стовпця допускається ввід лише таких імен: 'Svitlana', 'Petro', 'Olha', 'Taras'.
DELIMITER //
CREATE TRIGGER before_insert_driver
    BEFORE INSERT
    ON student
    FOR EACH ROW
BEGIN
    IF (NEW.first_name NOT IN ( 'Taras', 'Svitlana', 'Petro', 'Olha')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Only Svitlana, Petro, Olha, Taras are allowed';
    END IF;
END //
DELIMITER ;
# Створити таблицю-журнал, в якій вести логи зі штампом часу при видаленні даних для певної таблиці
DELIMITER //
CREATE TRIGGER after_delete_transaction_log
    AFTER DELETE
    ON transaction
    FOR EACH ROW
BEGIN
    INSERT INTO transaction_log (transaction_id, total_usd, action, timestamp) VALUES (OLD.id, OLD.total_usd, 'delete', NOW());
END //
DELIMITER ;

# Створити таблицю-журнал, в якій вести логи зі штампом часу при модифікації даних для таблиці
DELIMITER //
CREATE TRIGGER after_update_transaction_log
    AFTER UPDATE
    ON transaction
    FOR EACH ROW
BEGIN
    INSERT INTO transaction_log (transaction_id, total_usd, action, timestamp) VALUES (OLD.id, OLD.total_usd, 'update', NOW());
END //
DELIMITER ;
