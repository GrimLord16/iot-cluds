package com.ua.lviv.iot.exception;

public class StudentHasEquipmentException  extends RuntimeException{
    public StudentHasEquipmentException(Integer equipmentId, Integer studentId) {
        super("'student' with id=" + studentId + "already has 'equipment' with id=" + equipmentId);
    }
}
