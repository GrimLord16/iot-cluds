-- ----------------------------------------------------------
-- INSERTION OF DATA
-- ----------------------------------------------------------

USE mydb;

INSERT INTO mydb.student_card VALUES
                                      (1, 'Still lady', '2022-12-12', 'andrii.pavelchack@gmail.com', 'Andrii', '1234', '3801209346', 'Pavelchak'),
                                      (2, 'Still lady', '2022-12-26', 'oleh.yatskiv@gmail.com', 'Oleh', '5678', '3801204346', 'Yatskiv'),
                                      (3, 'Still lady', '2022-12-26', 'stepan.giga@gmail.com', 'Stepan', '1234', '3801234346', 'Giga'),
                                      (4, 'Still lady', '2022-12-13', 'christian.bale@gmail.com', 'Christian', '1234', '3541209346', 'Bale'),
                                      (5, 'Still lady', '2022-12-23', 'katherina.syn@gmail.com', 'Katherina', '1234', '380134509346', 'Syn'),
                                      (6, 'Still lady', '2022-12-1', 'vadym.pavlyk@gmail.com', 'Vadym', '1234', '380126549346', 'Pavlyk'),
                                      (7, 'Still lady', '2022-12-13', 'sophia.pavlyk@gmail.com', 'Sophia', '1234', '38064209346', 'Pavlyk'),
                                      (8, 'Still lady', '2022-12-14', 'cory.neville@gmail.com', 'Cory', '1234', '38012013346', 'Neville'),
                                      (9, 'Still lady', '2022-12-9', 'stephania.porter@gmail.com', 'Stephania', '1234', '3845209346', 'Porter'),
                                      (10, 'Still lady', '2022-12-7', 'sally.bottom@gmail.com', 'Sally', '1234', '3801245346', 'Bottom');

INSERT INTO `mydb`.`student` VALUES
                                 (1, 'Andrii', 'Pavelchak', 1),
                                 (2, 'Oleh', 'Yatskiv', 2),
                                 (3, 'Stepan', 'Giga', 3),
                                 (4, 'Christian', 'Bale', 4),
                                 (5, 'Katherina', 'Syn', 5),
                                 (6, 'Vadym', 'Pavlyk', 6),
                                 (7, 'Sophia', 'Pavlyk', 7),
                                 (8, 'Cory', 'Neville', 8),
                                 (9, 'Stephania', 'Porter', 9),
                                 (10, 'Sally', 'Bottom', 10);

INSERT INTO `mydb`.`equipment_set` VALUES
                                       (1, 1, 0, '3d-printing'),
                                       (2, 1, 1, '3d-printing'),
                                       (3, 1, 1, '3d-printing'),
                                       (4, 1, 0, '3d-printing 4'),
                                       (5, 1, 1, '3d-printing-1'),
                                       (6, 1, 1, 'Laser cutting - 2'),
                                       (7, 1, 1, 'Laser cutting 3'),
                                       (8, 1, 1, 'Laser-cutting - 1'),
                                       (9, 1, 1, 'Microchips'),
                                       (10, 1, 1, 'Microcontrollers');

INSERT INTO `mydb`.`equipment` VALUES
                                   (21, 1, '3d printer-1', 100, 'action', 1),
                                   (22, 1, 'laser cutter', 90, 'action',	2),
                                   (23, 1, 'plastic', 0, 'consumable', 1),
                                   (24, 1,'extruder - 1', 10, 'support', 1),
                                   (25, 1,'resistor', 0, 'consumable', 3),
                                   (26, 1,'capacitor', 0, 'consumable', 3),
                                   (27, 1,'transistor', 0, 'consumable',  3 ),
                                   (28, 1,'power generator', 20, 'support',  3),
                                   (29, 1,'microchip E234', 11, 'support', 3),
                                   (30, 1,'microchip', 34, 'support', 3);

INSERT INTO `mydb`.`transaction` VALUES
                                     (1,190),(6,300),
                                     (2,700),(7,170),
                                     (3,400),(8,150),
                                     (4,325),(10,440),
                                     (5,200),(9,360);

INSERT INTO `mydb`.`rent` VALUES
                              (1,'2022-09-09 13:52:11','2022-09-11 14:53:00', 1, 1),
                              (2,'2022-09-10 08:05:23','2022-09-11 08:05:23', 2, 2),
                              (3,'2022-09-10 16:30:11','2022-09-12 11:20:32', 3, 3),
                              (4,'2022-09-10 17:13:49','2022-09-12 17:13:49', 4, 4),
                              (5,'2022-09-10 18:20:03','2022-09-11 20:45:44', 5, 5),
                              (6,'2022-09-10 19:02:57','2022-09-13 19:02:57', 6, 6),
                              (7,'2022-09-11 08:30:02','2022-09-13 09:20:00', 7, 7),
                              (8,'2022-09-11 09:16:37','2022-09-13 09:16:37', 8, 8),
                              (9,'2022-09-11 11:56:09','2022-09-12 11:56:09', 9, 9),
                              (10,'2022-09-11 14:26:17','2022-09-14 15:36:49', 10, 10);

INSERT INTO `mydb`.`repair_transaction` VALUES
                                            (1,190),(6,300),
                                            (2,700),(7,170),
                                            (3,400),(8,150),
                                            (4,325),(10,440),
                                            (5,200),(9,360);


INSERT INTO `mydb`.`repair` VALUES
                                (1, 230, '2022-09-09 13:52:11', 1, 'sadf', 1, 1),
                                (2, 230, '2022-09-10 08:05:23', 1, 'asdf', 2, 2),
                                (3, 230, '2022-09-10 16:30:11', 1, 'sdff', 3, 3),
                                (4, 230, '2022-09-10 17:13:49',  1, 'asdf', 4, 4),
                                (5, 230, '2022-09-10 18:20:03' ,  1, 'dsfa', 5, 5),
                                (6, 230, '2022-09-10 19:02:57',  1, 'asdf', 6, 6),
                                (7, 230,'2022-09-11 08:30:02',  1, 'adsf', 7, 7),
                                (8, 230, '2022-09-11 09:16:37', 1, 'sdfa', 8, 8),
                                (9, 230, '2022-09-11 11:56:09', 1, 'dsfa', 9, 9),
                                (10, 230, '2022-09-11 14:26:17', 1, 'adsfd', 10, 10);


INSERT INTO `mydb`.`student_has_equipment` VALUES
                                               (1, 21),
                                               (2, 22),
                                               (3, 23),
                                               (4, 24),
                                               (5, 25),
                                               (6, 26),
                                               (7, 27),
                                               (8, 28),
                                               (9, 29),
                                               (10, 30);

