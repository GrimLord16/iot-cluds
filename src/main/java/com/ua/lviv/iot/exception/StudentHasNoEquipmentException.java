package com.ua.lviv.iot.exception;

public class StudentHasNoEquipmentException extends RuntimeException{
    public StudentHasNoEquipmentException(Integer equipmentId, Integer studentId) {
        super("Could not find 'equipment' with id=" + equipmentId + "in 'student' with id=" + studentId);
    }
}
